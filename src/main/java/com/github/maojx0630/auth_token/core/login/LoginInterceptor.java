package com.github.maojx0630.auth_token.core.login;

import com.github.maojx0630.auth_token.config.AuthTokenConfig;
import com.github.maojx0630.auth_token.config.LoginAuthTokenConfig;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 * @author 毛家兴
 * @since 2022/10/18 14:08
 */
public class LoginInterceptor implements HandlerInterceptor {

  private final AuthTokenConfig authTokenConfig;

  private final LoginAuthTokenConfig loginConfig;

  public LoginInterceptor(AuthTokenConfig authTokenConfig, LoginAuthTokenConfig loginConfig) {
    this.authTokenConfig = authTokenConfig;
    this.loginConfig = loginConfig;
  }

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    return true;
  }
}
