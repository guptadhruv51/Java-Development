package com.example.demospringboot.test;

import org.springframework.stereotype.Component;

@Component

public class TestService
{

    public void doSomething()
    {
        System.out.println("Hello in test Service");
    }
}
