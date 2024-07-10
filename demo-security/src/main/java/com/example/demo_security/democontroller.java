package com.example.demo_security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class democontroller
{

    @GetMapping("/hello")

    public String Hello()
    {
        return "Hello World";
    }
}
/**
 * Different authentication mechanisms
 * In memory
 * 
 */
