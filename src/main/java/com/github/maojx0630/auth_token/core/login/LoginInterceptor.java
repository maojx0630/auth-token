package com.github.maojx0630.auth_token.core.login;

import com.github.maojx0630.auth_token.AuthTokenUtil;
import com.github.maojx0630.auth_token.config.login.LoginAuthTokenConfig;
import com.github.maojx0630.auth_token.util.ResponseUtils;
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

  private final LoginAuthTokenConfig loginConfig;

  public LoginInterceptor(LoginAuthTokenConfig loginConfig) {
    this.loginConfig = loginConfig;
  }

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    if (AuthTokenUtil.getOptUser().isPresent()) {
      return true;
    } else {
      ResponseUtils.writeStr(response, loginConfig.getHttpCode(), loginConfig.getMessage());
      return false;
    }
  }
}
