package com.example.minorproject1.Controllers;

import com.example.minorproject1.dtos.CreateStudentRequest;
import com.example.minorproject1.dtos.GetStudentDetailsResponse;
import com.example.minorproject1.dtos.UpdateStudentRequest;
import com.example.minorproject1.models.Student;
import com.example.minorproject1.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentControllers
{
    @Autowired
    StudentService studentService;
    @PostMapping()
    public Integer createStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest)
    {
            return studentService.create(createStudentRequest);
    }
    @GetMapping("/{studentId}")
    public GetStudentDetailsResponse getstudentDetails
            (@PathVariable("studentId") int id,
             @RequestParam(value = "require-book-list",required = false,
                     defaultValue = "false") boolean requireBookList)
    {
        return this.studentService.getStudentDetailsResponse(id,requireBookList);

    }
    @PatchMapping("/{studentId}")
    public GetStudentDetailsResponse updateStudentDetails(
            @PathVariable ("studentId") Integer id,
             @Valid @RequestBody UpdateStudentRequest updateStudentRequest)

    {
    return this.studentService.update(id,updateStudentRequest);
    }
    @DeleteMapping("/{studentId}")
    public GetStudentDetailsResponse DeactivateService(@PathVariable ("studentId") Integer id)
    {
        return this.studentService.deactivate(id);
    }
}
