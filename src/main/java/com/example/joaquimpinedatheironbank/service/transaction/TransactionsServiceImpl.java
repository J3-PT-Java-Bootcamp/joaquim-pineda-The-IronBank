package com.example.joaquimpinedatheironbank.service.transaction;

import com.example.joaquimpinedatheironbank.entities.Money;
import com.example.joaquimpinedatheironbank.entities.accounts.Account;
import com.example.joaquimpinedatheironbank.entities.transaction.Transaction;
import com.example.joaquimpinedatheironbank.enums.TransactionType;
import com.example.joaquimpinedatheironbank.http.requests.MoneyTransferRequest;
import com.example.joaquimpinedatheironbank.repository.transactions.TransactionRepository;
import com.example.joaquimpinedatheironbank.service.account.AccountService;
import com.example.joaquimpinedatheironbank.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public ResponseEntity<?> transferMoney(String idOfOwner, MoneyTransferRequest moneyTransferRequest) throws HttpClientErrorException {
        Account from = accountService.findAccountNumber(moneyTransferRequest.getFromAccount()).get();
        checkIfAccountExists(from);
        checkIfAmountIsPositive(moneyTransferRequest.getAmount());
        checkIfUserIsOwnerOfAccount(from, idOfOwner);
        checkIfAccountIsFrozen(from);
        checkIfAccountIsClosed(from);
        checkIfAccountHasEnoughMoney(from, moneyTransferRequest.getAmount());
        Account to = null;
        try {
            to = accountService.findAccountNumber(moneyTransferRequest.getToAccount())
                    .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        }catch (HttpClientErrorException e){

        }
        from.subtractBalance(moneyTransferRequest.getAmount());
        Transaction transaction = new Transaction();
        transaction.setAmount(new Money(moneyTransferRequest.getAmount()));
        transaction.setDescription(moneyTransferRequest.getDescription());
        transaction.setOriginAccount(from);
        if (checkIfReceiverAccountExists(to)) {
            checkIfUserNameIsTheSame(to, moneyTransferRequest.getToAccount());
            transaction.setType(TransactionType.INTERNALTRANSFER);
            transaction.setDestinationAccount(to);
            to.addBalance(moneyTransferRequest.getAmount());
            accountService.save(to);
        } else {
            transaction.setType(TransactionType.EXTERNALTRANSFER);
            transaction.setExternalDestinationAccount(moneyTransferRequest.getToAccount());
        }
        accountService.save(from);
        transactionRepository.save(transaction);


        return ResponseEntity.ok(to);

    }

    @Override
    public void payBills() {

    }

    @Override
    public void depositMoney() {

    }

    @Override
    public void withdrawMoney() {

    }

    private void checkIfAccountExists(Account account) {
        if (account == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Account not found");
        }
    }

    private void checkIfUserIsOwnerOfAccount(Account account, String idOfOwner) {
        if (!idOfOwner.trim().equalsIgnoreCase(account.getPrimaryOwner()) && !idOfOwner.trim().equalsIgnoreCase(account.getSecondaryOwner())) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "You can't transfer money not your MONEYYYY!");
        }
    }

    private void checkIfUserNameIsTheSame(Account account, String nameOfUser) {
        if (account.getPrimaryOwner().trim().equalsIgnoreCase(nameOfUser.trim())) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid name");
        }
    }

    private void checkIfAccountHasEnoughMoney(Account account, BigDecimal amount) {
        if (account.getBalance().getAmount().equals(0) || account.getBalance().getAmount().compareTo(amount) <= 0) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Insufficient funds");
        }
    }

    private void checkIfAccountIsFrozen(Account account) {
        if (account.isFrozen()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Account is frozen");
        }
    }

    private void checkIfAccountIsClosed(Account account) {
        if (account.isClosed()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Account is closed");
        }
    }

    private void checkIfAmountIsPositive(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Amount must be positive");
        }
    }

    private boolean checkIfReceiverAccountExists(Account account) {
        return account != null;
    }

}
