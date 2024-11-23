package com.bon.apetitum.demo.repository;

import com.bon.apetitum.demo.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{
    @Query("SELECT r FROM Review r, Food f WHERE r.food.id = f.id AND f.id = ?1")
    public List<Review> findReviewsForFoodId(int foodId);
}