package com.example.minorproject1.service;

import com.example.minorproject1.models.Author;
import com.example.minorproject1.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService
{
    @Autowired
    AuthorRepository authorRepository;
    public Author getOrCreate(Author author)
    {
        Author savedAuthor=this.authorRepository.findByEmail(author.getEmail());
        if(savedAuthor==null)
        {
            this.authorRepository.save(author);
            return author;
        }
        return savedAuthor;
    }

}
