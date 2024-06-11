package com.example.demodb.service;

import com.example.demodb.models.Book;
import com.example.demodb.repository.BookRepository;
import com.example.demodb.requests.BookCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // inherits from component
public class BookService
{
    @Autowired
    BookRepository bookRepository;
    public void create(BookCreateRequest bookCreateRequest)
    {

        Book book=bookCreateRequest.to();
    }

}
