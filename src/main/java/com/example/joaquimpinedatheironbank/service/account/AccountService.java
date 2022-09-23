package com.example.joaquimpinedatheironbank.service.account;

import com.example.joaquimpinedatheironbank.dto.NewAccountDTO;
import com.example.joaquimpinedatheironbank.entities.accounts.Account;
import com.example.joaquimpinedatheironbank.http.requests.EditAccountRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    ResponseEntity<?> createAccount(NewAccountDTO newAccountDTO, String autenticatedUser);

    Optional<Account> findAccountNumber(String fromAccount);

    Account updateAccount(EditAccountRequest from);

    void save(Account from);

    List<Account> findAllAccounts();
    List<Account> findAccountsOfUser(String id);

    void checkAllAccounts();
}
