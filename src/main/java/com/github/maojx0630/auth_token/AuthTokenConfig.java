package com.github.maojx0630.auth_token;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

/**
 * @author 毛家兴
 * @since 2022/10/17 16:21
 */
@Configuration
@ConfigurationProperties(prefix = "auth-token")
public class AuthTokenConfig {

  /** 设置为true会使用redis存储token信息 (使用spring data redis操作redis) */
  private boolean redisCache = false;

  /** redis存储时的key头 */
  private String redisHead = "auth_token_login_cache_key";

  /** token名称 */
  private String tokenName = "authentication";

  /** 是否尝试从param里读取token */
  private boolean readParam = true;

  /** 是否尝试从header里读取token */
  private boolean readHeader = true;

  /** 默认过期时间 */
  private long tokenTimeout = 1000 * 60 * 60 * 24;
  /** 是否访问后重置过期时间 */
  private boolean overdueReset = true;

  /** 是否同端互斥 */
  private boolean deviceReject = false;

  /** 是否允许同一账号并发登录 (为true时允许一起登录, 为false时新登录挤掉旧登录) */
  private boolean concurrentLogin = true;

  /** 是否启用登录拦截器 */
  private boolean loginHandlerInterceptor = true;

  /** 登录拦截器执行顺序 */
  private int loginHandlerInterceptorOrder = 1;

  /** 登录拦截路径 */
  private List<String> loginPath = Collections.singletonList("/**");

  /** 排除登录拦截路径 */
  private List<String> loginExcludePath = Collections.singletonList("/login/**");

  /** 是否启用角色拦截器 */
  private boolean roleHandlerInterceptor = false;

  /** 角色拦截器执行顺序 */
  private int roleHandlerInterceptorOrder = 2;

  /** 是否启用权限拦截器 */
  private boolean permissionsHandlerInterceptor = false;

  /** 权限拦截器执行顺序 */
  private int permissionsHandlerInterceptorOrder = 2;

  /** token拦截器执行顺序 */
  private int authTokenHandlerInterceptorOrder = 0;

  /** 用户token信息签名校验使用 可以通过 SecureUtil.sign(SignAlgorithm.SHA256withRSA)重新生成 */
  private String signPublicKeyBase64 =
      "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDKU7AFbo4ZeKc/19dcoEfL0/avgODtgMLbP0Skdc6oAP7OkChizJheuAr6fWOgI3x+pwxWK5h4xlh3JlwL2LY9wUOzkWAkNgAbn+ml7K8q1viox4D7rAwm18c1d7T/ujXgoAwLrymcTFtbx3K/SnWLw3SSu2egFd1UjOOaHpKsJwIDAQAB";

  /** 用户token信息签名校验使用 可以通过 SecureUtil.sign(SignAlgorithm.SHA256withRSA)重新生成 */
  private String signPrivateKeyBase64 =
      "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMpTsAVujhl4pz/X11ygR8vT9q+A4O2Awts/RKR1zqgA/s6QKGLMmF64Cvp9Y6AjfH6nDFYrmHjGWHcmXAvYtj3BQ7ORYCQ2ABuf6aXsryrW+KjHgPusDCbXxzV3tP+6NeCgDAuvKZxMW1vHcr9KdYvDdJK7Z6AV3VSM45oekqwnAgMBAAECgYEAhC3ePf426V8QbL4W3VV88iV88LVSvPa01kVkE8k/BDT6QnqdV8Zv+NllmrXWIyVrzNZXUehTSPvTiavO0JJ4o4Af4wN6FqLcXcuBhN4QJR6d4Rh8ZooYZCceXOjfyERU76jtk/Fz2Y8w7w1M3gilg6j9Vrg61eDQ2uj5ib8163kCQQDqu2+Zni+nafQfAy27F4y4O/phUIp+oOl0muhXOZYxKOrGMQ3Fj6qRtABy5ifOEt1Pd+mmDaUazK2aAv8E8ze1AkEA3KieQfochDBM7BTuhlwHGTllu0tcQNDd8zut/RxjxtUmgrmUrZz2tB2SALcScgCVrBV859QTRTcBI+RsGVgF6wJAPBJj6Sh2gZ24AAfMOEk6lhujZCw8hVOg61qJg2kVatdR3g7Hu3uc51TtmHoWTcK+KIxDm196lXU0KGoD4bxs0QJAUN0WShVvd93/gpDVLLXzAAJxzqZ6Y2JnxCh9xYEu64HXgBakbs9T9YPUqqBsiQy/zPV+9bJZcYcLyux91PfWCwJAV55zsKrjU4k4rHpaSnD4CcH0W2HpG6jn4wBSwLD2GgbmglpcYJQEwBxxaPlTij5Fx0H7Xqemphhgttx2UcnR0Q==";

