package com.example.joaquimpinedatheironbank.repository;

import com.example.joaquimpinedatheironbank.entities.accounts.StudentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentAccountRepository extends JpaRepository<StudentAccount, String> {

    StudentAccount save(StudentAccount account);

}

