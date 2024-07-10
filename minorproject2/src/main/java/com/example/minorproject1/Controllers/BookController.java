package com.example.minorproject1.Controllers;

import com.example.minorproject1.dtos.CreateBookRequest;
import com.example.minorproject1.dtos.GetBookDetailsResponse;
import com.example.minorproject1.models.Book;
import com.example.minorproject1.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController
{
    @Autowired
    BookService bookService;

    @PostMapping("")
    public Integer createBook(@Valid @RequestBody CreateBookRequest createBookRequest)
    {
        return bookService.create(createBookRequest);
    }
    @GetMapping("/{bookId}")
    public Book getBookbyId(@PathVariable("bookId") Integer id)
    {
        return this.bookService.getById(id);
        //response DTo
    }
    @GetMapping("/{bookId}/")
    public GetBookDetailsResponse getBookbyId2(@PathVariable("bookId") Integer id)
    {
        return this.bookService.getbyId2(id);
        //response DTo
    }
}
