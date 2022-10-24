package com.github.maojx0630.auth_token.core.role;

import com.github.maojx0630.auth_token.AuthTokenUtil;
import com.github.maojx0630.auth_token.config.role.RoleAuthTokenConfig;
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
 * 角色拦截器
 *
 * @author 毛家兴
 * @since 2022/10/18 13:49
 */
public class RoleInterceptor implements HandlerInterceptor {

  private final RoleAuthTokenConfig roleConfig;

  private final RoleInfoInterface roleInfoInterface;

  public RoleInterceptor(RoleAuthTokenConfig roleConfig, RoleInfoInterface roleInfoInterface) {
    this.roleConfig = roleConfig;
    this.roleInfoInterface = roleInfoInterface;
  }

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    String uri = request.getRequestURI();
    Optional<AuthTokenRes> optUser = AuthTokenUtil.getOptUser();
    if (optUser.isPresent()) {
      AntPathMatcher matcher = new AntPathMatcher();
      AuthTokenRes res = optUser.get();
      List<String> userRoleInfo = roleInfoInterface.getUserRoleInfo(res);
      ag:
      for (RoleModel role :
          roleInfoInterface.getAllRoleList().stream()
              .filter(item -> userRoleInfo.contains(item.getRole()))
              .collect(Collectors.toList())) {
        for (String pattern : role.getExcludePathPatterns()) {
          // 如果是被排除路径命中 这个角色就不通过
          if (matcher.match(pattern, uri)) {
            continue ag;
          }
        }
        // 如果拥有权限命中 就拥有权限
        for (String pattern : role.getPathPatterns()) {
          if (matcher.match(pattern, uri)) {
            return true;
          }
        }
      }
    }
    ResponseUtils.writeStr(response, roleConfig.getHttpCode(), roleConfig.getMessage());
    return false;
  }
}
