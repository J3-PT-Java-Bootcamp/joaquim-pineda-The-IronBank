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
import java.util.UUID;

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
        if (newAccountDTO.getSecondaryOwner() != null) {
            userService.findUserById(newAccountDTO.getSecondaryOwner());
        }

        switch (newAccountDTO.getAccountType()) {
            case SAVINGS:
                SavingsAccount savingsAccount = newAccountDTO.toSavingsAccount(autenticatedUser);
                savingsAccountRepository.save(savingsAccount);
                return ResponseEntity.ok(savingsAccount);
            case CREDIT:
                CreditAccount creditAccount = newAccountDTO.toCreditAccount(autenticatedUser);
                creditAccountRepository.save(creditAccount);
                return ResponseEntity.ok(creditAccount);
            case CHECKING:
                CheckingAccount checkingAccount = newAccountDTO.toCheckingAccount(autenticatedUser);
                checkingAccountRepository.save(checkingAccount);
                return ResponseEntity.ok(checkingAccount);
            case STUDENT:
                StudentAccount studentAccount = newAccountDTO.toStudentAccount(autenticatedUser);
                studentAccountRepository.save(studentAccount);
                return ResponseEntity.ok(studentAccount);
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
        Account accountToUpdate = accountRepository.findById(UUID.fromString(account.getId())).get();
        switch (accountToUpdate.getType()) {
            case SAVINGS:
                return savingsAccountRepository.save(account.toSavingAccount((SavingsAccount) accountToUpdate));
            case CREDIT:
                return creditAccountRepository.save(account.toCreditAccount((CreditAccount) accountToUpdate));
            case CHECKING:
                return checkingAccountRepository.save(account.toCheckingAccount((CheckingAccount) accountToUpdate));
            case STUDENT:
                return studentAccountRepository.save(account.toStudentAccount((StudentAccount) accountToUpdate));
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

    @Override
    public void checkAllAccounts() {
        List<CreditAccount> creditAccounts = creditAccountRepository.findAll();
        List<StudentAccount> studentAccounts = studentAccountRepository.findAll();
        List<CheckingAccount> checkingAccounts = checkingAccountRepository.findAll();
        List<SavingsAccount> savingsAccounts = savingsAccountRepository.findAll();

        for (CreditAccount creditAccount : creditAccounts) {
            creditAccount.checkCreditAccount();
            creditAccountRepository.save(creditAccount);
        }
        for (StudentAccount studentAccount : studentAccounts) {
            studentAccount.checkStudentAccount();
            studentAccountRepository.save(studentAccount);
        }
        for (CheckingAccount checkingAccount : checkingAccounts) {
            checkingAccount.checkCheckingAccount();
            checkingAccountRepository.save(checkingAccount);
        }
        for (SavingsAccount savingsAccount : savingsAccounts) {
            savingsAccount.checkSavingsAccount();
            savingsAccountRepository.save(savingsAccount);
        }


    }


}
