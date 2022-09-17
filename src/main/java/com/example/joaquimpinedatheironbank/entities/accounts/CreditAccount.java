package com.example.joaquimpinedatheironbank.entities.accounts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CreditAccount extends Account {

    private BigDecimal creditLimit;

    private BigDecimal interestRate;

    private BigDecimal penaltyFee;


}
