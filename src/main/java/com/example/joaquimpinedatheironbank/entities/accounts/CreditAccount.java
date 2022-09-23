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
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CreditAccount extends Account {

    private BigDecimal creditLimit;
    @Min(0)
    private BigDecimal interestRate;
    @Min(0)
    private final BigDecimal penaltyFee = new BigDecimal(40);
    private Instant lastFee = Instant.now();
    private Instant lastInterest = Instant.now();
    public CreditAccount(String accountNumber, Money balance, String secretKey, String primaryOwner, String secondaryOwner, AccountType type, String createdBy, AccountStatus status, List<Transaction> from, List<Transaction> to, BigDecimal creditLimit, BigDecimal interestRate) {
        super(accountNumber, balance, secretKey, primaryOwner, secondaryOwner, type, createdBy, status, from, to);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }
    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }


    public CreditAccount checkCreditAccount() {
        Instant now = Instant.now();
        if (now.isAfter(lastFee.plusSeconds(1*60*60*24*30))) {
            if (balance.getAmount().compareTo(creditLimit) < 0) {
                balance.setAmount(balance.getAmount().subtract(penaltyFee));
                lastFee = now;
            }
        }
        if (now.isAfter(lastInterest.plusSeconds(1*60*60*24*30))) {
            balance.setAmount(balance.getAmount().add(balance.getAmount().multiply(interestRate)));
            lastInterest = now;
        }
        return this;
    }
}
