package com.example.joaquimpinedatheironbank.service;

import com.example.joaquimpinedatheironbank.dto.NewAccountDTO;
import com.example.joaquimpinedatheironbank.entities.*;
import com.example.joaquimpinedatheironbank.repository.AccountRepository;
import com.example.joaquimpinedatheironbank.repository.SavingsAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {



    @Autowired
    private UserService userService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SavingsAccountRepository savingsAccountRepository;



    @Override
    public String createAccount(NewAccountDTO newAccountDTO, String autenticatedUser) {
            userService.findUserById(newAccountDTO.getPrimaryOwner());
            if(newAccountDTO.getSecondaryOwner() != null){
                userService.findUserById(newAccountDTO.getSecondaryOwner());
            }

        switch (newAccountDTO.getAccountType()) {
            case SAVINGS:
                SavingsAccount savingsAccount =  newAccountDTO.toSavingsAccount(autenticatedUser);
               savingsAccountRepository.save(savingsAccount);
                return "Savings";
            case CREDIT:
               CreditAccount creditAccount =newAccountDTO.toCreditAccount(autenticatedUser);
                return "Current";
            case CHECKING:
                CheckingAccount checkingAccount = newAccountDTO.toCheckingAccount(autenticatedUser);
                return "Checking";
            case STUDENT:
                StudentAccount studentAccount = newAccountDTO.toStudentAccount(autenticatedUser);
                return "Student";
            default:
                throw new IllegalArgumentException("Invalid account type");
        }
    }



}
