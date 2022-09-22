package com.example.joaquimpinedatheironbank.entities.accounts;

import com.example.joaquimpinedatheironbank.entities.Money;
import com.example.joaquimpinedatheironbank.entities.accounts.Account;
import com.example.joaquimpinedatheironbank.entities.transaction.Transaction;
import com.example.joaquimpinedatheironbank.enums.AccountStatus;
import com.example.joaquimpinedatheironbank.enums.AccountType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class StudentAccount extends Account {
    @Min(0)
    private BigDecimal minimumBalance;
    @Min(0)
    private BigDecimal monthlyMaintenanceFee;

    public StudentAccount(String accountNumber, Money balance, String secretKey, String primaryOwner, String secondaryOwner, AccountType type, String createdBy, AccountStatus status, List<Transaction> from, List<Transaction> to, BigDecimal minimumBalance, BigDecimal monthlyMaintenanceFee) {
        super(accountNumber, balance, secretKey, primaryOwner, secondaryOwner, type, createdBy, status, from, to);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }
}
