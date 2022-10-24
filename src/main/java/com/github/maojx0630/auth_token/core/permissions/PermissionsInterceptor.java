package com.github.maojx0630.auth_token.core.permissions;

import com.github.maojx0630.auth_token.config.AuthTokenConfig;
import com.github.maojx0630.auth_token.config.PermissionsAuthTokenConfig;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 权限拦截器
 *
 * @author 毛家兴
 * @since 2022/10/18 13:49
 */
public class PermissionsInterceptor implements HandlerInterceptor {

  private final AuthTokenConfig authTokenConfig;

  private final PermissionsAuthTokenConfig permissionsConfig;

  public PermissionsInterceptor(
      AuthTokenConfig authTokenConfig, PermissionsAuthTokenConfig permissionsConfig) {
    this.authTokenConfig = authTokenConfig;
    this.permissionsConfig = permissionsConfig;
  }
}
