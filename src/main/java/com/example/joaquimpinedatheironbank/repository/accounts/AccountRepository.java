package com.example.joaquimpinedatheironbank.repository.accounts;

import com.example.joaquimpinedatheironbank.entities.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Account save(Account account);

    Optional<Account> findByAccountNumber(String accountNumber);

    @Query(value = "SELECT * FROM account WHERE primary_owner = :id OR secondary_owner = :id ", nativeQuery = true)
    List<Account> findByAccountOwnersId(String id);
}

