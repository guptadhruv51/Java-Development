package com.example.demo_jpa.service;

import com.example.demo_jpa.dtos.BookCreateRequest;
import com.example.demo_jpa.models.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
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
}
