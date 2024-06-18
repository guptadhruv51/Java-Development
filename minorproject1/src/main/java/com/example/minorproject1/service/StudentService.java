package com.example.minorproject1.service;

import com.example.minorproject1.dtos.GetStudentDetailsResponse;
import com.example.minorproject1.models.Book;
import com.example.minorproject1.models.Student;
import com.example.minorproject1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService
{
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    BookService bookService;
    public GetStudentDetailsResponse getStudentDetailsResponse(Integer id)
    {
        Student student=studentRepository.findById(id).orElse(null);
        List<Book> bookList=this.bookService.getBooksByStudentId(id);
        return GetStudentDetailsResponse.builder().student(student)
                        .bookList(bookList)
                .build();
    }


}
