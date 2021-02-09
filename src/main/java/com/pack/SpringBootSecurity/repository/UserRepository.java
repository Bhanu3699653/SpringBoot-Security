package com.pack.SpringBootSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pack.SpringBootSecurity.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}