package com.github.maojx0630.auth_token.core.login;

import com.github.maojx0630.auth_token.AuthTokenConfig;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登录拦截器
 *
 * @author 毛家兴
 * @since 2022/10/18 14:08
 */
public class LoginInterceptor implements HandlerInterceptor {

  private AuthTokenConfig authTokenConfig;

  public LoginInterceptor(AuthTokenConfig authTokenConfig) {
    this.authTokenConfig = authTokenConfig;
  }
}
