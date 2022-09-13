package com.example.joaquimpinedatheironbank.service;

import com.example.joaquimpinedatheironbank.entities.User;
import com.example.joaquimpinedatheironbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void findAll() {

    }

    @Override
    public void create(User user) {
        userRepository.save(user);

    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
