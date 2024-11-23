package com.bon.apetitum.demo;

public class PersonPrice {
    //"base_price": 0,
    // "price_per_unit": 0.8,
    //"unit": "100g"
    private float base_price;
    private float price_per_unit;
    private String unit;

    public PersonPrice(float base_price, float price_per_unit, String unit) {
        this.base_price = base_price;
        this.price_per_unit = price_per_unit;
        this.unit = unit;
    }

    public float getBase_price() {
        return base_price;
    }

    public void setBase_price(float base_price) {
        this.base_price = base_price;
    }

    public float getPrice_per_unit() {
        return price_per_unit;
    }

    public void setPrice_per_unit(float price_per_unit) {
        this.price_per_unit = price_per_unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public PersonPrice() {
    }
}
