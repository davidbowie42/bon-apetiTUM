package com.bon.apetitum.demo.entity;

import jakarta.persistence.*;
import java.util.Set;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@Entity
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @Email
    private String email;

    @ManyToMany
    @JoinTable(
            name = "favorites",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    private Set<Food> favoriteFoods;

    // Getters and setters
    public User() {
    }
    public User(String name, String password, String email, Set<Food> favoriteFoods) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.favoriteFoods = favoriteFoods;
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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Set<Food> getFavoriteFoods()
    {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<Food> favoriteFoods)
    {
        this.favoriteFoods = favoriteFoods;
    }
}