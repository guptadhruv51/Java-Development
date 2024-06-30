package com.example.demo_redis.controllers;

import com.example.demo_redis.dto.requestPerson;
import com.example.demo_redis.model.Person;
import com.example.demo_redis.services.StringValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class StringValueController
{

        @Autowired
    StringValueService stringValueService;


            @PostMapping("/value")
        public Person createPerson(@RequestBody requestPerson requestPerson)
            {
                    return this.stringValueService.create(requestPerson.to());
            }

            @GetMapping("/value/{id}")
    public Person getPerson(@PathVariable("id") String id)
            {
                return this.stringValueService.get(id);
            }

}
