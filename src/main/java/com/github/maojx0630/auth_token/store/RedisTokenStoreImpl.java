package com.github.maojx0630.auth_token.store;

import com.github.maojx0630.auth_token.model.AuthTokenRes;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;

/**
 * @author 毛家兴
 * @since 2022/10/19 16:28
 */
public class RedisTokenStoreImpl implements TokenStoreInterface {

  protected final String redisHead;
  protected final HashOperations<String, String, AuthTokenRes> hashRedis;

  public RedisTokenStoreImpl(String redisHead, RedisTemplate<String, ?> redisTemplate) {
    this.redisHead = redisHead;
    this.hashRedis = redisTemplate.opsForHash();
  }

  @Override
  public void put(String userKey, String tokenKey, AuthTokenRes res) {
    hashRedis.put(userKey, tokenKey, res);
  }

  @Override
  public AuthTokenRes get(String userKey, String tokenKey) {
    return hashRedis.get(userKey, tokenKey);
  }

  @Override
  public Collection<String> getAllUserKey() {
    RedisOperations<String, ?> operations = hashRedis.getOperations();
    return operations.keys(redisHead + "*");
  }

  @Override
  public Collection<AuthTokenRes> getUserAll(String userKey) {
    return hashRedis.values(userKey);
  }

  @Override
  public Collection<String> getUserAllTokenKey(String userKey) {
    return hashRedis.keys(userKey);
  }

  @Override
  public void removeUser(Collection<String> userKey) {
    hashRedis.getOperations().delete(userKey);
  }

  @Override
  public void removeToken(String userKey, Collection<String> tokenKey) {
    hashRedis.delete(userKey, tokenKey.toArray());
  }

  @Override
  public void clearAllUser() {
    Collection<String> keys = this.getAllUserKey();
    if (null != keys && !keys.isEmpty()) {
      hashRedis.getOperations().delete(keys);
    }
  }
}
