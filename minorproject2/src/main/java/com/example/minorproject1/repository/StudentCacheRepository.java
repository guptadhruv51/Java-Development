package com.example.minorproject1.repository;

import com.example.minorproject1.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository

public class StudentCacheRepository

{
    private static final String Student_key_prefix="std::";
    private static final Long Student_key_expiry= 3600L;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    public void add(Student student)
    {
        String key=this.getKey(student.getId());
        this.redisTemplate.opsForValue().set(key,student,Student_key_expiry, TimeUnit.SECONDS);


    }
    public Student getfromCache(Integer studentId)
    {
            String key=this.getKey(studentId);
            return (Student) this.redisTemplate.opsForValue().get(key);
    }
    private String getKey(Integer studentId)
    {
    return Student_key_prefix+studentId;
    }

}
