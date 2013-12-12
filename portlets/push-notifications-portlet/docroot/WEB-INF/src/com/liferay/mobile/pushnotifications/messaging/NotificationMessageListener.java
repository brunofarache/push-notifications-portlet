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

package com.liferay.mobile.pushnotifications.messaging;

import com.liferay.mobile.pushnotifications.sender.android.AndroidNotificationSender;
import com.liferay.mobile.pushnotifications.sender.ios.iOSNotificationSender;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;

import java.util.List;

/**
 * @author Silvio Santos
 */
public class NotificationMessageListener implements MessageListener {

	public void receive(Message message) {
		List<Long> userIds = (List<Long>)message.get("userIds");
		String alert = message.getString("alert");
		String collapseKey = message.getString("collapseKey");
		String data = message.getString("data");
		int timeToLive = message.getInteger("timeToLive");
		boolean delayWhileIdle = message.getBoolean("delayWhileIdle");
		int badge = message.getInteger("badge");
		String sound = message.getString("sound");

		for (long userId : userIds) {
			try {
				AndroidNotificationSender.send(
					userId, collapseKey, data, timeToLive, delayWhileIdle);

				iOSNotificationSender.send(userId, alert, data, badge, sound);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		NotificationMessageListener.class);

}