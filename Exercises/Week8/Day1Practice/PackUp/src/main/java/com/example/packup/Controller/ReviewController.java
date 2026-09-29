package com.example.packup.Controller;


import com.example.packup.Api.ApiResponse;
import com.example.packup.Model.Review;
import com.example.packup.Service.ReviewService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
    private final ReviewService reviewService;

    //CRUD
    @GetMapping("/allReviews")
    public ResponseEntity<?> getAllReviews (){
        if(reviewService.getAllReviews().isEmpty()){
            return ResponseEntity.status(200).body(new ApiResponse("No reviews"));
        }
        return ResponseEntity.status(200).body(reviewService.getAllReviews());
    }

    @PostMapping("/createReview")
    public ResponseEntity<?> createReview(@RequestBody @Valid Review review){
        reviewService.createReview(review);
        return ResponseEntity.status(200).body(new ApiResponse("review posted successfully"));
    }

    @PutMapping("/updateReview/{id}")
    public ResponseEntity<?> updateReview(@PathVariable Integer id,@RequestBody @Valid Review review){
        reviewService.updateReview(id,review);
        return ResponseEntity.status(200).body(new ApiResponse("review updated successfully"));

    }

    @DeleteMapping("/deleteReview/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer id){
        reviewService.deleteReview(id);
        return ResponseEntity.status(200).body(new ApiResponse("review deleted successfully"));
    }

    @GetMapping("/getAvgRating/{id}")
    public ResponseEntity<?> userAvgRatings(@PathVariable Integer id){
        Double avg= reviewService.userAvgRatings(id);
        return ResponseEntity.status(200).body(avg);

    }

}
