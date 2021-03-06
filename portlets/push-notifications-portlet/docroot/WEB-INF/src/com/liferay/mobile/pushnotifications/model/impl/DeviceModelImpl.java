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

package com.liferay.mobile.pushnotifications.model.impl;

import com.liferay.mobile.pushnotifications.model.Device;
import com.liferay.mobile.pushnotifications.model.DeviceModel;
import com.liferay.mobile.pushnotifications.model.DeviceSoap;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the Device service. Represents a row in the &quot;PushNotifications_Device&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.mobile.pushnotifications.model.DeviceModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DeviceImpl}.
 * </p>
 *
 * @author Silvio Santos
 * @see DeviceImpl
 * @see com.liferay.mobile.pushnotifications.model.Device
 * @see com.liferay.mobile.pushnotifications.model.DeviceModel
 * @generated
 */
@JSON(strict = true)
public class DeviceModelImpl extends BaseModelImpl<Device>
	implements DeviceModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a device model instance should use the {@link com.liferay.mobile.pushnotifications.model.Device} interface instead.
	 */
	public static final String TABLE_NAME = "PushNotifications_Device";
	public static final Object[][] TABLE_COLUMNS = {
			{ "deviceId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "platform", Types.VARCHAR },
			{ "token", Types.VARCHAR }
		};
	public static final String TABLE_SQL_CREATE = "create table PushNotifications_Device (deviceId LONG not null primary key,userId LONG,createDate DATE null,platform VARCHAR(75) null,token VARCHAR(250) null)";
	public static final String TABLE_SQL_DROP = "drop table PushNotifications_Device";
	public static final String ORDER_BY_JPQL = " ORDER BY device.deviceId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY PushNotifications_Device.deviceId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.mobile.pushnotifications.model.Device"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.mobile.pushnotifications.model.Device"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.mobile.pushnotifications.model.Device"),
			true);
	public static long PLATFORM_COLUMN_BITMASK = 1L;
	public static long TOKEN_COLUMN_BITMASK = 2L;
	public static long USERID_COLUMN_BITMASK = 4L;
	public static long DEVICEID_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Device toModel(DeviceSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Device model = new DeviceImpl();

		model.setDeviceId(soapModel.getDeviceId());
		model.setUserId(soapModel.getUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setPlatform(soapModel.getPlatform());
		model.setToken(soapModel.getToken());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Device> toModels(DeviceSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Device> models = new ArrayList<Device>(soapModels.length);

		for (DeviceSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.mobile.pushnotifications.model.Device"));

	public DeviceModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _deviceId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setDeviceId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _deviceId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Device.class;
	}

	@Override
	public String getModelClassName() {
		return Device.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("deviceId", getDeviceId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("platform", getPlatform());
		attributes.put("token", getToken());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long deviceId = (Long)attributes.get("deviceId");

		if (deviceId != null) {
			setDeviceId(deviceId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		String platform = (String)attributes.get("platform");

		if (platform != null) {
			setPlatform(platform);
		}

		String token = (String)attributes.get("token");

		if (token != null) {
			setToken(token);
		}
	}

	@JSON
	@Override
	public long getDeviceId() {
		return _deviceId;
	}

	@Override
	public void setDeviceId(long deviceId) {
		_deviceId = deviceId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public long getOriginalUserId() {
		return _originalUserId;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public String getPlatform() {
		if (_platform == null) {
			return StringPool.BLANK;
		}
		else {
			return _platform;
		}
	}

	@Override
	public void setPlatform(String platform) {
		_columnBitmask |= PLATFORM_COLUMN_BITMASK;

		if (_originalPlatform == null) {
			_originalPlatform = _platform;
		}

		_platform = platform;
	}

	public String getOriginalPlatform() {
		return GetterUtil.getString(_originalPlatform);
	}

	@JSON
	@Override
	public String getToken() {
		if (_token == null) {
			return StringPool.BLANK;
		}
		else {
			return _token;
		}
	}

	@Override
	public void setToken(String token) {
		_columnBitmask |= TOKEN_COLUMN_BITMASK;

		if (_originalToken == null) {
			_originalToken = _token;
		}

		_token = token;
	}

	public String getOriginalToken() {
		return GetterUtil.getString(_originalToken);
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			Device.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Device toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Device)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		DeviceImpl deviceImpl = new DeviceImpl();

		deviceImpl.setDeviceId(getDeviceId());
		deviceImpl.setUserId(getUserId());
		deviceImpl.setCreateDate(getCreateDate());
		deviceImpl.setPlatform(getPlatform());
		deviceImpl.setToken(getToken());

		deviceImpl.resetOriginalValues();

		return deviceImpl;
	}

	@Override
	public int compareTo(Device device) {
		long primaryKey = device.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Device)) {
			return false;
		}

		Device device = (Device)obj;

		long primaryKey = device.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public void resetOriginalValues() {
		DeviceModelImpl deviceModelImpl = this;

		deviceModelImpl._originalUserId = deviceModelImpl._userId;

		deviceModelImpl._setOriginalUserId = false;

		deviceModelImpl._originalPlatform = deviceModelImpl._platform;

		deviceModelImpl._originalToken = deviceModelImpl._token;

		deviceModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Device> toCacheModel() {
		DeviceCacheModel deviceCacheModel = new DeviceCacheModel();

		deviceCacheModel.deviceId = getDeviceId();

		deviceCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			deviceCacheModel.createDate = createDate.getTime();
		}
		else {
			deviceCacheModel.createDate = Long.MIN_VALUE;
		}

		deviceCacheModel.platform = getPlatform();

		String platform = deviceCacheModel.platform;

		if ((platform != null) && (platform.length() == 0)) {
			deviceCacheModel.platform = null;
		}

		deviceCacheModel.token = getToken();

		String token = deviceCacheModel.token;

		if ((token != null) && (token.length() == 0)) {
			deviceCacheModel.token = null;
		}

		return deviceCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{deviceId=");
		sb.append(getDeviceId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", platform=");
		sb.append(getPlatform());
		sb.append(", token=");
		sb.append(getToken());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.mobile.pushnotifications.model.Device");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>deviceId</column-name><column-value><![CDATA[");
		sb.append(getDeviceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>platform</column-name><column-value><![CDATA[");
		sb.append(getPlatform());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>token</column-name><column-value><![CDATA[");
		sb.append(getToken());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = Device.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] { Device.class };
	private long _deviceId;
	private long _userId;
	private String _userUuid;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private Date _createDate;
	private String _platform;
	private String _originalPlatform;
	private String _token;
	private String _originalToken;
	private long _columnBitmask;
	private Device _escapedModel;
}