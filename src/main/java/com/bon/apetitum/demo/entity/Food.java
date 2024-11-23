package com.bon.apetitum.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

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
    private Set<User> users;

    @ElementCollection(targetClass = Category.class)
    @Enumerated(EnumType.STRING)
    private List<Category> categories;
    // Getters and setters
    public Food() {
    }

    public Food(String name, int rating, int nRating, int recentRating, int nRecentRating, List<Category> categories) {
        this.name = name;
        this.rating = rating;
        this.nRating = nRating;
        this.recentRating = recentRating;
        this.nRecentRating = nRecentRating;
        this.categories = categories;
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

    public Set<User> getUsers()
    {
        return users;
    }

    public void setUsers(Set<User> users)
    {
        this.users = users;
    }

    public List<Category> getCategories()
    {
        return categories;
    }

    public void setCategories(List<Category> categories)
    {
        this.categories = categories;
    }
}