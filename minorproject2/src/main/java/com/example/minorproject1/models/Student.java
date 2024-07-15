package com.example.minorproject1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String name;
    @Column(unique = true)
    private String email;
    @Column(unique = true,nullable = false)
    private String mobile;
    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;

    @OneToMany(mappedBy="student") //back reference (name of the data member in book class)
    @JsonIgnoreProperties("student")
    private List<Book> bookList;
    @OneToMany(mappedBy="student")
    @JsonIgnoreProperties("student")
    private List<Transactions> transactionsList;
    @Enumerated(value=EnumType.STRING)
    private StudentStaus status;

    @JoinColumn
    @OneToOne
    @JsonIgnoreProperties("student") // removes a particular property from the response
    @JsonIgnore // remove the user object from the response
    private User user;
}
