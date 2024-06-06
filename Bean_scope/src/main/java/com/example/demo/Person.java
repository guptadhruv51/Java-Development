package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class Person
{
    private static Logger logger= LoggerFactory.getLogger(Person.class);
    public Person()
    {
        logger.info("inside Person default:{}", this);
    }


}
//inside Person default:com.example.demo.Person@78010562
//Inside demo2:com.example.demo.Person@78010562
//Inside demo2:com.example.demo.Person@78010562

//Prototype
//inside Person default:com.example.demo.Person@6b4283c4
//Inside demo:com.example.demo.Person@6b4283c4
//inside Person default:com.example.demo.Person@69391e08
//Inside demo2:com.example.demo.Person@69391e08