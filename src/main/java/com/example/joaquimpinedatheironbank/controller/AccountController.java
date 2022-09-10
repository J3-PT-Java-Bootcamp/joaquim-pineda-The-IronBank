package com.example.joaquimpinedatheironbank.controller;

import com.example.joaquimpinedatheironbank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping
    public void getAllAccounts() {
        accountService.findAll();
    }

}
