package com.bon.apetitum.demo.service;

import com.bon.apetitum.demo.entity.User;
import com.bon.apetitum.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User newUser) {

        return userRepository.save(newUser);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void deleteById(int userId) {
        try {
            userRepository.deleteById(userId);
        }
        catch (Exception e) {
            System.err.println("Unable to delete User with ID: " + userId);
        }
    }

}

