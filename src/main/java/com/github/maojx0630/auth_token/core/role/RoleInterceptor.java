package com.github.maojx0630.auth_token.core.role;

import com.github.maojx0630.auth_token.AuthTokenConfig;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 角色拦截器
 *
 * @author 毛家兴
 * @since 2022/10/18 13:49
 */
public class RoleInterceptor implements HandlerInterceptor {
  private AuthTokenConfig authTokenConfig;

  public RoleInterceptor(AuthTokenConfig authTokenConfig) {
    this.authTokenConfig = authTokenConfig;
  }
}
