package org.example;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
import org.springframework.web.bind.annotation.PostMapping;

public class UserController
{
    @Autowired
    UserService userservice;
    @PostMapping
    public void createUser(@Valid @RequestBody CreateUserRequest createUserRequest )
    {

    }

}
