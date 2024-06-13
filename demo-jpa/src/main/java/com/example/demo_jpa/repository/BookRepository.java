package com.example.demo_jpa.repository;

import com.example.demo_jpa.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * JpaRepository<T,ID>:
 * t-> name of model class.
 *
 * Interface: cannot have any object;
 * JpaRepository-->SimpleJpaRepository has a repository component and will thus create a bean
 */

public interface BookRepository extends JpaRepository <Book,Integer>
{

}
