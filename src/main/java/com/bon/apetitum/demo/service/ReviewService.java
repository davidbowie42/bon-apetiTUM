package com.bon.apetitum.demo.service;


import com.bon.apetitum.demo.entity.Review;
import com.bon.apetitum.demo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review save(Review newReview) {

        return reviewRepository.save(newReview);
    }

    public Iterable<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review findById(int reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    public void deleteById(int reviewId) {
        try {
            reviewRepository.deleteById(reviewId);
        }
        catch (Exception e) {
            System.err.println("Unable to delete Review with ID: " + reviewId);
        }
    }

    public Iterable<Review> findReviewsForFoodId(int foodId)
    {
        return reviewRepository.findReviewsForFoodId(foodId);
    }
}
