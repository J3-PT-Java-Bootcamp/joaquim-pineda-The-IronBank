package com.example.joaquimpinedatheironbank.entities.accounts;

import com.example.joaquimpinedatheironbank.entities.Money;
import com.example.joaquimpinedatheironbank.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private String originAccount;
    private String destinationAccount;
    private String description;
    private Money amount;
    private Instant transactionDate;

    public Transaction(TransactionType type, String originAccount, String destinationAccount, String description, Money amount) {
        this.type = type;
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
        this.description = description;
        this.amount = amount;
        this.transactionDate = Instant.now();
    }
}
