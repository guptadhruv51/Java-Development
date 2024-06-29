package com.example.minorproject1.services;

import com.example.minorproject1.repository.TransactionRepository;
import com.example.minorproject1.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest
{

    /**
     * Skip functions which do not need to be tested
     * UTs usually for service classes (controller, repo doesn't contain business logic)
     * Code coverage: Percentage of the lines of code covered by test cases
     *  Make sure when there is a code change UT fails and the developer knows what is happening
     *  You should write test cases not onl for happy scenario but also for edge cases or
     *  failure scenarios
     *  UT: Checks the difference between the expected output and the actual output
     *  JUnit: used for writing test cases: @test, Assertions are present in it
     *  Mockito: used to write mock classes/ mock the behaviour of certain functions
     */
    // inject mock creates an actual object and mock create a dummy object
    @InjectMocks
    // similar to @Component: the object of transaction service
            // will be created and attached to the test class
    TransactionService transactionService;
    @Mock
    // Creates a dummy object of transaction repository and attaches with transaction service.
    TransactionRepository transactionRepository;

    @Test
    public void testCalculateFine()
    {


    }

}
