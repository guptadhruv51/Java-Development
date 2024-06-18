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
public class Book
{
    //Book:Author (N:1)
    //jpa joins two entities related to each other. We can create relationships between them
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   // @GeneratedValue(strategy = GenerationType.UUID)
    private String externalTransactionId;
    @Enumerated(value=EnumType.STRING)
    private Genre genre;
    private String name;

    @ManyToOne // Many books can have one author
    // many: first word represents current class
    @JoinColumn  //foreign key creation
    private Author author;
    @ManyToOne
    @JoinColumn // will make sure there is a foreign key in book table with a student id
    private Student student;
    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;

    @OneToMany(mappedBy="book") // in transaction class data member is book
    private List<Transactions> transactionsList;

}
