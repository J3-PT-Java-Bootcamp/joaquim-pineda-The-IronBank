package com.example.joaquimpinedatheironbank.service;

import com.example.joaquimpinedatheironbank.dto.NewAccountDTO;
import org.springframework.http.ResponseEntity;

public interface AccountService {

    ResponseEntity<?> createAccount(NewAccountDTO newAccountDTO, String autenticatedUser);
}
