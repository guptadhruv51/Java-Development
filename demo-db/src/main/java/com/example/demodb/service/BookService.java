package com.example.demodb.service;

import com.example.demodb.controller.BookController;
import com.example.demodb.models.Book;
import com.example.demodb.repository.BookRepository;
import com.example.demodb.requests.BookCreateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import java.sql.SQLException;

@Service // inherits from component
public class BookService
{
    private Logger logger = LoggerFactory.getLogger(BookService.class);
    @Autowired
    BookRepository bookRepository;
    public void create(BookCreateRequest bookCreateRequest) throws SQLException
    {

        Book book=bookCreateRequest.to();
        this.bookRepository.create(book);
    }

    public List<Book> getAll() throws SQLException {
        return this.bookRepository.getAll();
    }

    public Book getbyId(int id) throws SQLException {
        return this.bookRepository.getById(id);
    }

    public boolean deletebyId(int id) throws SQLException
    {
        System.out.println("Inside Service Delete");
            return this.bookRepository.deletebyId(id);
    }
}
