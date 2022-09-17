package com.example.joaquimpinedatheironbank.controller.pri;


import com.example.joaquimpinedatheironbank.http.requests.MoneyTransferRequest;
import com.example.joaquimpinedatheironbank.service.transaction.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/pri/transactions")
public class transactionController {
    @Autowired
    TransactionsService transactionsService;


    @PostMapping("/transfer")
    public void transferMoney(Principal principal,@RequestBody MoneyTransferRequest moneyTransferRequest) {
        System.out.println(principal.getName());
        System.out.println(moneyTransferRequest.getFromAccount());
        System.out.println(moneyTransferRequest.getOwnerOfToAccount());
        transactionsService.transferMoney(principal.getName(), moneyTransferRequest);
    }


}
