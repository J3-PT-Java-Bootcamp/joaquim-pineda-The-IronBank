package com.example.joaquimpinedatheironbank.service.account;

import com.example.joaquimpinedatheironbank.dto.NewAccountDTO;
import com.example.joaquimpinedatheironbank.entities.accounts.*;
import com.example.joaquimpinedatheironbank.http.requests.EditAccountRequest;

import com.example.joaquimpinedatheironbank.repository.accounts.*;
import com.example.joaquimpinedatheironbank.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Account> findAccountNumber(String fromAccount) {
        return accountRepository.findByAccountNumber(fromAccount);
    }

    @Override
    public Account updateAccount(EditAccountRequest account) {

        switch (account.getAccountType()) {
            case SAVINGS:
                return savingsAccountRepository.save((SavingsAccount) account.toSavingAccount());
            case CREDIT:
                return creditAccountRepository.save((CreditAccount) account.toCreditAccount());
            case CHECKING:
                return checkingAccountRepository.save((CheckingAccount) account.toCheckingAccount());
            case STUDENT:
                return studentAccountRepository.save((StudentAccount) account.toStudentAccount());
            default:
                throw new IllegalArgumentException("Invalid account type");
        }

    }

    @Override
    public void save(Account from) {
        accountRepository.save(from);
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> findAccountsOfUser(String id) {
        return accountRepository.findByAccountOwnersId(id);
    }


}
