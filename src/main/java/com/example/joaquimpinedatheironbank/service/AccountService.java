package com.example.joaquimpinedatheironbank.service;

import com.example.joaquimpinedatheironbank.dto.NewAccountDTO;
import com.example.joaquimpinedatheironbank.entities.Account;

import java.util.List;

public interface AccountService {

    String createAccount(NewAccountDTO newAccountDTO, String autenticatedUser);
}
