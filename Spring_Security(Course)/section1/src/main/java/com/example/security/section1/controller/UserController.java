package com.example.security.section1.controller;

import com.example.security.section1.Model.Customer;
import com.example.security.section1.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer)
    {
        try
        {
                String hashpwd=passwordEncoder.encode(customer.getPwd());
                customer.setPwd(hashpwd);
                Customer savedCustomer=customerRepo.save(customer);
                if(savedCustomer.getId()>0)
                {
                    return new
                            ResponseEntity<>("User created succesfully", HttpStatus.CREATED);

                }
                else {
                    return new
                            ResponseEntity<>("User Registration Failed", HttpStatus.BAD_REQUEST);

                }
        }
        catch(Exception e)
        {
            return new ResponseEntity<>("Failed with Exception"+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
