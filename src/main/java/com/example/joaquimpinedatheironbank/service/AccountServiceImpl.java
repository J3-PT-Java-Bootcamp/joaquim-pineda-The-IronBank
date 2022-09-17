package com.example.joaquimpinedatheironbank.service;

import com.example.joaquimpinedatheironbank.dto.NewAccountDTO;
import com.example.joaquimpinedatheironbank.entities.accounts.CheckingAccount;
import com.example.joaquimpinedatheironbank.entities.accounts.CreditAccount;
import com.example.joaquimpinedatheironbank.entities.accounts.SavingsAccount;
import com.example.joaquimpinedatheironbank.entities.accounts.StudentAccount;
import com.example.joaquimpinedatheironbank.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {



    @Autowired
    private UserService userService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SavingsAccountRepository savingsAccountRepository;
    @Autowired
    private CreditAccountRepository creditAccountRepository;

    @Autowired
    private StudentAccountRepository studentAccountRepository;
    @Autowired
    private CheckingAccountRepository checkingAccountRepository;

    @Override
    public ResponseEntity<?> createAccount(NewAccountDTO newAccountDTO, String autenticatedUser) {
            userService.findUserById(newAccountDTO.getPrimaryOwner());
            if(newAccountDTO.getSecondaryOwner() != null){
                userService.findUserById(newAccountDTO.getSecondaryOwner());
            }

        switch (newAccountDTO.getAccountType()) {
            case SAVINGS:
                SavingsAccount savingsAccount =  newAccountDTO.toSavingsAccount(autenticatedUser);
               savingsAccountRepository.save(savingsAccount);
                return ResponseEntity.ok(savingsAccount);
            case CREDIT:
               CreditAccount creditAccount =newAccountDTO.toCreditAccount(autenticatedUser);
                creditAccountRepository.save(creditAccount);

                return ResponseEntity.ok(creditAccount);
            case CHECKING:
                CheckingAccount checkingAccount = newAccountDTO.toCheckingAccount(autenticatedUser);
                checkingAccountRepository.save(checkingAccount);
                return ResponseEntity.ok(checkingAccount);
            case STUDENT:
                StudentAccount studentAccount = newAccountDTO.toStudentAccount(autenticatedUser);
                studentAccountRepository.save(studentAccount);
                return  ResponseEntity.ok(studentAccount);
            default:
                throw new IllegalArgumentException("Invalid account type");
        }
    }



}
