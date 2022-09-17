package com.example.joaquimpinedatheironbank.service.account;

import com.example.joaquimpinedatheironbank.dto.NewAccountDTO;
import com.example.joaquimpinedatheironbank.entities.accounts.Account;
import org.springframework.http.ResponseEntity;

public interface AccountService {

    ResponseEntity<?> createAccount(NewAccountDTO newAccountDTO, String autenticatedUser);

    Account findAccountNumber(String fromAccount);

    Account updateAccount(Account from);
}
