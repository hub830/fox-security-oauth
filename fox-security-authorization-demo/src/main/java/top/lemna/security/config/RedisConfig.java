package top.lemna.security.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import top.lemna.security.util.GenericJackson2JsonRedisSerializerEx;


@Configuration
@EnableAutoConfiguration
public class RedisConfig {


/*
  @SuppressWarnings("rawtypes")
  @Bean
  RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
      final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
      GenericJackson2JsonRedisSerializer json = new GenericJackson2JsonRedisSerializer();
      template.setConnectionFactory(redisConnectionFactory);
      template.setKeySerializer(new StringRedisSerializer());
      template.setHashValueSerializer(new GenericToStringSerializer<Object>(Object.class));
      template.setValueSerializer(json);
      return template;
  }*/

  @Bean
  RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    RedisTemplate template = new RedisTemplate();
    template.setConnectionFactory(redisConnectionFactory);
    template.setKeySerializer(new StringRedisSerializer());
    template.setDefaultSerializer(new GenericJackson2JsonRedisSerializerEx());
    template.setEnableDefaultSerializer(true);
    return template;
  }
}
