package com.example.joaquimpinedatheironbank.service.transaction;

import com.example.joaquimpinedatheironbank.entities.Money;
import com.example.joaquimpinedatheironbank.entities.accounts.Account;
import com.example.joaquimpinedatheironbank.entities.transaction.Transaction;
import com.example.joaquimpinedatheironbank.entities.users.User;
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
        Account from = accountService.findAccountNumber(moneyTransferRequest.getFromAccount());
        if (!idOfOwner.trim().equalsIgnoreCase(from.getPrimaryOwner())) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "You can't transfer money not your MONEYYYY!");
        }


        if (from == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Account not found");
        }
        User fromUser = userService.findUserById(from.getPrimaryOwner());

        if (from.getBalance().getAmount().equals(0) || from.getBalance().getAmount().compareTo(moneyTransferRequest.getAmount()) <= 0) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Insufficient funds");
        }
        if (fromUser.getName().trim().equalsIgnoreCase(moneyTransferRequest.getOwnerOfToAccount().trim())) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid name");
        }
        Account to = accountService.findAccountNumber(moneyTransferRequest.getToAccount());
        Transaction transaction = new Transaction(TransactionType.TRANSFER, from, to, "hola", new Money(moneyTransferRequest.getAmount()));
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
}
