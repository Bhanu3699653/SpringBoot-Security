package com.pack.SpringBootSecurity.service;

import com.pack.SpringBootSecurity.model.User;

public interface UserService {
    void save(User user);
   // void saveRole(Iterable i);
    User findByUsername(String username);
}