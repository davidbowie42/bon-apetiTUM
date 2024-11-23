package com.bon.apetitum.demo.service;

import com.bon.apetitum.demo.entity.MensaUser;
import com.bon.apetitum.demo.repository.MensaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MensaUserService {

    @Autowired
    private MensaUserRepository mensaUserRepository;

    public MensaUser save(MensaUser newUser) {

        return mensaUserRepository.save(newUser);
    }

    public Iterable<MensaUser> findAll() {
        return mensaUserRepository.findAll();
    }

    public MensaUser findById(int userId) {
        return mensaUserRepository.findById(userId).orElse(null);
    }

    public void deleteById(int userId) {
        try {
            mensaUserRepository.deleteById(userId);
        }
        catch (Exception e) {
            System.err.println("Unable to delete User with ID: " + userId);
        }
    }

}

