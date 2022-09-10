package com.example.joaquimpinedatheironbank.entities;

import com.example.joaquimpinedatheironbank.enums.AccountStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;


@Entity
@Setter
@Getter
@Table(name = "StudentCheking")
public class StudentCheckingAccount extends Account {
    public StudentCheckingAccount(Money balance, String secretKey, String primaryOwner, String secondaryOwner, Money penaltyFee, Instant creationDate, AccountStatus status) {
        super(balance, secretKey, primaryOwner, secondaryOwner, penaltyFee, creationDate, status);
    }


}
