package com.example.minorproject1.repository;

import com.example.minorproject1.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Integer>
{


    Author findByEmail(String email);
}
