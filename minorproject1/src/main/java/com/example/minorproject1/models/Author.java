package com.example.minorproject1.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.util.*;
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
    @OneToMany(mappedBy = "author") //attribute name of book class author or the name for forward direction FK attribute
    private List<Book> bookList; //bidirectional relationship (tightly coupled)

    /**
     * Pros and Cons of bidirectional relationship
     * Pro:
     *  Hibernate will internally make a call to the book table
     *  and fetch the book details and attach it with the author object
     *
     * Cons:
     * Time taken will be more causing latency issues
     *  Cyclic dependency:
     *  If not handled dependent attributes properly in the returned response,
     *  it can lead to stack overflow or infinite loop
     *
     *
     *  book-->Author
     *  Author->Book
     *  infinite loop
     *
     */

}
