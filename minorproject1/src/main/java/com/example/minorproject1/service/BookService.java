package com.example.minorproject1.service;

import com.example.minorproject1.dtos.CreateBookRequest;
import com.example.minorproject1.dtos.GetBookDetailsResponse;
import com.example.minorproject1.models.Author;
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
    @Autowired
    AuthorService authorService;
    @Autowired
    GetBookDetailsResponse getBookDetailsResponse;
    public List<Book> getBooksByStudentId(Integer id)
    {
            return this.bookRepository.findByStudentId(id);
    }
    public Integer create(CreateBookRequest request)
    {
        Book book=request.maptoBook();
        Author author=book.getAuthor();
        author = this.authorService.getOrCreate(author);
        book.setAuthor(author);
        this.bookRepository.save(book);
        return book.getId();
    }

    public Book getById(Integer id)
    {
        return this.bookRepository.findById(id).orElseThrow(null);
    }
    public GetBookDetailsResponse getbyId2(Integer id)
    {
        Book book=this.bookRepository.findById(id).orElse(null);
        GetBookDetailsResponse response=new GetBookDetailsResponse();
        response.setName(book.getName());
        response.setAuthorName(book.getAuthor().getName());
        response.setGenre(book.getGenre());
        return response;
    }
}
