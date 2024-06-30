package com.example.demo_redis.controllers;

import com.example.demo_redis.dto.requestPerson;
import com.example.demo_redis.model.Person;
import com.example.demo_redis.services.HashValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class HashValueOperator
{

    @Autowired
    HashValueService hashValueService;
    @PostMapping("/hash")
    public Person createPerson(@RequestBody requestPerson requestPerson)
    {
        return this.hashValueService.set(requestPerson.to());
    }
    @DeleteMapping("/hash")

    public void createPerson(@RequestParam("id") String id,
                               @RequestParam("field") String field)
    {
        this.hashValueService.deleteField(id,field);
    }

    @GetMapping("/hash/all")

    public Person getPerson(@RequestParam("id") String id)
    {
        return this.hashValueService.getPerson(id);
    }

    @GetMapping("/hash/field")

    public Person getPersonByField(@RequestParam("id") String id,
                            @RequestParam("field") String field)
    {
        return (Person)this.hashValueService.getValueForField(id,field);
    }

}
