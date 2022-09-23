package com.example.joaquimpinedatheironbank.entities.accounts;

import com.example.joaquimpinedatheironbank.entities.Money;
import com.example.joaquimpinedatheironbank.entities.transaction.Transaction;
import com.example.joaquimpinedatheironbank.enums.AccountStatus;
import com.example.joaquimpinedatheironbank.enums.AccountType;
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
public class SavingsAccount extends Account {
    @Min(0)
    private final BigDecimal penaltyFee = new BigDecimal(40);
    private Instant lastFee = Instant.now();

    public SavingsAccount(String accountNumber, Money balance, String SecretKey, String PrimaryOwner, String SecondaryOwner, AccountType Type, String CreatedBy, AccountStatus status, List<Transaction> from, List<Transaction> to) {
        super(accountNumber, balance, SecretKey, PrimaryOwner, SecondaryOwner, Type, CreatedBy, status, from, to);
    }


    public void checkSavingsAccount() {
        Instant now = Instant.now();
        if (now.isAfter(lastFee.plusSeconds(1*60*60*24*30))) {
            if (balance.getAmount().compareTo(new BigDecimal(1000)) < 0) {
                balance.setAmount(balance.getAmount().subtract(penaltyFee));
                lastFee = now;
            }
        }
    }
}
