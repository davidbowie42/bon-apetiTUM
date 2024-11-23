package com.bon.apetitum.demo.service;

import com.bon.apetitum.demo.*;
import com.bon.apetitum.demo.entity.Food;
import com.bon.apetitum.demo.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public Food save(Food newFood) {

        return foodRepository.save(newFood);
    }

    public Iterable<Food> findAll() {
        return foodRepository.findAll();
    }

    public Food findById(int foodId) {
        return foodRepository.findById(foodId).orElse(null);
    }

    public Food findByName(String foodName) {
        var res = foodRepository.findFoodByLabel(foodName);
        if (!res.isEmpty())
        {
            return res.get(0);
        }
        return null;
    }

    public void deleteById(int foodId) {
        try {
            foodRepository.deleteById(foodId);
        }
        catch (Exception e) {
            System.err.println("Unable to delete Food with ID: " + foodId);
        }
    }

}
