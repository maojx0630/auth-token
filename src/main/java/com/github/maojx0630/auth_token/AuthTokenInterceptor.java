package com.github.maojx0630.auth_token;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于检查token填充登录用户
 *
 * @author 毛家兴
 * @since 2022/10/18 13:48
 */
public class AuthTokenInterceptor implements HandlerInterceptor {

  private AuthTokenConfig authTokenConfig;

  public AuthTokenInterceptor(AuthTokenConfig authTokenConfig) {
    this.authTokenConfig = authTokenConfig;
  }

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    //    AuthTokenUtil.setThreadLocal();
    return true;
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    AuthTokenUtil.removeThreadLocal();
  }
}
