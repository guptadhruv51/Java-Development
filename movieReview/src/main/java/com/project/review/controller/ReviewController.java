package com.project.review.controller;

import com.project.review.DTO.ReviewRequest;
import com.project.review.DTO.ReviewResponse;
import com.project.review.service.ReviewService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("review/")
public class ReviewController
{
    @Autowired
    ReviewService reviewService;

    @PostMapping("add")
    public ResponseEntity<String> addReview(@RequestBody ReviewRequest reviewRequest)
    {
        reviewService.addReview(reviewRequest);
        return new ResponseEntity<>("Review added succesfully", HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ReviewResponse getReview(@RequestParam Long reviewId){
        return reviewService.getReviewById(reviewId);
    }
}
