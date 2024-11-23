package com.bon.apetitum.demo.controller;

import com.bon.apetitum.demo.FoodAPIs;
import com.bon.apetitum.demo.FoodData;
import com.bon.apetitum.demo.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/actions")
public class FoodController {

    @GetMapping("/menu-today/{menser}")
    public String getMenu(@PathVariable String menser, Model model) throws IOException {
        LocalDate currentDate = LocalDate.now(); // Get the default week fields based on the locale
        WeekFields weekFields = WeekFields.of(Locale.getDefault()); // Extract the week of the year and the year
        int weekOfYear = currentDate.get(weekFields.weekOfWeekBasedYear());
        int year = currentDate.get(weekFields.weekBasedYear());

        String test = FoodAPIs.getWeeklyFoodFromAPI(weekOfYear, year, menser);
        var response = FoodAPIs.getMenu(test);

        var foodData = FoodData.mapToFoodData(response.getDays()[0].getDishes());
        model.addAttribute("rows", foodData);
        //return new ResponseEntity<Iterable<FoodData>>(List.of(foodData), HttpStatus.OK);
        return "fragments/core :: table";
    }
}
