package com.example.joaquimpinedatheironbank.service.user;

import com.example.joaquimpinedatheironbank.entities.users.User;

public interface UserService {

    void create(User user);

    User findByEmail(String email);

    void save(User user);

    User findUserById(String id);

}
