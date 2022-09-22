package com.example.joaquimpinedatheironbank.repository.users;

import com.example.joaquimpinedatheironbank.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User save(User user);



    User findByEmail(String email);



}

