package com.example.demo_redis.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class cacheConfig
{
    /**
     * Redis server connection
     */
    @Bean
    ObjectMapper objectMapper()
    {
        return new ObjectMapper();
    }
    @Bean
    RedisConnectionFactory getConnection()
    {
        //overloaded constructor
        RedisStandaloneConfiguration standaloneConfiguration=new RedisStandaloneConfiguration(
                "redis-11156.c299.asia-northeast1-1.gce.redns.redis-cloud.com",
                11156
        );
        standaloneConfiguration.setPassword("oU6VaCKo5EHWwCER7AIRpIgZA8bmpkd4");
        LettuceConnectionFactory lettuceConnectionFactory=new LettuceConnectionFactory(standaloneConfiguration);
        return lettuceConnectionFactory;
    }
    // object to string conversion and vice versa

    // K,V:
    //K: datatype of the key (Person as the key)  {person-->string}
    //V: datatype of the value (person as the value) {person-> string,list<string>}

    // person, student ..... Java Object, but key will always be a string
    @Bean
    public RedisTemplate <String,Object> getTemplate()
    {
                    RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
                    redisTemplate.setConnectionFactory(getConnection());
//                    redisTemplate.afterPropertiesSet();
                    redisTemplate.setKeySerializer(new StringRedisSerializer()); // string to something
                    redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer()); //Object to something
                    redisTemplate.setHashKeySerializer(new StringRedisSerializer());
                    redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
                    return redisTemplate;


    }

}
