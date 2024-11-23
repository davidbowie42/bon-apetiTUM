package com.bon.apetitum.demo.repository;

import com.bon.apetitum.demo.entity.MensaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensaUserRepository extends JpaRepository<MensaUser, Integer>{
}