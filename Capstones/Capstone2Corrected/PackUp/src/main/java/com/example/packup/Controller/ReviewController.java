package com.example.packup.Controller;


import com.example.packup.Api.ApiResponse;
import com.example.packup.Model.Review;
import com.example.packup.Service.ReviewService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
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
    public ResponseEntity<?> createReview(@RequestBody @Valid Review review, Errors errors){
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        int created = reviewService.createReview(review);
        if(created == -1){
            return ResponseEntity.status(400).body(new ApiResponse("No Booking with this id"));
        }
        if(created == -2){
            return ResponseEntity.status(400).body(new ApiResponse("No User with this id"));
        }
        if(created == -3){
            return ResponseEntity.status(400).body(new ApiResponse("Your id is incorrect"));
        }
        if(created == -4){
            return ResponseEntity.status(400).body(new ApiResponse("You can only review a completed booking"));
        }
        if(created == -5){
            return ResponseEntity.status(400).body(new ApiResponse("reviewer id is the same as reviewee id"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("review posted successfully"));
    }

    @PutMapping("/updateReview/{id}")
    public ResponseEntity<?> updateReview(@PathVariable Integer id,@RequestBody @Valid Review review, Errors errors){
        if(errors.hasErrors()){
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        int updated = reviewService.updateReview(id,review);
        if(updated == -1){
            return ResponseEntity.status(400).body(new ApiResponse("No Review with this id"));
        }
        if(updated == -2){
            return ResponseEntity.status(400).body(new ApiResponse("No Booking with this id"));
        }
        if(updated == -3){
            return ResponseEntity.status(400).body(new ApiResponse("No User with this id"));
        }
        if(updated == -4){
            return ResponseEntity.status(400).body(new ApiResponse("Your id is incorrect"));
        }
        if(updated == -5){
            return ResponseEntity.status(400).body(new ApiResponse("you didn't write this review"));
        }
        if(updated == -6){
            return ResponseEntity.status(400).body(new ApiResponse("reviewer id is the same as reviewee id"));
        }

        return ResponseEntity.status(200).body(new ApiResponse("review updated successfully"));

    }

    @DeleteMapping("/deleteReview/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer id){
        if(reviewService.deleteReview(id)){
            return ResponseEntity.status(200).body(new ApiResponse("review deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("No review with this Id"));
    }

    @GetMapping("/getAvgRating/{id}")
    public ResponseEntity<?> userAvgRatings(@PathVariable Integer id){
        Double avg= reviewService.userAvgRatings(id);
        if(avg == null){
            return ResponseEntity.status(400).body(new ApiResponse("No user with this Id"));
        }
        return ResponseEntity.status(200).body(avg);

    }

}
