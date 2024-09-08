package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class UserController
{
    @Autowired
    UserService userservice;
    @PostMapping
    public void createUser(@Valid @RequestBody CreateUserRequest createUserRequest ) throws JsonProcessingException {
            this.userservice.create(createUserRequest);
    }

}
