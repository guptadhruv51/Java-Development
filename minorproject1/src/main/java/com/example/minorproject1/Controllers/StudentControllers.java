package com.example.minorproject1.Controllers;

import com.example.minorproject1.dtos.GetStudentDetailsResponse;
import com.example.minorproject1.models.Student;
import com.example.minorproject1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentControllers
{
    @Autowired
    StudentService studentService;
    @GetMapping("/{studentId}")
    public GetStudentDetailsResponse getstudentDetails(@PathVariable("studentId") int id)
    {
        return this.studentService.getStudentDetailsResponse(id);

    }
}
