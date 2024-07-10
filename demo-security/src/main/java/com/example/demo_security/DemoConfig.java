package com.example.demo_security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class DemoConfig
{

    @Bean
    public InMemoryUserDetailsManager configureAuth()
    {
        UserDetails u1= User.builder()
                .username("Dhruv")
                .password("dhruv@1234").authorities("admin")
                .build();
        UserDetails u2=User.builder()
                .username("Gaurav")
                .password("abc").authorities("admin")
                .build();
        return new InMemoryUserDetailsManager(u1,u2);
    }
    @Bean
    public

    //BCryptPasswordEncoder: Strength represents how secure we want the password to be. Supports [4-31] with default=10
    // Security increases the time of encryption.




}
