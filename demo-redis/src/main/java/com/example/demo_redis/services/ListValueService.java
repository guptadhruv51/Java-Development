package com.example.demo_redis.services;

import com.example.demo_redis.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ListValueService {

    private static final Long REDIX_KEY_EXPIRY= 10L;
    private static String REDIS_KEY="person_list";
    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    public Long lpush(Person person)
    {
    return this.redisTemplate.opsForList().leftPush(REDIS_KEY,person);
    }

    public Long rpush(Person person)
    {
        return this.redisTemplate.opsForList().rightPush(REDIS_KEY,person);
    }
    public List<Person> rpop(Integer count)
    {
        return this.redisTemplate.
                opsForList().
                rightPop(REDIS_KEY,count)
                .stream()
                .map(x->(Person) x)
                .collect(Collectors.toList());
    }
    public List<Person> lpop(Integer count)
    {
        return this.redisTemplate.
                opsForList().
                leftPop(REDIS_KEY,count)
                .stream()
                .map(x->(Person) x)
                .collect(Collectors.toList());
    }

    public List<Person> lrange(Long start, Long last)
    {
        return this.redisTemplate.opsForList().range(REDIS_KEY,start,last).stream().map(x->(Person) x).collect(Collectors.toList());
    }
}

