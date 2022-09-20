package com.example.joaquimpinedatheironbank.repository.transactions;

import com.example.joaquimpinedatheironbank.entities.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {


}

