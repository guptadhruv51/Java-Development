package com.project.review.service;

import com.project.review.DTO.ReviewRequest;
import com.project.review.DTO.ReviewResponse;
import com.project.review.models.Movie;
import com.project.review.models.Review;
import com.project.review.repository.MovieRepo;
import com.project.review.repository.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService
{
    @Autowired
    ReviewRepo reviewRepo;
    @Autowired
    MovieRepo movieRepo;
    public void addReview(ReviewRequest reviewRequest)
    {
        Review review=reviewRequest.toReview();
        Movie movie=movieRepo.findById(review.getMovie().getId()).orElse(null);
        reviewRepo.save(review);
        if(movie!=null)
        {
            Double average=reviewRepo.getReviewAvg(movie.getId());
            movie.setRating(average);
            movieRepo.save(movie);
        }
    }

    public ReviewResponse getReviewById(Long reviewId)
    {
        Review review=reviewRepo.findById(reviewId).orElse(null);
        return 
    }
}
