package com.example.demospringboot;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope
// bean: object created by Spring
// Scope of a bean (no of objects created by Bean)
/**
 * Scope (name of the scope)
 * Singleton: Default, one instance is created
 * Prototype: An instance is created for every Data Injection object.
 * For example, if there were two classes calling person using Autowired, two instances will be created
 */
public class Person
{
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
