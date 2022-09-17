package com.example.joaquimpinedatheironbank.repository;

import com.example.joaquimpinedatheironbank.entities.Account;
import com.example.joaquimpinedatheironbank.entities.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, String> {
    SavingsAccount save(SavingsAccount account);

}

