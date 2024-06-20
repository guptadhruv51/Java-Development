package com.example.minorproject1.models;

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
    private List<Book> bookList;
    @OneToMany(mappedBy="student")
    private List<Transactions> transactionsList;
    @Enumerated(value=EnumType.STRING)
    private StudentStaus status;
}
