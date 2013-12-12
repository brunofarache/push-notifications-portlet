/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.mobile.pushnotifications.sender.android;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import com.liferay.mobile.pushnotifications.model.Device;
import com.liferay.mobile.pushnotifications.service.DeviceLocalServiceUtil;
import com.liferay.mobile.pushnotifications.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Silvio Santos
 * @author Bruno Farache
 */
public class AndroidNotificationSender {

	public static void send(
			long userId, String collapseKey, String data, int timeToLive,
			boolean delayWhileIdle)
		throws IOException, SystemException {

		List<Device> devices = DeviceLocalServiceUtil.getUserDevices(
			userId, PLATFORM);

		if (devices.isEmpty()) {
			return;
		}

		List<String> tokens = getTokens(devices);

		Message message = buildMessage(
			collapseKey, data, timeToLive, delayWhileIdle);

		Sender sender = new Sender(PortletPropsValues.ANDROID_API_KEY);

		MulticastResult multicastResult = sender.send(message, tokens, 5);

		handleResponse(devices, multicastResult);
	}

	protected static Message buildMessage(
		String collapseKey, String data, int timeToLive,
		boolean delayWhileIdle) {

		Message.Builder builder = new Message.Builder();

		if (data != null) {
			builder.addData("data", data);
		}

		if (collapseKey != null) {
			builder.collapseKey(collapseKey);
		}

		if ((timeToLive >= 0) || (timeToLive <= MAX_TIME_TO_LIVE)) {
			builder.timeToLive(timeToLive);
		}

		builder.delayWhileIdle(delayWhileIdle);

		return builder.build();
	}

	protected static List<String> getTokens(List<Device> devices) {
		List<String> tokens = new ArrayList<String>();

		for (Device device : devices) {
			tokens.add(device.getToken());
		}

		return tokens;
	}

	protected static void handleResponse(
		List<Device> devices, MulticastResult multicastResult) {

		if ((multicastResult.getCanonicalIds() == 0) &&
			(multicastResult.getFailure() == 0)) {

			return;
		}

		List<Result> results = multicastResult.getResults();

		for (int i = 0; i < results.size(); i++) {
			Result result = results.get(i);

			String messageId = result.getMessageId();
			String token = result.getCanonicalRegistrationId();

			Device device = devices.get(i);

			try {
				if (Validator.isNotNull(messageId) &&
					Validator.isNotNull(token)) {

					DeviceLocalServiceUtil.deleteDevice(device);

					device.setToken(token);

					DeviceLocalServiceUtil.addDevice(device);

					continue;
				}

				String error = result.getErrorCodeName();

				if (Validator.isNotNull(error)) {
					if (error.equals(Constants.ERROR_NOT_REGISTERED) ||
						error.equals(Constants.ERROR_INVALID_REGISTRATION)) {

						DeviceLocalServiceUtil.deleteDevice(device);
					}
				}
			}
			catch (SystemException se) {
				_log.error(se);
			}
		}
	}

	protected static final int MAX_TIME_TO_LIVE = 2419200;

	protected static final String PLATFORM = "android";

	private static Log _log = LogFactoryUtil.getLog(
		AndroidNotificationSender.class);

}