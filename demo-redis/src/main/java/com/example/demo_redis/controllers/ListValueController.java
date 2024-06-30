package com.example.demo_redis.controllers;

import com.example.demo_redis.dto.requestPerson;
import com.example.demo_redis.model.Person;
import com.example.demo_redis.services.ListValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class ListValueController {

    @Autowired
    ListValueService listValueService;
            @PostMapping("/lpush")
    public Long lpush(@RequestBody requestPerson requestPerson)
            {
                return this.listValueService.lpush(requestPerson.to());
            }
    @PostMapping("/rpush")
    public Long rpush(@RequestBody requestPerson requestPerson)
    {
        return this.listValueService.rpush(requestPerson.to());
    }
    @DeleteMapping("/lpop/")
    public List<Person> lpop(@RequestParam(value="count",required = false,defaultValue = "1") Integer count)
    {
        return this.listValueService.lpop(count);
    }
    @DeleteMapping("/rpop/")
    public List<Person> rpop(@RequestParam(value="count",required = false,defaultValue = "1") Integer count)
    {
        return this.listValueService.rpop(count);
    }
    @GetMapping("/lrange")
    public List<Person> range(@RequestParam(value="start",required = false,defaultValue = "0") Integer start,
                              @RequestParam(value="stop",required = false,defaultValue = "-1") Integer stop)
    {
        return this.listValueService.lrange((long)start, (long)stop);
    }


}
