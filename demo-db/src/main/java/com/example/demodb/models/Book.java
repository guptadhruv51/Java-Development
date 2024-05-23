package com.example.demodb.models;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Getter // Lombok adding getter to the class automatically
@Setter // Can put this annotation on individual members as well, so that we can create special setters and getters
// Can put it on class level or attribute level
@NoArgsConstructor // Default constructor useful when we have another parametrized constructor
@AllArgsConstructor
@ToString
@Builder // allows us to create and assign the value in a single line
// creates an internal class, imagine the setters returning this
public class Book
{
    private int id; // auto incremented field
    private String name;
    private Genre genre;
    private String authorName;
    private String authorCountry;
    private String authorEmail;
    private Date createdOn;
    private Date updatedOn;


}
