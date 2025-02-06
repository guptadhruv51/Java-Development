package com.project.review.controller;

import com.project.review.DTO.MovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/")
public class AdminController
{
    @Autowired
    MovieRequest movieRequest;
    @PostMapping("addMovie")
    public ResponseEntity<String> createMovie(@RequestBody MovieRequest movieRequest)
    {
        movieRequest.addMovie(movieRequest);
        return new ResponseEntity<>("Movie added", HttpStatus.CREATED );

    }
}
