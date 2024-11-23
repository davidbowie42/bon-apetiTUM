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
    private MensaUser mensaUser;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Food food;
    @Positive
    private int rating;
    private Date date;
    private String reviewText;

    // Getters and setters

    public Review(MensaUser author, Food food, int rating, Date date, String reviewText)
    {
        this.mensaUser = author;
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

    public MensaUser getMensaUser()
    {
        return mensaUser;
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
