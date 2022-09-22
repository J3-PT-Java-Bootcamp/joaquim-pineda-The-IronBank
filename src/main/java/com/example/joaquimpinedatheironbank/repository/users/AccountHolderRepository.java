package com.example.joaquimpinedatheironbank.repository.users;

import com.example.joaquimpinedatheironbank.entities.users.AccountHolder;
import com.example.joaquimpinedatheironbank.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHolderRepository extends JpaRepository<AccountHolder, String> {

    AccountHolder save(AccountHolder user);



    User findByEmail(String email);



}

