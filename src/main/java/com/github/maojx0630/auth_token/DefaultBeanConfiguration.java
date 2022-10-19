package com.github.maojx0630.auth_token;

import com.github.maojx0630.auth_token.config.AuthTokenConfig;
import com.github.maojx0630.auth_token.store.LocalTokenStoreImpl;
import com.github.maojx0630.auth_token.store.RedisTokenStoreImpl;
import com.github.maojx0630.auth_token.store.TokenStoreInterface;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author 毛家兴
 * @since 2022/10/19 16:42
 */
@Configuration
public class DefaultBeanConfiguration {

  @ConditionalOnMissingBean(TokenStoreInterface.class)
  @ConditionalOnProperty(prefix = "auth-token.core", name = "redisCache", havingValue = "true")
  public TokenStoreInterface redisTokenStoreInterface(
      AuthTokenConfig config, RedisTemplate<String, ?> redisTemplate) {
    return new RedisTokenStoreImpl(config.getRedisHead(), redisTemplate);
  }

  @ConditionalOnMissingBean(TokenStoreInterface.class)
  public TokenStoreInterface localTokenStoreInterface(AuthTokenConfig config) {
    return new LocalTokenStoreImpl(config);
  }
}
