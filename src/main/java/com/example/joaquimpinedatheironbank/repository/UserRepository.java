package com.example.joaquimpinedatheironbank.repository;

import com.example.joaquimpinedatheironbank.entities.Account;
import com.example.joaquimpinedatheironbank.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User save(User user);

    User findByEmail(String email);
}

