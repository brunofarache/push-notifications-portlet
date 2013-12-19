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
import com.liferay.mobile.pushnotifications.service.base.DeviceServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Silvio Santos
 * @author Bruno Farache
 */
public class DeviceServiceImpl extends DeviceServiceBaseImpl {

	@Override
	public void addDevice(String token, String platform)
		throws PortalException, SystemException {

		deviceLocalService.addDevice(getUserId(), token, platform);
	}

	@Override
	public void deleteDevice(String token)
		throws PortalException, SystemException {

		try {
			Device device = deviceLocalService.getDeviceByToken(token);

			if (getUserId() == device.getUserId()) {
				deviceLocalService.deleteDevice(device.getToken());
			}
		}
		catch (NoSuchDeviceException nsde) {
			if (_log.isInfoEnabled()) {
				_log.info("Device " + token + " does not exist");
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(DeviceServiceImpl.class);

}