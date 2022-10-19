package com.github.maojx0630.auth_token.store;

import com.github.maojx0630.auth_token.user.model.AuthTokenRes;

import java.util.List;

/**
 * @author 毛家兴
 * @since 2022/10/19 10:19
 */
public interface TokenStoreInterface {

  /**
   * 存储token信息
   *
   * @param userKey 用户key
   * @param tokenKey token key
   * @param res token信息
   * @author 毛家兴
   * @since 2022/10/19 10:27
   */
  void put(String userKey, String tokenKey, AuthTokenRes res);

  /**
   * 根据key获取token信息 需自己实现token过期
   *
   * @param userKey 用户key
   * @param tokenKey token key
   * @return com.github.maojx0630.auth_token.user.model.AuthTokenRes
   * @author 毛家兴
   * @since 2022/10/19 10:26
   */
  AuthTokenRes get(String userKey, String tokenKey);

  /**
   * 获取一个用户所有的登录信息
   *
   * @param userKey 用户key
   * @return java.util.List<com.github.maojx0630.auth_token.user.model.AuthTokenRes>
   * @author 毛家兴
   * @since 2022/10/19 10:27
   */
  List<AuthTokenRes> getUserAll(String userKey);

  /**
   * 获取一个用户所有的token key
   *
   * @param userKey 用户key
   * @return java.util.List<java.lang.String>
   * @author 毛家兴
   * @since 2022/10/19 13:41
   */
  List<String> getUserAllTokenKey(String userKey);
  /**
   * 移除一个用户所有的token
   *
   * @param userKey user key
   * @author 毛家兴
   * @since 2022/10/19 13:21
   */
  void removeUser(String userKey);

  /**
   * 移除一个用户的一个token
   *
   * @param userKey user key
   * @param tokenKey token key
   * @author 毛家兴
   * @since 2022/10/19 13:22
   */
  void removeToken(String userKey, String tokenKey);

  /**
   * 清理所有用户
   *
   * @author 毛家兴
   * @since 2022/10/19 13:42
   */
  void clearAllUser();
}
