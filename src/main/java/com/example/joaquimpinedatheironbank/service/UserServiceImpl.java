package com.example.joaquimpinedatheironbank.service;

import com.example.joaquimpinedatheironbank.entities.User;
import com.example.joaquimpinedatheironbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;



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

    @Override
    public User findUserById(String autenticatedUser) throws HttpClientErrorException {
        try {
            return userRepository.findById(autenticatedUser).get();
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "User not found");
        }
    }
}


