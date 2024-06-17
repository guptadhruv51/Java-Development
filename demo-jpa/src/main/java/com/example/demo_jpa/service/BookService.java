package com.example.demo_jpa.service;

import com.example.demo_jpa.dtos.BookCreateRequest;
import com.example.demo_jpa.models.Book;
import com.example.demo_jpa.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BookService
{
    private final Logger logger = LoggerFactory.getLogger(BookService.class);
    @Autowired
    BookRepository bookRepository;
    //bean name: bookrepository bean type: SimpleJpaRepository<Book>
    // because bookrepo interface extends jparepo, which in turn is implemented by simplejparepo
    public void create(BookCreateRequest bookCreateRequest) throws SQLException
    {

        Book book=bookCreateRequest.to();
        this.bookRepository.save(book);
       // this.bookRepository.create(book);
    }

    public List<Book> getAll() throws SQLException {
        //return null ;
        return this.bookRepository.findAll();

    }

    public Book getbyId(int id) throws SQLException
    {

        // findbyId means find by primary key
        //returns an optional
        return this.bookRepository.
                findById(id)
                .orElse(null);

        //orElse()
        //orElseGet()
        //prElseThrow()
        //this.bookRepository.getById(id);
    }
}
