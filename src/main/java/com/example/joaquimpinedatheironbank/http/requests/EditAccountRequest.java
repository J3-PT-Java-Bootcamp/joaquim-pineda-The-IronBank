package com.example.joaquimpinedatheironbank.http.requests;

import com.example.joaquimpinedatheironbank.entities.Money;
import com.example.joaquimpinedatheironbank.entities.accounts.CheckingAccount;
import com.example.joaquimpinedatheironbank.entities.accounts.CreditAccount;
import com.example.joaquimpinedatheironbank.entities.accounts.SavingsAccount;
import com.example.joaquimpinedatheironbank.entities.accounts.StudentAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EditAccountRequest {

    @NotNull
    @NotBlank
    private String id;
    @NotNull
    @NotBlank
    private String secretKey;
    @NotNull
    @NotBlank
    private String primaryOwner;
    private String secondaryOwner;
    @NotNull
    @NotBlank
    private BigDecimal penalityFee;
    private BigDecimal minimumBalance;
    private BigDecimal monthlyMaintenanceFee;
    private BigDecimal creditLimit;
    private BigDecimal interestRate;
    private BigDecimal balance;


    public SavingsAccount toSavingAccount(SavingsAccount accountToUpdate) {

        if (balance != null) {
            accountToUpdate.setBalance(new Money(balance));
        }
        if (primaryOwner != null) {
            accountToUpdate.setPrimaryOwner(primaryOwner);
        }
        if (secondaryOwner != null) {
            accountToUpdate.setSecondaryOwner(secondaryOwner);
        }


        return accountToUpdate;
    }

    public CreditAccount toCreditAccount(CreditAccount accountToUpdate) {
        if (balance != null) {
            accountToUpdate.setBalance(new Money(balance));
        }
        if (primaryOwner != null) {
            accountToUpdate.setPrimaryOwner(primaryOwner);
        }
        if (secondaryOwner != null) {
            accountToUpdate.setSecondaryOwner(secondaryOwner);
        }
        if (secretKey != null) {
            accountToUpdate.setSecretKey(secretKey);
        }
        if (creditLimit != null) {
            accountToUpdate.setCreditLimit(creditLimit);
        }
        if (interestRate != null) {
            accountToUpdate.setInterestRate(interestRate);
        }
        return accountToUpdate;
    }

    public CheckingAccount toCheckingAccount(CheckingAccount accountToUpdate) {
        if (balance != null) {
            accountToUpdate.setBalance(new Money(balance));
        }
        if (primaryOwner != null) {
            accountToUpdate.setPrimaryOwner(primaryOwner);
        }
        if (secondaryOwner != null) {
            accountToUpdate.setSecondaryOwner(secondaryOwner);
        }

        if (monthlyMaintenanceFee != null) {
            accountToUpdate.setMonthlyMaintenanceFee(monthlyMaintenanceFee);
        }
        if (minimumBalance != null) {
            accountToUpdate.setMinimumBalance(minimumBalance);
        }

        if (secretKey != null) {
            accountToUpdate.setSecretKey(secretKey);
        }

        if (penalityFee != null) {
            accountToUpdate.setPenaltyFee(penalityFee);
        }


        return accountToUpdate;
    }

    public StudentAccount toStudentAccount(StudentAccount accountToUpdate) {


        if (balance != null) {
            accountToUpdate.setBalance(new Money(balance));
        }
        if (primaryOwner != null) {
            accountToUpdate.setPrimaryOwner(primaryOwner);
        }
        if (secondaryOwner != null) {
            accountToUpdate.setSecondaryOwner(secondaryOwner);
        }

        if (monthlyMaintenanceFee != null) {
            accountToUpdate.setMonthlyMaintenanceFee(monthlyMaintenanceFee);
        }

        if(secretKey != null){
            accountToUpdate.setSecretKey(secretKey);
        }

        if(minimumBalance != null){

            accountToUpdate.setMinimumBalance(minimumBalance);

        }


        return accountToUpdate;
    }
}
