package com.example.minorproject1.repository;

import com.example.minorproject1.models.Student;
import com.example.minorproject1.models.StudentStaus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository  extends JpaRepository<Student,Integer>
{

    @Modifying
    @Transactional
    @Query(value = "update student s set s.status=?2 where s.id=?1")
    void deactivate(int studentId, StudentStaus status);
}
