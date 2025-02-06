package com.project.review.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Movie implements Serializable
{
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private Double rating;

    @OneToMany(mappedBy = "movie")
    private List<Review> reviewList;

}
