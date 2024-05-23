package com.example.demospringboot.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController
{
        private TestService testService;
    @Value("${test.property.value}")
        int testProp;
    @Value("${test.property.str}")
    String testStr;

        /**
         * Getting value from properties for variable
         * Autowired for methods
         * Hashmap<String,Object> String is the name of the property
         */

@Autowired // does not work on static fields
TestController(TestService testService){
        System.out.println("Inside testController: testService = " + testService);
        this.testService = testService;
        this.testService.doSomething();
        
    }
    /**
 * Autowired: Field Dependency injection:
     * If there are multiple parameterized constructors, Spring is confused which one to initialize using Autowired
     * If there are two constructors, autowired tells java which constructor to use (solving dilemma)
     *  1. Happens once the constructor is executed, i.e.; inside the constructor, the value of injected dependency is null
     *  2. Field injection cannot have a final keyword because whenever we use final the value needs to be given immediately or in the constructor (during compile time).
     *
     * Constructor Injection
     *  1.
     *  2. Autowired on top of constructors when there are multiple parameterized constructors and solves the problem of Spring dilemma.
     *  3. When only one parameterized constructor, Autowired annotation does not matter.
 */
}
