package com.example.joaquimpinedatheironbank.entities;

import com.example.joaquimpinedatheironbank.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "creditAccount")
public class CreditAccount extends Account {

    @OneToOne
    @JoinColumn(name = "id")
    private Money balance;

    private String PrimaryOwner;

    @OneToOne
    @JoinColumn(name = "id")
    private Money creditLimit;
    @OneToOne
    @JoinColumn(name = "id")
    private Money interestRate;
    @OneToOne
    @JoinColumn(name = "id")
    private Money penaltyFee;


}
