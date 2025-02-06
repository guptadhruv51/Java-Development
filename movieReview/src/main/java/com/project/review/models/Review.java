package com.project.review.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Review
{

        @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

        private String movieReview;

        private double ratings;

        @ManyToOne
        @JoinColumn(name="movie_id")
        @JsonIgnore
        private Movie movie;
        @CreationTimestamp
        private Date createdDate;
        @UpdateTimestamp
        private Date updatedDate;


}
