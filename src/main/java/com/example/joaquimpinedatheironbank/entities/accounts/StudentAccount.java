package com.example.joaquimpinedatheironbank.entities.accounts;

import com.example.joaquimpinedatheironbank.entities.accounts.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class StudentAccount extends Account {

    private BigDecimal minimumBalance;
    private BigDecimal monthlyMaintenanceFee;


}
