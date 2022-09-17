package com.example.joaquimpinedatheironbank.repository.accounts;

import com.example.joaquimpinedatheironbank.entities.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account save(Account account);

    Optional<Account> findByAccountNumber(String accountNumber);
}

