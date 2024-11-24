package com.bon.apetitum.demo.controller;

import com.bon.apetitum.demo.entity.Food;
import com.bon.apetitum.demo.entity.MensaUser;
import com.bon.apetitum.demo.entity.Review;
import com.bon.apetitum.demo.service.FoodService;
import com.bon.apetitum.demo.service.MensaUserService;
import com.bon.apetitum.demo.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping(path = "/actions")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MensaUserService userService;

    @Autowired
    private FoodService foodService;

    @GetMapping("/newReview/{foodId}/{rating}/{comment}")
    public ResponseEntity<?> newReview(@PathVariable int foodId, @PathVariable int rating, @PathVariable String comment, @CookieValue(value = "MyAuth", defaultValue = "null") String authCookie) {
        if (authCookie.equals("null")) {
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        } else {
            // Extract user ID from the authCookie
            int userId = Integer.parseInt(authCookie);

            // Fetch the user entity
            MensaUser user = userService.findById(userId);
            if (user == null) {
                return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
            }

            // Fetch the food entity
            Food food = foodService.findById(foodId);
            if (food == null) {
                return new ResponseEntity<String>("Food not found", HttpStatus.NOT_FOUND);
            }

            // Create the new review
            Review newReview = new Review(user, food, rating, new Date(), comment);
            reviewService.save(newReview);

            int totalRating = food.getRating() * food.getnRating();
            int totalRecentRating = food.getRecentRating() * food.getnRecentRating();

            food.setRating((totalRating + 20 * rating) / (food.getnRating() + 1));
            food.setRecentRating((totalRecentRating + 20 * rating) / (food.getnRecentRating() + 1));
            food.setnRating(food.getnRating() + 1);
            food.setnRecentRating(food.getnRecentRating() + 1);
            foodService.save(food);

            return new ResponseEntity<String>("Review added", HttpStatus.OK);
        }
    }

    @GetMapping("/foodReviews/{foodId}")
    public ResponseEntity<?> findReviewsForFoodId(@PathVariable int foodId) {
        return new ResponseEntity<Iterable<Review>>(reviewService.findReviewsForFoodId(foodId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteReview/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable int reviewId) {
        reviewService.deleteById(reviewId);
        return new ResponseEntity<String>("Review deleted", HttpStatus.OK);
    }

    @PutMapping("/updateReview/{reviewId}/{rating}/{comment}")
    public ResponseEntity<?> updateReview(@PathVariable int reviewId, @PathVariable int rating, @PathVariable String comment) {
        Review review = reviewService.findById(reviewId);
        if (review == null) {
            return new ResponseEntity<String>("Review not found", HttpStatus.NOT_FOUND);
        }
        review.setRating(rating);
        review.setReviewText(comment);
        reviewService.save(review);
        return new ResponseEntity<String>("Review updated", HttpStatus.OK);
    }


}