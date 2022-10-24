package com.github.maojx0630.auth_token.core.permissions;

import com.github.maojx0630.auth_token.AuthTokenUtil;
import com.github.maojx0630.auth_token.config.permissions.PermissionsAuthTokenConfig;
import com.github.maojx0630.auth_token.model.AuthTokenRes;
import com.github.maojx0630.auth_token.util.ResponseUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 权限拦截器
 *
 * @author 毛家兴
 * @since 2022/10/18 13:49
 */
public class PermissionsInterceptor implements HandlerInterceptor {

  private final PermissionsInfoInterface permissionsInfo;

  private final PermissionsAuthTokenConfig permissionsConfig;

  public PermissionsInterceptor(
      PermissionsInfoInterface permissionsInfo, PermissionsAuthTokenConfig permissionsConfig) {
    this.permissionsInfo = permissionsInfo;
    this.permissionsConfig = permissionsConfig;
  }

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    String uri = request.getRequestURI();
    Optional<AuthTokenRes> optUser = AuthTokenUtil.getOptUser();
    if (optUser.isPresent()) {
      AntPathMatcher matcher = new AntPathMatcher();
      AuthTokenRes res = optUser.get();
      List<String> userPermissionsList = permissionsInfo.getUserPermissionsInfo(res);
      for (PermissionsModel model :
          permissionsInfo.getAllPermissionsList().stream()
              .filter(item -> userPermissionsList.contains(item.getPermissions()))
              .collect(Collectors.toList())) {
        // 如果拥有权限命中 就拥有权限
        if (matcher.match(model.getPathPatterns(), uri)) {
          return true;
        }
      }
    }
    ResponseUtils.writeStr(
        response, permissionsConfig.getHttpCode(), permissionsConfig.getMessage());
    return false;
  }
}
