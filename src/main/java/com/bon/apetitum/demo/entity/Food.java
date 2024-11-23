package com.bon.apetitum.demo.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int rating;
    private int nRating;

    private int recentRating;
    private int nRecentRating;

    @ManyToMany(mappedBy = "favoriteFoods")
    private Set<MensaUser> users;

    @OneToMany(mappedBy = "reviewId")
    private Set<Review> reviews;


    private String type;
    // Getters and setters
    public Food() {
    }

    public Food(String name, int rating, int nRating, int recentRating, int nRecentRating, String type) {
        this.name = name;
        this.rating = rating;
        this.nRating = nRating;
        this.recentRating = recentRating;
        this.nRecentRating = nRecentRating;
        this.type = type;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getRating()
    {
        return rating;
    }

    public void setRating(int rating)
    {
        this.rating = rating;
    }

    public int getnRating()
    {
        return nRating;
    }

    public void setnRating(int nRating)
    {
        this.nRating = nRating;
    }

    public int getRecentRating()
    {
        return recentRating;
    }

    public void setRecentRating(int recentRating)
    {
        this.recentRating = recentRating;
    }

    public int getnRecentRating()
    {
        return nRecentRating;
    }

    public void setnRecentRating(int nRecentRating)
    {
        this.nRecentRating = nRecentRating;
    }

    public Set<MensaUser> getUsers()
    {
        return users;
    }

    public void setUsers(Set<MensaUser> users)
    {
        this.users = users;
    }

    public Set<Review> getReviews()
    {
        return reviews;
    }

    public void setReviews(Set<Review> reviews)
    {
        this.reviews = reviews;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}