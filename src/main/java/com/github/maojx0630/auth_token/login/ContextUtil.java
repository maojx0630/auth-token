package com.github.maojx0630.auth_token.login;

import org.springframework.context.ApplicationContext;

/**
 * @author 毛家兴
 * @since 2022/10/18 15:20
 */
public final class ContextUtil {

  private static ApplicationContext context;

  private ContextUtil() {}

  static void initContext(ApplicationContext applicationContext) {
    context = applicationContext;
  }

  public static ApplicationContext getContext() {
    return context;
  }

  public static <T> T getBean(Class<T> requiredType) {
    return context.getBean(requiredType);
  }
}
