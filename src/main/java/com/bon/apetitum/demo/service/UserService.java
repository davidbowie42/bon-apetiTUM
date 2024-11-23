package com.bon.apetitum.demo.service;

import com.bon.apetitum.demo.entity.MensaUser;
import com.bon.apetitum.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public MensaUser save(MensaUser newUser) {

        return userRepository.save(newUser);
    }

    public Iterable<MensaUser> findAll() {
        return userRepository.findAll();
    }

    public MensaUser findById(int userId) {
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

