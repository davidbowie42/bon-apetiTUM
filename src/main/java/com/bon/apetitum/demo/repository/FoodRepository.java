package com.bon.apetitum.demo.repository;

import com.bon.apetitum.demo.entity.Food;
import com.bon.apetitum.demo.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer>{
//    @Query("SELECT f FROM Food f WHERE f.name = ?1")
//    public List<Review> findFoodByLabel(String label);
@Query("SELECT f FROM Food f WHERE f.name = ?1")
public List<Food> findFoodByLabel(String label);
}