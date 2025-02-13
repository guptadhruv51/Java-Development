package com.project.review.DTO;

import com.project.review.models.Movie;
import com.project.review.models.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest
{
    String movieReview;
    Double rating;
    Long movieId;

    public Review toReview()
    {
        return Review.builder().movieReview(this.movieReview).ratings(this.rating).
                movie(Movie.builder().id(movieId).build())
                .build();
    }

}
