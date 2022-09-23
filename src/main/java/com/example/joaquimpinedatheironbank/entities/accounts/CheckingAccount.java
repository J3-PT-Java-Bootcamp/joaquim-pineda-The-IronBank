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
import java.time.Instant;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class CheckingAccount extends Account {
    @Min(0)
    private BigDecimal monthlyMaintenanceFee ;
    @Min(0)
    private BigDecimal minimumBalance ;
    @Min(0)
    private BigDecimal penaltyFee ;

    private Instant lastFee = Instant.now();
    private boolean lastMinimumBalance = false;


    public CheckingAccount(String accountNumber, Money balance, String secretKey, String primaryOwner, String secondaryOwner, AccountType type, String createdBy, AccountStatus status, List<Transaction> from, List<Transaction> to, BigDecimal monthlyMaintenanceFee, BigDecimal minimumBalance, BigDecimal penaltyFee) {
        super(accountNumber, balance, secretKey, primaryOwner, secondaryOwner, type, createdBy, status, from, to);
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.minimumBalance = minimumBalance;
        this.penaltyFee = penaltyFee;
        this.lastFee= Instant.now();
        this.lastMinimumBalance= false;
    }

    public void checkCheckingAccount() {
        Instant now = Instant.now();
        if (now.isAfter(lastFee.plusSeconds(1*60*60*24*30))) {
            if (balance.getAmount().compareTo(minimumBalance) < 0) {
                balance.setAmount(balance.getAmount().subtract(monthlyMaintenanceFee));
                lastFee = Instant.now();
            }
        }
        if (!lastMinimumBalance) {
            if(balance.getAmount().compareTo(minimumBalance) >= 0){
                lastMinimumBalance = true;
            balance.setAmount(balance.getAmount().subtract(penaltyFee));
            }

        }else{
            if(balance.getAmount().compareTo(minimumBalance) < 0){
                lastMinimumBalance = false;
            }
        }

    }
}
