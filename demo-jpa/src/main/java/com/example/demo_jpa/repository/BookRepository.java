package com.example.demo_jpa.repository;

import com.example.demo_jpa.models.Book;
import com.example.demo_jpa.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

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

    //custom query
    //Query: convert a query into an executable function
    //@Query(value = "select * from my_book where genre=?1",nativeQuery = true) // abstraction given by JPA to the end user to write custom queries
    // or @Query("select * from my_book where genre=:genre(name of argument)")
    //@Query("select b from Book b where b.genre=:genre") // Book : java object
    //List<Book> findbyGenre(Genre genre);

    // Why does hibernate convert camel case to underscore not only in column name but also in table name?

    /**
     * Different formats:
     * JPQL: Java persistence query language
     * Format where we write queries keeping java objects in mind.
     * Java gives an error if the query is wrong
     * Conversion time is associated: slightly slower
     * Abstraction from the underlying table
     *
     * Native Query:
     * Format where we write queries keeping sql tables in mind.
     * Java can't check the query during compile time
     * No conversion time is associated
     * Need to be aware of table schema and structure
     */

    @Query("select b from Book b where b.authorEmail=:authorEmail")
    List<Book> findByAuthorJPQL(String authorEmail);
    @Query(value = "Select * from my_book b where b.author_email=:authorEmail",nativeQuery = true)
    List<Book> findByAuthorNative(String authorEmail);

    List<Book> findByGenre(Genre genre); // will automatically be converted to a query when we write findBy(Java Class datamember)
    // JPA naming convention
    // findByGenreLike
    List<Book> findByAuthorEmail(String authorEmail);
    List <Book> findByGenreOrAuthorEmail(Genre genre, String authorEmail);
}
