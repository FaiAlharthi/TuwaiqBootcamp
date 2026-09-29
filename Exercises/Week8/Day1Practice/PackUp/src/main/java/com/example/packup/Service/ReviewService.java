package com.example.packup.Service;

import com.example.packup.Api.ApiException;
import com.example.packup.Model.Booking;
import com.example.packup.Model.Review;
import com.example.packup.Repository.BookingRepository;
import com.example.packup.Repository.ReviewRepository;
import com.example.packup.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    //CRUD

    //READ
    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    //CREATE
    public void createReview(Review review){
        if(bookingRepository.findBookingById(review.getBookingId())==null){
            throw new ApiException("No Booking with this id");
        }
        if(userRepository.findUserById(review.getRevieweeId())==null){
            throw new ApiException("No User with this id");
        }
        if(userRepository.findUserById(review.getReviewerId())==null){
            throw new ApiException("Your id is incorrect");
        }
        Booking booked= bookingRepository.findBookingById(review.getBookingId());
        if(!booked.getStatus().equalsIgnoreCase("completed")){
            throw new ApiException("You can only review a completed booking");
        }
        if(review.getRevieweeId().equals(review.getReviewerId())){
            throw new ApiException("reviewer id is the same as reviewee id");
        }

        reviewRepository.save(review);
    }

    //UPDATE
    public void updateReview(Integer id, Review review) {
        Review review1 = reviewRepository.findReviewById(id);
        if (review1 == null) {
            throw new ApiException("No Review with this id");
        }
        if(bookingRepository.findBookingById(review.getBookingId())==null){
            throw new ApiException("No Booking with this id");
        }
        if(userRepository.findUserById(review.getRevieweeId())==null){
            throw new ApiException("No User with this id");
        }
        if(userRepository.findUserById(review.getReviewerId())==null){
            throw new ApiException("Your id is incorrect");
        }
        if(! (reviewRepository.findReviewById(id).getReviewerId().equals(review.getReviewerId())) ){
            throw new ApiException("you didn't write this review");
        }
        if(review.getRevieweeId().equals(review.getReviewerId())){
            throw new ApiException("reviewer id is the same as reviewee id");
        }

        review1.setBookingId(review.getBookingId());
        review1.setReviewerId(review.getReviewerId());
        review1.setRevieweeId(review.getRevieweeId());
        review1.setRating(review.getRating());
        review1.setComment(review.getComment());
        reviewRepository.save(review1);
    }

    //DELETE
    public void deleteReview(Integer id){
        Review review= reviewRepository.findReviewById(id);
        if(review == null){
            throw new ApiException("No review with this Id");
        }
        reviewRepository.delete(review);
    }

    //calculate user rating avg as renter 5/15
    public Double userAvgRatings(Integer userId){
        if(userRepository.findUserById(userId) == null){
            throw new ApiException("No user with this Id");
        }
        List<Integer>ratings = reviewRepository.userRatings(userId);
        Double sum =0.0;
        if(ratings.isEmpty())
            return 0.0;
        for(Integer rate: ratings){
            sum = sum+rate;
        }
        return (sum/ratings.size());
    }

}
