package com.example.demospringboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController
{
    @Autowired // this will get the reference of the person object created by Spring
    private Person person;
    // Autowiring: value is initialized after the constructor of the class
    /**
     * Autowired=new person() one and the same thing for Spring
     * if we use private Person person=new Person() the object has to be managed by yourself
     * @Autowired
     * private person person=new person()
     * 2 Objects are created: one by yourself and the other as a shared instance of singleton bean
     */
    private static Logger logger=  LoggerFactory.getLogger(DemoController.class);
    DemoController()
    {
        logger.info("Inside Demo this={}",this);
    }

    /**
     * The democontroller object is created only once
     * Who creates the objet?
     * @RestConroller helps
     * @return
     */
    /**
     * IOC: Inversion of Control
     * Delegating the responsibility to someone else to create the objects etc.
     * Instead of you creating an object of a particular class Spring creates it for you
     * All the objects that are created by the Spring will be created before the application is started,
     * You need to tell Spring for what classes the object creation needs to be done
     * Spring will create the objects for only those classes that have @Component annotation on it either directly or indirectly
     * : RestController inherits @Component so indirectly uses it
     * If there are two constructors : default and parameterized  default object is created, and if only parametrised we eed to provide the parameters
     * if there is an error in IOC the application will not start at all
     * Whenever Spring creates an object itself, it stores the object in a container called an IOC container and is known as ApplicationContext.
     * Space in the memory containing all the objects created by Spring (only the objects created by Spring)
     *
     *
     *
     * Dependency Injection (DI)
     *  1. A particular class can get the reference of any other dependent class object from IOC container
     *  2. If the object is not present in the IOC container there cannot be any dependency injection
     *
     *
     *
     * Bean: Something managed by Spring could be an object, property, variable
     * @return
     */
    @GetMapping("/demo")
    public String get()
    {
        return "Hello World";
    }
}
