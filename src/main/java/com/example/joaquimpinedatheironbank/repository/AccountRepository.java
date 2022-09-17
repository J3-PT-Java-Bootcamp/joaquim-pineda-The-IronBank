package com.example.joaquimpinedatheironbank.repository;

import com.example.joaquimpinedatheironbank.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}

