package com.example.joaquimpinedatheironbank.entities;

import com.example.joaquimpinedatheironbank.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private TransactionType type;
    private String OriginAccount;
    private String DestinationAccount;
    private String Description;
    @OneToOne
    private Money amount;

    public Transaction() {

    }
}
