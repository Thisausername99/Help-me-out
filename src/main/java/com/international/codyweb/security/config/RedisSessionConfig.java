package com.international.codyweb.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
class RedisSessionConfig {

  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {
	  return new LettuceConnectionFactory();
  }
  
//Setting up the Redis template object.
  @Bean
  public RedisTemplate<?, ?> redisTemplate() {
//      final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
//      redisTemplate.setConnectionFactory(redisConnectionFactory());
//      redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
	  RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
      redisTemplate.setConnectionFactory(redisConnectionFactory());
	  return redisTemplate;
  }
  
}	