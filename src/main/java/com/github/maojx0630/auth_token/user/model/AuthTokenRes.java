package com.github.maojx0630.auth_token.user.model;

/**
 * @author 毛家兴
 * @since 2022/10/18 14:33
 */
public class AuthTokenRes {

  private String id;

  private String userKey;

  private String tokenKey;

  private String token;

  private Long timeout;

  private Long loginTime;

  private String userType;

  private Long lastAccessTime;

  private String deviceType;

  private String deviceName;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserKey() {
    return userKey;
  }

  public void setUserKey(String userKey) {
    this.userKey = userKey;
  }

  public String getTokenKey() {
    return tokenKey;
  }

  public void setTokenKey(String tokenKey) {
    this.tokenKey = tokenKey;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Long getTimeout() {
    return timeout;
  }

  public void setTimeout(Long timeout) {
    this.timeout = timeout;
  }

  public Long getLoginTime() {
    return loginTime;
  }

  public void setLoginTime(Long loginTime) {
    this.loginTime = loginTime;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public Long getLastAccessTime() {
    return lastAccessTime;
  }

  public void setLastAccessTime(Long lastAccessTime) {
    this.lastAccessTime = lastAccessTime;
  }

  public String getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(String deviceType) {
    this.deviceType = deviceType;
  }

  public String getDeviceName() {
    return deviceName;
  }

  public void setDeviceName(String deviceName) {
    this.deviceName = deviceName;
  }
}
