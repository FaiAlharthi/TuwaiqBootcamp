package com.example.packup.Service;

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
    public int createReview(Review review){
        if(bookingRepository.findBookingById(review.getBookingId())==null){
            return -1;
        }
        if(userRepository.findUserById(review.getRevieweeId())==null){
            return -2;
        }
        if(userRepository.findUserById(review.getReviewerId())==null){
            return -3;
        }
        Booking booked= bookingRepository.findBookingById(review.getBookingId());
        if(!booked.getStatus().equalsIgnoreCase("completed")){
            return -4;
        }
        if(review.getRevieweeId().equals(review.getReviewerId())){
            return -5;
        }

        reviewRepository.save(review);
        return 0;
    }

    //UPDATE
    public int updateReview(Integer id, Review review) {
        Review review1 = reviewRepository.findReviewById(id);
        if (review1 == null) {
            return -1;
        }
        if(bookingRepository.findBookingById(review.getBookingId())==null){
            return -2;
        }
        if(userRepository.findUserById(review.getRevieweeId())==null){
            return -3;
        }
        if(userRepository.findUserById(review.getReviewerId())==null){
            return -4;
        }
        if(! (reviewRepository.findReviewById(id).getReviewerId().equals(review.getReviewerId())) ){
            return -5;
        }
        if(review.getRevieweeId().equals(review.getReviewerId())){
            return -6;
        }

        review1.setBookingId(review.getBookingId());
        review1.setReviewerId(review.getReviewerId());
        review1.setRevieweeId(review.getRevieweeId());
        review1.setRating(review.getRating());
        review1.setComment(review.getComment());
        reviewRepository.save(review1);
        return 0;
    }

    //DELETE
    public boolean deleteReview(Integer id){
        Review review= reviewRepository.findReviewById(id);
        if(review == null){
            return false;
        }
        reviewRepository.delete(review);
        return true;
    }

    //calculate user rating avg as renter 5/15
    public Double userAvgRatings(Integer userId){
        if(userRepository.findUserById(userId) == null){
            return null;
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
