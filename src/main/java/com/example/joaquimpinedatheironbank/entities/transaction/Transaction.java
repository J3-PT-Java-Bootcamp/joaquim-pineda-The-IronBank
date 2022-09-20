package com.example.joaquimpinedatheironbank.entities.transaction;

import com.example.joaquimpinedatheironbank.entities.Money;
import com.example.joaquimpinedatheironbank.entities.accounts.Account;
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
    private UUID id = UUID.randomUUID();
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne (fetch = FetchType.LAZY)
    private Account originAccount;

    @ManyToOne (fetch = FetchType.LAZY)
    private Account destinationAccount;
    private String description;
    private Money amount;
    private Instant transactionDate;

    public Transaction(TransactionType type, Account originAccount, Account destinationAccount, String description, Money amount) {
        this.type = type;
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
        this.description = description;
        this.amount = amount;
        this.transactionDate = Instant.now();
    }
}
