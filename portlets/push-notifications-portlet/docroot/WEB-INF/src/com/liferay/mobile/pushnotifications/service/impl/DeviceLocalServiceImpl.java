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

package com.liferay.mobile.pushnotifications.service.impl;

import com.liferay.mobile.pushnotifications.NoSuchDeviceException;
import com.liferay.mobile.pushnotifications.model.Device;
import com.liferay.mobile.pushnotifications.service.base.DeviceLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Silvio Santos
 * @author Bruno Farache
 */
public class DeviceLocalServiceImpl extends DeviceLocalServiceBaseImpl {

	@Override
	public Device addDevice(long userId, String token, String platform)
		throws PortalException, SystemException {

		long deviceId = counterLocalService.increment();

		Device device = devicePersistence.create(deviceId);

		device.setUserId(userId);
		device.setToken(token);
		device.setPlatform(platform);
		device.setCreateDate(new Date());

		deviceLocalService.updateDevice(device);

		return device;
	}

	@Override
	public Device deleteDevice(String token)
		throws NoSuchDeviceException, SystemException {

		Device device = devicePersistence.findByToken(token);

		devicePersistence.remove(device);

		return device;
	}

	@Override
	public Device getDeviceByToken(String token)
		throws NoSuchDeviceException, SystemException {

		return devicePersistence.findByToken(token);
	}

	@Override
	public List<String> getTokens(long userId, String platform)
		throws SystemException {

		List<String> tokens = new ArrayList<String>();

		List<Device> devices = devicePersistence.findByU_P(userId, platform);

		for (Device device : devices) {
			tokens.add(device.getToken());
		}

		return tokens;
	}

	@Override
	public Device updateToken(String oldToken, String newToken)
		throws NoSuchDeviceException, SystemException {

		Device device = devicePersistence.findByToken(oldToken);

		device.setToken(newToken);

		devicePersistence.update(device);

		return device;
	}

	@Override
	public Device updateUserId(long userId, String token)
		throws NoSuchDeviceException, SystemException {

		Device device = devicePersistence.findByToken(token);

		device.setUserId(userId);

		devicePersistence.update(device);

		return device;
	}

}