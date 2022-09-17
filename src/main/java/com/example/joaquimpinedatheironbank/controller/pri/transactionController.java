package com.example.joaquimpinedatheironbank.controller.pri;

import com.example.joaquimpinedatheironbank.http.requests.MoneyTransferRequest;
import com.example.joaquimpinedatheironbank.service.email.EmailService;
import com.example.joaquimpinedatheironbank.service.transaction.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pri/transactions")
public class transactionController {
    @Autowired
    TransactionsService transactionsService;

    @PostMapping("/transfer")
    public void transferMoney(MoneyTransferRequest moneyTransferRequest) {
        transactionsService.transferMoney( moneyTransferRequest);
    }


}
