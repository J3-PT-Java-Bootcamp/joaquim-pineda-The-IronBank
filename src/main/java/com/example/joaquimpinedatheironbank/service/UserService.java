package com.example.joaquimpinedatheironbank.service;

import com.example.joaquimpinedatheironbank.entities.User;

public interface UserService {
    void findAll();
    void create(User user);

    User findByEmail(String email);

    void save(User user);
}
