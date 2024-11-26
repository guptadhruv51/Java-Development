package com.example.demo_security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller
{
    @GetMapping("/hello")
    public String sayHello()
    {
        return "Hello";
    }
    @PreAuthorize("hasAnyRole('USER','admin')")
    //@PreAuthorize("hasRole('admin')")
    @GetMapping("/user")
    public String userEndpoint()
    {
        return "Hello User!!";
    }
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/admin")
    public String adminEndpoint()
    {
        return "Hello Admin";
    }
}
