package com.example.joaquimpinedatheironbank.repository;

import com.example.joaquimpinedatheironbank.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Account, Long> {
}

