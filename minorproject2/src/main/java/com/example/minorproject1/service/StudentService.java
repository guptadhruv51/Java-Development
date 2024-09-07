package com.example.minorproject1.service;

import com.example.minorproject1.dtos.CreateStudentRequest;
import com.example.minorproject1.dtos.GetStudentDetailsResponse;
import com.example.minorproject1.dtos.UpdateStudentRequest;
import com.example.minorproject1.models.Authority;
import com.example.minorproject1.models.Student;
import com.example.minorproject1.models.StudentStaus;
import com.example.minorproject1.models.User;
import com.example.minorproject1.repository.StudentCacheRepository;
import com.example.minorproject1.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class StudentService
{

    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentCacheRepository studentCacheRepository;
    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;
    public GetStudentDetailsResponse getStudentDetailsResponse(Integer id,boolean requireBookList)
    {

        //Cache Logic
        Student student=this.studentCacheRepository.getfromCache(id);
        if(student!=null)
        {
            return GetStudentDetailsResponse.builder().student(student)
                    .bookList(student.getBookList())
                    .build();
        }

        student=studentRepository.findById(id).orElse(null);
//        List<Book> bookList=null;
//        if(requireBookList)
//        {
//            bookList=this.bookService.getBooksByStudentId(id);
//        }
        //List<Book> bookList=this.bookService.getBooksByStudentId(id);
//        assert student != null;

        // Cache Miss

        this.studentCacheRepository.add(student);
        return GetStudentDetailsResponse.builder().student(student)
                        .bookList(student.getBookList())
                .build();
    }


    public Integer create(CreateStudentRequest createStudentRequest)
    {
        /**
         * 1. To create a user (encode the password, attach the authorities)
         * 2. Create user
         * 3. Attach the userid with the student (foreign key reference)
         */
        Student student=createStudentRequest.to();
        User user=this.userService.createUser(student.getUser(), Authority.student);
        student.setUser(user);
        this.studentRepository.save(student);
        return student.getId();
//        Student student =createStudentRequest.to();
//        student=this.studentRepository.save(student); //taking the id from the saved object in database
//        //in Spring 3.0, JPA automatically returns the id from the database
//        return student.getId();
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
