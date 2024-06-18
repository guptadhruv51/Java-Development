package com.example.minorproject1.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.IdentityHashMap;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Transactions
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn
    private Student student;
    @ManyToOne
    @JoinColumn
    private Book book;

    private TransactionStatus transactionStatus;
    @UpdateTimestamp
    private Date updatedOn;
    @CreationTimestamp
    private Date createdOn;

}
