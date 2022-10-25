package com.github.maojx0630.auth_token.login;

import cn.hutool.core.util.StrUtil;
import com.github.maojx0630.auth_token.config.AuthTokenConfig;

/**
 * @author 毛家兴
 * @since 2022/10/18 14:37
 */
public class LoginParam {

  Long timeout;

  Long loginTime;

  String userType;

  String deviceType;

  String deviceName;

  private LoginParam() {}

  public static LoginParam.Builder builder() {
    return new LoginParam.Builder();
  }

  public static class Builder {
    private final LoginParam loginParam = new LoginParam();

    public LoginParam.Builder timeout(Long timeout) {
      loginParam.timeout = timeout;
      return this;
    }

    public LoginParam.Builder loginTime(Long loginTime) {
      loginParam.loginTime = loginTime;
      return this;
    }

    public LoginParam.Builder userType(String userType) {
      loginParam.userType = userType;
      return this;
    }

    public LoginParam.Builder deviceType(String deviceType) {
      loginParam.deviceType = deviceType;
      return this;
    }

    public LoginParam.Builder deviceName(String deviceName) {
      loginParam.deviceName = deviceName;
      return this;
    }

    public LoginParam build() {
      return loginParam;
    }
  }

  LoginParam completion(AuthTokenConfig config) {
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
