package com.example.demo_redis.services;

import com.example.demo_redis.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service
public class HashValueService
{

    @Autowired
    ObjectMapper objectMapper;
    private static final Long REDIX_KEY_EXPIRY= 10L;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    // person= field: value pairs

    private static final String REDIS_KEY_PREFIX="person_hash::";
    public String get(String id)
    {

        return REDIS_KEY_PREFIX+id;
    }
    public Person set(Person person)
    {
        //convert a person object to a map of field and values and then pass it to put all
        //HK: hash key or field in terms of redis
        //HV: hash value of field value in terms of redis
        // a key k stores pairs of HK and HV
        // key: string
        //HK: String (properties of the object)
        //HV-Object
       Map fieldValuepairs=this.objectMapper.convertValue(person,Map.class);

        this.redisTemplate.opsForHash().putAll(get(person.getId()),fieldValuepairs);
        return person;
    }
    public Person getPerson(String personId)
    {
        Map map=this.redisTemplate.opsForHash().entries(get(personId));
        return this.objectMapper.convertValue(map,Person.class);
    }
    public Object getValueForField(String id, String field)
    {
        return this.redisTemplate.opsForHash().get(get(id),field);
    }

    public void  deleteField(String id, String field)
    {
        this.redisTemplate.opsForHash().delete(get(id),field);
    }
}
