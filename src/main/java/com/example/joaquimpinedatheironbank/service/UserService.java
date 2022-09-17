package com.example.joaquimpinedatheironbank.service;

import com.example.joaquimpinedatheironbank.entities.users.User;

public interface UserService {

    void create(User user);

    User findByEmail(String email);

    void save(User user);

    User findUserById(String autenticatedUser);
}
