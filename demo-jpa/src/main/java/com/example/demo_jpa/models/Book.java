package com.example.demo_jpa.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter // Lombok adding getter to the class automatically
@Setter // Can put this annotation on individual members as well, so that we can create special setters and getters
// Can put it on class level or attribute level
@NoArgsConstructor // Default constructor useful when we have another parametrized constructor
@AllArgsConstructor
@ToString
@Builder // allows us to create and assign the value in a single line
// creates an internal class, imagine the setters returning this
@Entity //jakarta.persistence api, same name as the class name for table
@Table(name= "my_book")
public class Book
{
    @Id //jakarta.persistence (primary key)
    @GeneratedValue(strategy = GenerationType.AUTO)
    //auto
    //UUID
    //identity
    private int id; // auto incremented field
    @Column(name="book_name",nullable = false,length = 50)
    private String name;
    @Enumerated()     // convert genre(enum) to a datatype
    private Genre genre;
    private String authorName;
    private String authorCountry;
    private String authorEmail;
    @CreationTimestamp //hibernate
    private Date createdOn;
    @UpdateTimestamp //hibernate
    private Date updatedOn;


}