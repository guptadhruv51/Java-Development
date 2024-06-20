package com.example.minorproject1.service;

import com.example.minorproject1.dtos.CreateStudentRequest;
import com.example.minorproject1.dtos.GetStudentDetailsResponse;
import com.example.minorproject1.dtos.UpdateStudentRequest;
import com.example.minorproject1.models.Book;
import com.example.minorproject1.models.Student;
import com.example.minorproject1.models.StudentStaus;
import com.example.minorproject1.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class StudentService
{
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    BookService bookService;
    public GetStudentDetailsResponse getStudentDetailsResponse(Integer id,boolean requireBookList)
    {
        Student student=studentRepository.findById(id).orElse(null);
        List<Book> bookList=null;
        if(requireBookList)
        {
            bookList=this.bookService.getBooksByStudentId(id);
        }
        //List<Book> bookList=this.bookService.getBooksByStudentId(id);
        return GetStudentDetailsResponse.builder().student(student)
                        .bookList(bookList)
                .build();
    }


    public Integer create(CreateStudentRequest createStudentRequest)
    {
        Student student =createStudentRequest.to();
        student=this.studentRepository.save(student); //taking the id from the saved object in database
        //in Spring 3.0, JPA automatically returns the id from the database
        return student.getId();
    }
    public GetStudentDetailsResponse update(Integer id, UpdateStudentRequest request)
    {
        Student student=request.to();
        GetStudentDetailsResponse studentresponse=getStudentDetailsResponse(id,false);
       Student savedStudent=studentresponse.getStudent();
       Student target=this.merge(student, savedStudent);
       this.studentRepository.save(student);
       studentresponse.setStudent(target);
       return studentresponse;
    }

    private Student merge(Student incoming, Student saved)
    {
        JSONObject incomingStudent =mapper.convertValue(incoming, JSONObject.class);
        JSONObject savedStudent=mapper.convertValue(saved, JSONObject.class);
        Iterator it=incomingStudent.keySet().iterator();
        while(it.hasNext())
        {
            String key=(String)it.next();
            if(incomingStudent.get(key)!=null)
            {
                savedStudent.put(key,incomingStudent.get(key));
            }
        }
        return mapper.convertValue(savedStudent,Student.class);
    }

    public GetStudentDetailsResponse deactivate(Integer id)
    {
         this.studentRepository.deactivate(id, StudentStaus.Inactive);
         return this.getStudentDetailsResponse(id,false);
    }
}
