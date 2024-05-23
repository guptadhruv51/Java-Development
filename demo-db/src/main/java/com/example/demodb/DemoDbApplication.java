package com.example.demodb;

import com.example.demodb.models.Book;
import com.example.demodb.models.Genre;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoDbApplication.class, args);
//		Book book=new Book();
//		book.getId();
//
//		// Builder
//		// creates a book builder and then converts it into a book object
//		// creates a new object as well
//		Book bookFromBuilder= Book.builder().id(10).genre(Genre.Fictional).build();

		/**
		 * 1. Internally using paramterized constructor, but the developer doesn't have to write the boiler plate code
		 * 2. You don't have to pass the default values for non required attributes
		 */
	}

}
