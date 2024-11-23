package com.bon.apetitum.demo;

import java.util.Date;

public class FoodDay {
    private Date date;
    private Dish[] dishes;

    public FoodDay(Date date, Dish[] dishes) {
        this.date = date;
        this.dishes = dishes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Dish[] getDishes() {
        return dishes;
    }

    public void setDishes(Dish[] dishes) {
        this.dishes = dishes;
    }

    public FoodDay() {
    }
}
