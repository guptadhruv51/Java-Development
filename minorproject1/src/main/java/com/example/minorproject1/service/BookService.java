package com.example.minorproject1.service;

import com.example.minorproject1.models.Book;
import com.example.minorproject1.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService
{
    @Autowired
    BookRepository bookRepository;
    public List<Book> getBooksByStudentId(Integer id)
    {
            return this.bookRepository.findByStudentId(id);
    }
}
