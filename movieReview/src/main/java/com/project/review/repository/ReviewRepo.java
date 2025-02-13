package com.project.review.repository;

import com.project.review.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepo extends JpaRepository<Review,Long>
{

    @Query(value = "select avg(ratings) from Review where movie_id=?",nativeQuery = true)
    Double getReviewAvg(Long id);
}
