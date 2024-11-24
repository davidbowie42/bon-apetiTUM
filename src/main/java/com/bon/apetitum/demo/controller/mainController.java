package com.bon.apetitum.demo.controller;

import com.bon.apetitum.demo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

@Controller
@RequestMapping(path = "/")
public class mainController {
    /*
     * The landing page; it checks if there is a cookie, if there is it returns the
     * dashboard if not
     * It will return the login page. "login" means login.html, Thymeleaf
     * automatically associates it with
     * template names
     */
    @GetMapping()
    String landing(@CookieValue(value = "MyAuth", defaultValue = "null") String authCookie,
                   HttpServletResponse response, Model model) {
        if (authCookie.equals("1")) {
            // Redirect
            response.setHeader("Location", "/dashboard");
            response.setStatus(302);
        }
        return "login";
    }

    @PostMapping("/login")
        // Used @ModelAttribute to handle formData type requests
    void login(HttpServletResponse response, @ModelAttribute LoginDto formData) {
        // Validate password, check user and all that jazz here,
        // ...
        // Then set a cookie for the client
        Cookie cookie = new Cookie("MyAuth", "1"); // Hard coded value, yours should be dynamic
        response.addCookie(cookie);
        // Then redirect to the dashboard
        response.setHeader("Location", "/dashboard");
        response.setStatus(302);
    }

    @GetMapping(path = "/dashboard")
    String dashboardPage(@CookieValue(value = "MyAuth", defaultValue = "null") String authCookie, Model model) {
        if (authCookie.equals("null"))
            return "login";
        // Note: Do proper cookie verfication here.
        // I am just checking a hard coded value, you should have other methods
        if (!authCookie.equals("1"))
            return "login";
        // Create the objects to be rendered on the template //TODO call the API
        Dish[] dishes = {
                //   new FoodItem(1, "Frühlingsrolle", "Yum yum", 10),
                //  new FoodItem(2, "Studitopf", "Lecker", 5),
        };
        // Inject the object to the page with the rows variable
        model.addAttribute("rows", dishes);
        // Return the rendered page
        return "dashboard";
    }

    @PostMapping("/add")
    String addNewItem(@ModelAttribute AddNewDto formData, Model model) {
        /*
         * Here I just received the request, and returned the row fragment
         * that contains the request. You can do your logic here like inserting to a
         * database..etc
         */
        AddNewDto returnObject = formData;
        model.addAttribute("item", returnObject);
        return "fragments/core :: row";
    }

    @GetMapping("/edit-form")
    String getFormFields(@RequestParam String id, @RequestParam String name, @RequestParam String desc, Model model) {
        /*
         * Here I am return the editform fragment, it will be rendered in the modal when
         * the edit button is clicked
         */
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        model.addAttribute("desc", desc);
        return "fragments/core :: editform";
    }

    @PostMapping("/edit")
    String editItem(@ModelAttribute AddNewDto formData, Model model) {
        /*
         * Here I just received the edit request, and returned the row fragment
         * that contains the request. You can do your logic here like SQL updating
         */
        AddNewDto returnObject = formData;
        model.addAttribute("item", returnObject);
        return "fragments/core :: row";
    }

    @DeleteMapping("/delete")
    @ResponseBody
        // I used this decorater to tell spring that I am not returning an HTML template
    void deleteItem(@RequestParam String id) {
        // I return nothing when delete is being requested, it auto responds with status
        // 200
        System.out.println("Delete");
    }
    @GetMapping("/aboutUs")
    String aboutUs(@ModelAttribute AddNewDto formData, Model model) {
        /*
         * I copy pasted this and use it for the sidebar -woof
         */
        AddNewDto returnObject = formData;
        model.addAttribute("item", returnObject);
        return "aboutUs";
    }
    @GetMapping("/weekPlan")
    String weekPlan(@ModelAttribute AddNewDto formData, Model model) {
        /*
         * I copy pasted this and use it for the sidebar -woof
         */
        AddNewDto returnObject = formData;
        model.addAttribute("item", returnObject);
        return "weekPlan";
    }
    @GetMapping("/leaderboard")
    String leaderboard(@ModelAttribute AddNewDto formData, Model model) {
        /*
         * I copy pasted this and use it for the sidebar -woof
         */
        AddNewDto returnObject = formData;
        model.addAttribute("item", returnObject);
        return "leaderboard";
    }


    @GetMapping("/refresh")
    String refreshTable(Model model) {
        FoodApiResponse response;
        // Here I just return all the values, you will probablt fetch this from the
        // database
        /*Dish[] dishes = {
            //    new FoodItem(1, "Frühlingsrolle", "Yum yum", 10),
             //   new FoodItem(2, "Studitopf", "Lecker", 5),
        };*/
        // week year menser
        LocalDate currentDate = LocalDate.now(); // Get the default week fields based on the locale
        WeekFields weekFields = WeekFields.of(Locale.getDefault()); // Extract the week of the year and the year
        int weekOfYear = currentDate.get(weekFields.weekOfWeekBasedYear());
        System.out.println(weekOfYear);
        int year = currentDate.get(weekFields.weekBasedYear());
        System.out.println(year);


        //TODO give user the option to choose a menser
        String menser = "mensa-garching";
        try {
            //response = FoodAPIs.getMenuDirectly(weekOfYear, year, menser);
            String test= FoodAPIs.getWeeklyFoodFromAPI(weekOfYear,year,menser);
            System.out.println(test);
            response=FoodAPIs.getMenu(test);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        FoodDay[] foodDays = response.getDays();
        //scheinbar wird eh nur der aktuellste Tag dieser Woche im json angezeigt?
        Dish[] dishes = foodDays[0].getDishes();
        model.addAttribute("rows", dishes);
        return "fragments/core :: table";
    }

    @PostMapping("/logout")
    @ResponseBody
    void logOut(HttpServletResponse response) {
        // Removing the cookies on logout
        Cookie cookie = new Cookie("MyAuth", null);
        cookie.setMaxAge(0); // unsetting a cookie
        response.addCookie(cookie);
        response.setHeader("HX-Redirect", "/"); // Let HTMX know to redirect
    }
}