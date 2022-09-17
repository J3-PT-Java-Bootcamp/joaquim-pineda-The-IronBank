package com.example.joaquimpinedatheironbank.repository;

import com.example.joaquimpinedatheironbank.entities.accounts.StudentAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAccountRepository extends JpaRepository<StudentAccount, String> {
    StudentAccount save(StudentAccount account);

}

