package com.example.demo_redis.services;

import com.example.demo_redis.dto.requestPerson;
import com.example.demo_redis.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class StringValueService
{
    private static final String REDIS_KEY_PREFIX="Person::";
    private static final Long REDIX_KEY_EXPIRY= 10L;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    public Person create(Person p)
    {
        this.redisTemplate.opsForValue().set(REDIS_KEY_PREFIX+p.getId(),p,REDIX_KEY_EXPIRY, TimeUnit.MINUTES);
        return p;
    }
    //setex: set with timeout
    public Person get(String id)
    {
        return (Person) this.redisTemplate.opsForValue().get(REDIS_KEY_PREFIX+id);
    }

}
