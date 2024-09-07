package com.example.minorproject1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class CacheConfig
{

    @Bean
    JedisConnectionFactory connectionfactory()
    {
        RedisStandaloneConfiguration redisStandaloneConfiguration=new RedisStandaloneConfiguration();
        return  new JedisConnectionFactory(redisStandaloneConfiguration);
    }


        @Bean
    RedisTemplate<String,Object> getTemplate()
        {
            RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
            redisTemplate.setConnectionFactory(connectionfactory());
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
            return redisTemplate;
        }

}
