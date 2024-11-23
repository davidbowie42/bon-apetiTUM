package com.bon.apetitum.demo.entity;

import jakarta.persistence.*;
import java.util.Date;
import jakarta.validation.constraints.Positive;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User author;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Food food;
    @Positive
    private int rating;
    private Date date;
    private String reviewText;

    // Getters and setters

    public Review(User author, Food food, int rating, Date date, String reviewText)
    {
        this.author = author;
        this.food = food;
        this.rating = rating;
        this.date = date;
        this.reviewText = reviewText;
    }

    public Review()
    {

    }

    public int getReviewId()
    {
        return reviewId;
    }

    public User getAuthor()
    {
        return author;
    }

    public Food getFood()
    {
        return food;
    }

    public int getRating()
    {
        return rating;
    }

    public Date getDate()
    {
        return date;
    }

    public String getReviewText()
    {
        return reviewText;
    }

}
