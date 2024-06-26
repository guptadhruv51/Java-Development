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
//hibernate_sequence is the table generated by hibernate for maintaining auto incremented ids
// sequence is a global id can be used for any table
public class Book
{
    @Id //jakarta.persistence (primary key)
    @GeneratedValue(strategy = GenerationType.AUTO)
    //auto: Hibernate will generate and has to be fetched by hibernate
    // 3 network calls+internal db calls
    //irregular sequencing
    //if we manually update, a discrepancy will be created in the hibernate_sequence
    // Major problem (latency): Insertions at the same time will not be possible because of the lock on the hibernate_sequence table
    //UUID: Generation type
    //identity: MYSQL or any underlying datastore will generate and will be individual for a table (auto-increment)
    // 1 network call+network db calls
    //regular sequencing
    // can manually update and will not cause problem
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