package com.example.joaquimpinedatheironbank.controller.pri;

import com.example.joaquimpinedatheironbank.config.KeycloakProvider;
import com.example.joaquimpinedatheironbank.dto.NewAccountDTO;
import com.example.joaquimpinedatheironbank.dto.UserAutorities;
import com.example.joaquimpinedatheironbank.entities.accounts.Account;
import com.example.joaquimpinedatheironbank.http.requests.EditAccountRequest;
import com.example.joaquimpinedatheironbank.service.account.AccountService;
import com.example.joaquimpinedatheironbank.service.keycloak.KeycloakAdminClientService;
import com.example.joaquimpinedatheironbank.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/pri/account")
public class AccountController {


    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;
    private final KeycloakAdminClientService kcAdminClient;

    private final KeycloakProvider kcProvider;

    public AccountController(KeycloakAdminClientService kcAdminClient, KeycloakProvider kcProvider) {
        this.kcProvider = kcProvider;
        this.kcAdminClient = kcAdminClient;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(Principal principal, @RequestBody NewAccountDTO newAccountDTO) {
        UserAutorities autenticatedUser = kcAdminClient.getUser(principal.getName());
        return accountService.createAccount(newAccountDTO, autenticatedUser.getUsername());

    }

    @DeleteMapping("/delete")
    public String deleteAccount() {
        return "deleteAccount";
    }

    @PutMapping("/update")
    public Account updateAccount(@RequestBody EditAccountRequest editAccountRequest) {
        return accountService.updateAccount(editAccountRequest);
    }

    @GetMapping("/get")
    public List<Account> getAccount(@RequestParam String id) {
        return accountService.findAccountsOfUser( id);
    }

    @GetMapping("/getAll")
    public List<Account> getAllAccount() {
        return accountService.findAllAccounts();
    }

}
