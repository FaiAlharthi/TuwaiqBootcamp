package com.example.packup.Repository;

import com.example.packup.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {
    Review findReviewById(Integer Id);
    List<Review> findReviewByRevieweeId(Integer Id);

    @Query("select r.rating from Review r where r.revieweeId=?1 ")
    List<Integer> userRatings(Integer Id);

}
