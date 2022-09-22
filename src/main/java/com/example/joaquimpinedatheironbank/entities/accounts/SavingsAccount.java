package com.example.joaquimpinedatheironbank.entities.accounts;

import com.example.joaquimpinedatheironbank.entities.Money;
import com.example.joaquimpinedatheironbank.entities.accounts.Account;
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
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class SavingsAccount extends Account {
    @Min(0)
    private final BigDecimal penaltyFee = new BigDecimal(40);

    public SavingsAccount( String accountNumber, Money balance, String SecretKey, String PrimaryOwner, String SecondaryOwner, AccountType Type, String CreatedBy, AccountStatus status, List<Transaction> from, List<Transaction> to) {
        super(accountNumber, balance, SecretKey, PrimaryOwner, SecondaryOwner, Type, CreatedBy, status, from, to);
    }


}
