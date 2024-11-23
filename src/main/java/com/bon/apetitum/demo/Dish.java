package com.bon.apetitum.demo;
import jakarta.persistence.*;

//@Entity
//@Table(name = "Food")
public class Dish {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String name;
    public Price prices;
    public String[] labels;

    public String dish_type;

    public Dish(String name, String dish_type, Price prices, String[] labels) {
        this.name = name;
        this.dish_type = dish_type;
        this.prices = prices;
    }

    public Dish() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getPrices() {
        return prices;
    }

    public void setPrices(Price prices) {
        this.prices = prices;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public String getDish_type() {
        return dish_type;
    }

    public void setDish_type(String dish_type) {
        this.dish_type = dish_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}