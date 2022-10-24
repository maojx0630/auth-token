package com.github.maojx0630.auth_token.model;

import cn.hutool.core.util.StrUtil;
import com.github.maojx0630.auth_token.ContextUtil;
import com.github.maojx0630.auth_token.config.AuthTokenConfig;

/**
 * @author 毛家兴
 * @since 2022/10/18 14:37
 */
public class LoginParam {

  private Long timeout;

  private Long loginTime;

  private String userType;

  private String deviceType;

  private String deviceName;

  public Long getTimeout() {
    return timeout;
  }

  public LoginParam setTimeout(Long timeout) {
    this.timeout = timeout;
    return this;
  }

  public Long getLoginTime() {
    return loginTime;
  }

  public LoginParam setLoginTime(Long loginTime) {
    this.loginTime = loginTime;
    return this;
  }

  public String getUserType() {
    return userType;
  }

  public LoginParam setUserType(String userType) {
    this.userType = userType;
    return this;
  }

  public String getDeviceType() {
    return deviceType;
  }

  public LoginParam setDeviceType(String deviceType) {
    this.deviceType = deviceType;
    return this;
  }

  public String getDeviceName() {
    return deviceName;
  }

  public LoginParam setDeviceName(String deviceName) {
    this.deviceName = deviceName;
    return this;
  }

  public LoginParam build() {
    AuthTokenConfig config = ContextUtil.getBean(AuthTokenConfig.class);
    if (null == timeout) {
      timeout = config.getTokenTimeout();
    }
    if (null == loginTime) {
      loginTime = System.currentTimeMillis();
    }
    if (StrUtil.isBlank(userType)) {
      userType = "default";
    }
    if (StrUtil.isBlank(deviceType)) {
      deviceType = "unknown";
    }
    if (StrUtil.isBlank(deviceName)) {
      deviceName = "未知设备";
    }
    return this;
  }
}
