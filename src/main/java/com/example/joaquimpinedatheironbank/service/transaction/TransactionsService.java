package com.example.joaquimpinedatheironbank.service.transaction;

import com.example.joaquimpinedatheironbank.http.requests.MoneyTransferRequest;
import org.springframework.beans.factory.annotation.Autowired;


public interface TransactionsService {

    void transferMoney(MoneyTransferRequest moneyTransferRequest);

    void payBills();

    void depositMoney();

    void withdrawMoney();


}
