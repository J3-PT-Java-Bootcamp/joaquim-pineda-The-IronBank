package com.example.joaquimpinedatheironbank.entities.accounts;

import com.example.joaquimpinedatheironbank.entities.Money;
import com.example.joaquimpinedatheironbank.entities.transaction.Transaction;
import com.example.joaquimpinedatheironbank.enums.AccountStatus;
import com.example.joaquimpinedatheironbank.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckingAccount extends Account {
    @Min(0)
    private BigDecimal monthlyMaintenanceFee ;
    @Min(0)
    private BigDecimal minimumBalance ;
    @Min(0)
    private BigDecimal penaltyFee ;

    public CheckingAccount(String accountNumber, Money balance, String secretKey, String primaryOwner, String secondaryOwner, AccountType type, String createdBy, AccountStatus status, List<Transaction> from, List<Transaction> to, BigDecimal monthlyMaintenanceFee, BigDecimal minimumBalance, BigDecimal penaltyFee) {
        super(accountNumber, balance, secretKey, primaryOwner, secondaryOwner, type, createdBy, status, from, to);
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.minimumBalance = minimumBalance;
        this.penaltyFee = penaltyFee;
    }
}
