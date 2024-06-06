package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController
{
    private static Logger logger= LoggerFactory.getLogger(DemoController.class);
    @Autowired
    private Person person;
    @GetMapping("/demo")
    public String demo2()
    {
        logger.info("Inside demo:{}", person);
        return "Hello World";
    }

}
