package com.example.joaquimpinedatheironbank.repository.accounts;

import com.example.joaquimpinedatheironbank.entities.accounts.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, String> {
    CheckingAccount save(CheckingAccount account);

}

