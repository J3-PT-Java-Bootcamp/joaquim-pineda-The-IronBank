package com.example.joaquimpinedatheironbank.service.transaction;

import com.example.joaquimpinedatheironbank.entities.Money;
import com.example.joaquimpinedatheironbank.entities.accounts.Account;
import com.example.joaquimpinedatheironbank.entities.accounts.Transaction;
import com.example.joaquimpinedatheironbank.entities.users.User;
import com.example.joaquimpinedatheironbank.enums.TransactionType;
import com.example.joaquimpinedatheironbank.http.requests.MoneyTransferRequest;
import com.example.joaquimpinedatheironbank.service.account.AccountService;
import com.example.joaquimpinedatheironbank.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;


    @Override
    @Transactional
    public void transferMoney(MoneyTransferRequest moneyTransferRequest) {
        Account from = accountService.findAccountNumber(moneyTransferRequest.getFromAccount());

        if(from == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Account not found");
        }
        User fromUser = userService.findUserById(from.getPrimaryOwner());

        if(from.getBalance().getAmount().compareTo(moneyTransferRequest.getAmount()) >= 0) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Insufficient funds");
        }
        if(fromUser.getName().trim().equalsIgnoreCase(moneyTransferRequest.getOwnerOfToAccount().trim())) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid name");
        }

        Account to = accountService.findAccountNumber(moneyTransferRequest.getToAccount());
        from.subtractBalance(moneyTransferRequest.getAmount());
        to.addBalance(moneyTransferRequest.getAmount());
        to.addTransaction(new Transaction(TransactionType.TRANSFER,
                from.getAccountNumber(),
                to.getAccountNumber(),
                moneyTransferRequest.getDescription(),
                new Money(moneyTransferRequest.getAmount()))
        );

        to.addTransaction(new Transaction(TransactionType.TRANSFER,
                from.getAccountNumber(),
                to.getAccountNumber(),
                moneyTransferRequest.getDescription(),
                new Money( new BigDecimal(0).subtract(moneyTransferRequest.getAmount())))
        );



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