  public String getRedisHead() {
    return redisHead;
  }

  public void setRedisHead(String redisHead) {
    this.redisHead = redisHead;
  }

  public String getTokenName() {
    return tokenName;
  }

  public void setTokenName(String tokenName) {
    this.tokenName = tokenName;
  }

  public boolean isReadParam() {
    return readParam;
  }

  public void setReadParam(boolean readParam) {
    this.readParam = readParam;
  }

  public boolean isReadHeader() {
    return readHeader;
  }

  public void setReadHeader(boolean readHeader) {
    this.readHeader = readHeader;
  }

  public long getTokenTimeout() {
    return tokenTimeout;
  }

  public void setTokenTimeout(long tokenTimeout) {
    this.tokenTimeout = tokenTimeout;
  }

  public boolean isOverdueReset() {
    return overdueReset;
  }

  public void setOverdueReset(boolean overdueReset) {
    this.overdueReset = overdueReset;
  }

  public boolean isDeviceReject() {
    return deviceReject;
  }

  public void setDeviceReject(boolean deviceReject) {
    this.deviceReject = deviceReject;
  }

  public boolean isConcurrentLogin() {
    return concurrentLogin;
  }

  public void setConcurrentLogin(boolean concurrentLogin) {
    this.concurrentLogin = concurrentLogin;
  }

  public boolean isLoginHandlerInterceptor() {
    return loginHandlerInterceptor;
  }

  public void setLoginHandlerInterceptor(boolean loginHandlerInterceptor) {
    this.loginHandlerInterceptor = loginHandlerInterceptor;
  }

  public int getLoginHandlerInterceptorOrder() {
    return loginHandlerInterceptorOrder;
  }

  public void setLoginHandlerInterceptorOrder(int loginHandlerInterceptorOrder) {
    this.loginHandlerInterceptorOrder = loginHandlerInterceptorOrder;
  }

  public List<String> getLoginPath() {
    return loginPath;
  }

  public void setLoginPath(List<String> loginPath) {
    this.loginPath = loginPath;
  }

  public List<String> getLoginExcludePath() {
    return loginExcludePath;
  }

  public void setLoginExcludePath(List<String> loginExcludePath) {
    this.loginExcludePath = loginExcludePath;
  }

  public boolean isRoleHandlerInterceptor() {
    return roleHandlerInterceptor;
  }

  public void setRoleHandlerInterceptor(boolean roleHandlerInterceptor) {
    this.roleHandlerInterceptor = roleHandlerInterceptor;
  }

  public int getRoleHandlerInterceptorOrder() {
    return roleHandlerInterceptorOrder;
  }

  public void setRoleHandlerInterceptorOrder(int roleHandlerInterceptorOrder) {
    this.roleHandlerInterceptorOrder = roleHandlerInterceptorOrder;
  }

  public boolean isPermissionsHandlerInterceptor() {
    return permissionsHandlerInterceptor;
  }

  public void setPermissionsHandlerInterceptor(boolean permissionsHandlerInterceptor) {
    this.permissionsHandlerInterceptor = permissionsHandlerInterceptor;
  }

  public int getPermissionsHandlerInterceptorOrder() {
    return permissionsHandlerInterceptorOrder;
  }

  public void setPermissionsHandlerInterceptorOrder(int permissionsHandlerInterceptorOrder) {
    this.permissionsHandlerInterceptorOrder = permissionsHandlerInterceptorOrder;
  }

  public int getAuthTokenHandlerInterceptorOrder() {
    return authTokenHandlerInterceptorOrder;
  }

  public void setAuthTokenHandlerInterceptorOrder(int authTokenHandlerInterceptorOrder) {
    this.authTokenHandlerInterceptorOrder = authTokenHandlerInterceptorOrder;
  }

  public String getSignPublicKeyBase64() {
    return signPublicKeyBase64;
  }

  public void setSignPublicKeyBase64(String signPublicKeyBase64) {
    this.signPublicKeyBase64 = signPublicKeyBase64;
  }

  public String getSignPrivateKeyBase64() {
    return signPrivateKeyBase64;
  }

  public void setSignPrivateKeyBase64(String signPrivateKeyBase64) {
    this.signPrivateKeyBase64 = signPrivateKeyBase64;
  }
}
