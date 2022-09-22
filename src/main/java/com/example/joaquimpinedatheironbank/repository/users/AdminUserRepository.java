package com.example.joaquimpinedatheironbank.repository.users;

import com.example.joaquimpinedatheironbank.entities.users.AccountHolder;
import com.example.joaquimpinedatheironbank.entities.users.AdminUser;
import com.example.joaquimpinedatheironbank.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserRepository extends JpaRepository<AdminUser, String> {

    AdminUser save(AdminUser user);



    AdminUser findByEmail(String email);



}

