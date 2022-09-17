package com.example.joaquimpinedatheironbank.entities.accounts;

import com.example.joaquimpinedatheironbank.entities.accounts.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SavingsAccount extends Account {

    private BigDecimal penaltyFee;

}
