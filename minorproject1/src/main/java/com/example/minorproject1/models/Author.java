package com.example.minorproject1.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author
{
    // JPA's unidirectional relationship
    // bidirectional relationship 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique=true)
    private String email;
    @CreationTimestamp
    private Date createdOn;

}
