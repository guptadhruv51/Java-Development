package com.example.demodb.controller;

import com.example.demodb.models.Book;
import com.example.demodb.requests.BookCreateRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demodb.service.BookService;

@RestController

public class BookController
{
    @Autowired
    BookService bookservice;
    @PostMapping("/book")
    public Book createBook(@Valid @RequestBody BookCreateRequest bookCreateRequest)
    {
            return null;

    }


}
