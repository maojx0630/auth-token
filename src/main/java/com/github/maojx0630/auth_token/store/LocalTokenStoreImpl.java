package com.github.maojx0630.auth_token.store;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.maojx0630.auth_token.config.AuthTokenConfig;
import com.github.maojx0630.auth_token.user.model.AuthTokenRes;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author 毛家兴
 * @since 2022/10/19 15:38
 */
public class LocalTokenStoreImpl implements TokenStoreInterface {

  private final AuthTokenConfig config;

  public LocalTokenStoreImpl(AuthTokenConfig authTokenConfig) {
    this.config = authTokenConfig;
  }

  private static final Cache<String, Cache<String, AuthTokenRes>> CACHE_CACHE =
      Caffeine.newBuilder().build();

  @Override
  public void put(String userKey, String tokenKey, AuthTokenRes res) {
    CACHE_CACHE
        .get(
            userKey,
            (k) -> {
              if (config.isOverdueReset()) {
                return Caffeine.newBuilder()
                    .expireAfterAccess(res.getTimeout(), TimeUnit.MILLISECONDS)
                    .removalListener(LocalTokenStoreImpl::removalListener)
                    .build();
              } else {
                return Caffeine.newBuilder()
                    .expireAfterWrite(res.getTimeout(), TimeUnit.MILLISECONDS)
                    .removalListener(LocalTokenStoreImpl::removalListener)
                    .build();
              }
            })
        .put(tokenKey, res);
  }

  @Override
  public AuthTokenRes get(String userKey, String tokenKey) {
    Cache<String, AuthTokenRes> cache = CACHE_CACHE.getIfPresent(userKey);
    if (cache != null) {
      return cache.getIfPresent(tokenKey);
    }
    return null;
  }

  @Override
  public Collection<AuthTokenRes> getUserAll(String userKey) {
    Cache<String, AuthTokenRes> cache = CACHE_CACHE.getIfPresent(userKey);
    if (cache != null) {
      return cache.asMap().values();
    }
    return Collections.emptyList();
  }

  @Override
  public Collection<String> getUserAllTokenKey(String userKey) {
    Cache<String, AuthTokenRes> cache = CACHE_CACHE.getIfPresent(userKey);
    if (cache != null) {
      return cache.asMap().keySet();
    }
    return Collections.emptyList();
  }

  @Override
  public void removeUser(Collection<String> userKey) {
    CACHE_CACHE.invalidateAll(userKey);
  }

  @Override
  public void removeToken(String userKey, Collection<String> tokenKey) {
    Cache<String, AuthTokenRes> cache = CACHE_CACHE.getIfPresent(userKey);
    if (cache != null) {
      cache.invalidateAll(tokenKey);
    }
  }

  @Override
  public void clearAllUser() {
    CACHE_CACHE.invalidateAll();
  }

  /**
   * 如果移除后没有其他key 就删除这个key
   *
   * @author 毛家兴
   * @since 2022/10/19 16:16
   */
  private static void removalListener(String key, AuthTokenRes res, RemovalCause cause) {
    Cache<String, AuthTokenRes> cache = CACHE_CACHE.getIfPresent(res.getUserKey());
    if (null != cache) {
      cache.cleanUp();
      if (cache.estimatedSize() == 0L) {
        CACHE_CACHE.invalidate(res.getUserKey());
      }
    }
  }
}
