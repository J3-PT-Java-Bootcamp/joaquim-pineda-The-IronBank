package com.example.joaquimpinedatheironbank.service.account;

import com.example.joaquimpinedatheironbank.dto.NewAccountDTO;
import com.example.joaquimpinedatheironbank.entities.accounts.*;
import com.example.joaquimpinedatheironbank.repository.AccountRepository;
import com.example.joaquimpinedatheironbank.repository.CheckingAccountRepository;
import com.example.joaquimpinedatheironbank.repository.CreditAccountRepository;
import com.example.joaquimpinedatheironbank.repository.SavingsAccountRepository;
import com.example.joaquimpinedatheironbank.repository.StudentAccountRepository;
import com.example.joaquimpinedatheironbank.service.user.UserService;
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

    @Override
    public Account findAccountNumber(String fromAccount) {
        return accountRepository.findById(111L).orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }


}
