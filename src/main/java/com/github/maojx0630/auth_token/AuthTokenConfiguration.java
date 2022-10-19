package com.github.maojx0630.auth_token;

import com.github.maojx0630.auth_token.config.AuthTokenConfig;
import com.github.maojx0630.auth_token.config.LoginAuthTokenConfig;
import com.github.maojx0630.auth_token.config.PermissionsAuthTokenConfig;
import com.github.maojx0630.auth_token.config.RoleAuthTokenConfig;
import com.github.maojx0630.auth_token.core.login.LoginInterceptor;
import com.github.maojx0630.auth_token.core.permissions.PermissionsInterceptor;
import com.github.maojx0630.auth_token.core.role.RoleInterceptor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 毛家兴
 * @since 2022/10/17 16:13
 */
@Configuration
public class AuthTokenConfiguration implements WebMvcConfigurer, InitializingBean {

  private final AuthTokenConfig config;

  private final RoleAuthTokenConfig roleConfig;

  private final LoginAuthTokenConfig loginConfig;

  private final PermissionsAuthTokenConfig permissionsConfig;

  private final ApplicationContext applicationContext;

  public AuthTokenConfiguration(
      AuthTokenConfig config,
      RoleAuthTokenConfig roleConfig,
      LoginAuthTokenConfig loginConfig,
      PermissionsAuthTokenConfig permissionsConfig,
      ApplicationContext applicationContext) {
    this.config = config;
    this.roleConfig = roleConfig;
    this.loginConfig = loginConfig;
    this.permissionsConfig = permissionsConfig;
    this.applicationContext = applicationContext;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(new AuthTokenInterceptor(config))
        .order(config.getAuthTokenHandlerInterceptorOrder())
        .addPathPatterns("/**");
    if (loginConfig.isEnable()) {
      registry
          .addInterceptor(new LoginInterceptor(config, loginConfig))
          .order(loginConfig.getOrder())
          .addPathPatterns(loginConfig.getLoginPath())
          .excludePathPatterns(loginConfig.getLoginExcludePath());
    }
    if (roleConfig.isEnable()) {
      registry
          .addInterceptor(new RoleInterceptor(config, roleConfig))
          .order(roleConfig.getOrder())
          .addPathPatterns("/**");
    }
    if (permissionsConfig.isEnable()) {
      registry
          .addInterceptor(new PermissionsInterceptor(config, permissionsConfig))
          .order(permissionsConfig.getOrder())
          .addPathPatterns("/**");
    }
  }

  @Override
  public void afterPropertiesSet() {
    ContextUtil.initContext(applicationContext);
    AuthTokenUtil.initConfig(config, applicationContext);
  }
}
