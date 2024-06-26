package com.example.demo_jpa.Controller;


import com.example.demo_jpa.dtos.BookCreateRequest;
import com.example.demo_jpa.models.Book;
import com.example.demo_jpa.models.Genre;
import com.example.demo_jpa.service.BookService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController

public class BookController
{
    private Logger logger = LoggerFactory.getLogger(BookController.class);
    @Autowired
    BookService bookservice;
    @PostMapping("/book")
    public void createBook(@Valid @RequestBody BookCreateRequest bookCreateRequest) throws SQLException
    {
        this.bookservice.create(bookCreateRequest);

    }
    @GetMapping("/book/all")
    public List<Book> getBooks() throws SQLException
    {
        return this.bookservice.getAll();

    }

    @GetMapping("/book/{id}")
    public Book getBookbyId(@PathVariable("id") int id) throws SQLException
    {
        return this.bookservice.getbyId(id);

    }

    @GetMapping("book/author/{authorEmail}/jpql")
    public List<Book> getBooksByEmailJPQL(@PathVariable("authorEmail") String authorEmail)
    {

        return this.bookservice.getbyEmailJPQL(authorEmail);
    }
    @GetMapping("book/author/{authorEmail}/native")
    public List<Book> getBooksByEmailNative(@PathVariable("authorEmail") String authorEmail)
    {

        return this.bookservice.getbyEmaiNative(authorEmail);
    }


}
