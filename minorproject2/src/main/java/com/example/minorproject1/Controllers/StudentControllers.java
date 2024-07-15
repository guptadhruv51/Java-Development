package com.example.minorproject1.Controllers;

import com.example.minorproject1.dtos.CreateStudentRequest;
import com.example.minorproject1.dtos.GetStudentDetailsResponse;
import com.example.minorproject1.dtos.UpdateStudentRequest;
import com.example.minorproject1.models.Authority;
import com.example.minorproject1.models.Student;
import com.example.minorproject1.models.User;
import com.example.minorproject1.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentControllers
{
    @Autowired
    StudentService studentService;
    @PostMapping() // similar to /user/signup
    public Integer createStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest)
    {
            return studentService.create(createStudentRequest);
    }

    /**
     * Admin can see any student's details
     * A student can see his/her details
     *
     */
    /**
     * Security Context on top level
     *
     * @param studentId
     * @param requireBookList
     * @return
     * @throws Exception
     */

    @GetMapping("/admin/{studentId}") // needs to have an authority of admin
    public GetStudentDetailsResponse getStudentDetailsForAdmin(
            @PathVariable("studentId") Integer studentId,
            @RequestParam(value = "require-book-list",required = false,
                    defaultValue = "false") boolean requireBookList) throws Exception {
        SecurityContext securityContext=SecurityContextHolder.getContext();
        Authentication authentication= securityContext.getAuthentication();
        User user= (User) authentication.getPrincipal();
//        if(user.getAuthorities().contains(Authority.admin))
//        {
//            throw new Exception("user is not an admin");
//
//        }
//        if(user.getAdmin()==null || user.getStudent()!=null)
//        {
//            throw new Exception("user is not an admin");
//        }


        return this.studentService.getStudentDetailsResponse(studentId,requireBookList);
    }
    //something a student will be able to see auth:student
    @GetMapping("")
    public GetStudentDetailsResponse getstudentDetails
            (
                    //@PathVariable("studentId") int id,
             @RequestParam(value = "require-book-list",required = false,
                     defaultValue = "false") boolean requireBookList) throws Exception {
        /**
         * Context holder: A container with multiple contexts
         *  .getAuthentication: gives current object
         *  .principle (): fetch the details
         *
         */
        SecurityContext securityContext=SecurityContextHolder.getContext();
       Authentication authentication= securityContext.getAuthentication();
       User user= (User) authentication.getPrincipal();
       Student student=user.getStudent();
       Integer studentId=null;
       if(student!=null)
       {
           studentId=student.getId();
       }
       else {
           throw new Exception("User is not a student");
       }
        return this.studentService.getStudentDetailsResponse(studentId,requireBookList);

    }
    @PatchMapping("")
    public GetStudentDetailsResponse updateStudentDetails(
             @Valid @RequestBody UpdateStudentRequest updateStudentRequest) throws Exception {
        SecurityContext securityContext=SecurityContextHolder.getContext();
        Authentication authentication= securityContext.getAuthentication();
        User user= (User) authentication.getPrincipal();
        Student student=user.getStudent();
        Integer studentId=null;
        if(student!=null)
        {
            studentId=student.getId();
        }
        else {
            throw new Exception("User is not a student");
        }
    return this.studentService.update(studentId,updateStudentRequest);
    }
    @DeleteMapping("")
    public GetStudentDetailsResponse DeactivateService() throws Exception {
        SecurityContext securityContext=SecurityContextHolder.getContext();
        Authentication authentication= securityContext.getAuthentication();
        User user= (User) authentication.getPrincipal();
        Student student=user.getStudent();
        Integer studentId=null;
        if(student!=null)
        {
            studentId=student.getId();
        }
        else {
            throw new Exception("User is not a student");
        }
        return this.studentService.deactivate(studentId);
    }
}
