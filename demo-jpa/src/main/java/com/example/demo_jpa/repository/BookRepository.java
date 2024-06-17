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
 * It also has a @Repository annotation, hence we don't need to write it
 * Jpa Repository is implemented by SimpleJpaRepo: Which is a generic class.
 */

public interface BookRepository extends JpaRepository <Book,Integer>
{


}
