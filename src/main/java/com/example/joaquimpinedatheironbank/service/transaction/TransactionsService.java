package com.example.joaquimpinedatheironbank.service.transaction;

import com.example.joaquimpinedatheironbank.http.requests.MoneyTransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


public interface TransactionsService {

    ResponseEntity<?> transferMoney(String idOfOwner, MoneyTransferRequest moneyTransferRequest);

    void payBills();

    void depositMoney();

    void withdrawMoney();


}
