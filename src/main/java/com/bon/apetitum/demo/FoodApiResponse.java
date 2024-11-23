package com.bon.apetitum.demo;

public class FoodApiResponse {
    private int number;
    private int year;
    private FoodDay[] days;
    private String version;

    public FoodApiResponse(int number, int year, FoodDay[] days, String version) {
        this.number = number;
        this.year = year;
        this.days = days;
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public FoodDay[] getDays() {
        return days;
    }

    public void setDays(FoodDay[] days) {
        this.days = days;
    }

    public FoodApiResponse() {
    }
}
