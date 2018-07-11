package com.fuyi.cache.redis.config;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

	//@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setKeySerializer(new ObjectRedisSerializer());
		redisTemplate.setValueSerializer(new ObjectRedisSerializer());
		return redisTemplate;
	}

	//@Bean // important!
	public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate) {
		RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);

		// 设置默认的过期时间
		redisCacheManager.setDefaultExpiration(20);

		// 单独设置  userCache缓存过期时间
		Map<String, Long> expires = new HashMap<String, Long>();
		expires.put("userCache", 10L);
		redisCacheManager.setExpires(expires);

		return redisCacheManager;
	}
	
	/**
	 * 自定义key的生成策略
	 * 根据类名+方法名+所有参数的值生成唯一的一个key
	 * 即使@Cacheable中的value属性一样，key也会不一样
	 */
	@Override
	@Bean
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {

			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuffer sb = new StringBuffer();
				sb.append(target.getClass().getName());
				sb.append("." + method.getName());
				for(Object arg : params) {
					sb.append("." + arg.toString());
				}
				return sb.toString();
			}};
	}
}
