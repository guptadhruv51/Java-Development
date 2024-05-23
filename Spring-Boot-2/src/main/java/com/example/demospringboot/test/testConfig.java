package com.example.demospringboot.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration

public class testConfig
{


    /**
     * Bean is used to declare beans that are returned from a function, i.e., the classes we cannot change
     * @Componenet is a class level annotation to declare beans of the classes we develop.
     * Will create a spring bean (singleton) no matter how many objects are created for the test config class
     * @return
     */
    @Bean
    @Scope("singleton")
    public ObjectMapper getObjectMapper()
    {
        return new ObjectMapper();
    }
}
