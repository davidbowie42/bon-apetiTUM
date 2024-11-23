package com.bon.apetitum.demo;


import java.io.IOException;

public class FoodData {

    public int id;

    public String name;
    public Price prices;
    public FoodLabel[] labels;

    public String dish_type;

    public int rating;
    public int recentRating;

    public FoodData(String name, String dish_type, Price prices, String[] labels) {
        this.name = name;
        this.dish_type = dish_type;
        this.prices = prices;
    }

    public FoodData() {
    }

    public static FoodData[] mapToFoodData(Dish[] dishes) throws IOException {
        FoodData[] foodData = new FoodData[dishes.length];

        var labelMap = FoodAPIs.getFoodLabels();

        for (int i = 0; i < dishes.length; i++) {
            FoodData foodDataItem = new FoodData();

            foodDataItem.setId(dishes[i].getId());
            foodDataItem.setName(dishes[i].getName());
            foodDataItem.setDish_type(dishes[i].getDish_type());
            foodDataItem.setPrices(dishes[i].getPrices());

            if (labelMap != null) {
                var labels = new FoodLabel[dishes[i].getLabels().length];
                for (int j = 0; j < dishes[i].getLabels().length; j++) {
                    labels[j] = labelMap.get(dishes[i].getLabels()[j]);
                }
                foodDataItem.setLabels(labels);
            }


            foodData[i] = foodDataItem;
        }

        return foodData;
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

    public FoodLabel[] getLabels() {
        return labels;
    }

    public void setLabels(FoodLabel[] labels) {
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

    public int getRating()
    {
        return rating;
    }

    public void setRating(int rating)
    {
        this.rating = rating;
    }

    public int getRecentRating()
    {
        return recentRating;
    }

    public void setRecentRating(int recentRating)
    {
        this.recentRating = recentRating;
    }
}
