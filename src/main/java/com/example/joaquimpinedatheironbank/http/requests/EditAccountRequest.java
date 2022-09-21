package com.example.joaquimpinedatheironbank.http.requests;

import com.example.joaquimpinedatheironbank.entities.Money;
import com.example.joaquimpinedatheironbank.entities.accounts.CheckingAccount;
import com.example.joaquimpinedatheironbank.entities.accounts.CreditAccount;
import com.example.joaquimpinedatheironbank.entities.accounts.SavingsAccount;
import com.example.joaquimpinedatheironbank.entities.accounts.StudentAccount;
import com.example.joaquimpinedatheironbank.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;


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
    private AccountType accountType;
    private String createdBy;
    private BigDecimal penalityFee;
    private BigDecimal minimumBalance;
    private BigDecimal monthlyMaintenanceFee;
    private BigDecimal creditLimit;
    private BigDecimal interestRate;
    private BigDecimal balance;


    public SavingsAccount toSavingAccount() {
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setId(UUID.fromString(id));
        savingsAccount.getAccountNumber();
        savingsAccount.setBalance(new Money(balance));
        savingsAccount.setSecretKey(secretKey);
        savingsAccount.setPrimaryOwner(primaryOwner);
        savingsAccount.setSecondaryOwner(secondaryOwner);
        savingsAccount.setType(accountType);
        savingsAccount.setCreatedBy(createdBy);
        savingsAccount.setPenaltyFee(penalityFee);
        return savingsAccount;
    }

    public CreditAccount toCreditAccount() {
        CreditAccount creditAccount = new CreditAccount();
        creditAccount.setId(UUID.fromString(id));
        creditAccount.getAccountNumber();
        creditAccount.setBalance(new Money(balance));
        creditAccount.setSecretKey(secretKey);
        creditAccount.setPrimaryOwner(primaryOwner);
        creditAccount.setSecondaryOwner(secondaryOwner);
        creditAccount.setType(accountType);
        creditAccount.setCreatedBy(createdBy);
        creditAccount.setPenaltyFee(penalityFee);
        creditAccount.setCreditLimit(creditLimit);
        creditAccount.setInterestRate(interestRate);
        return creditAccount;
    }

    public CheckingAccount toCheckingAccount() {
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setId(UUID.fromString(id));
        checkingAccount.getAccountNumber();
        checkingAccount.setBalance(new Money(balance));
        checkingAccount.setSecretKey(secretKey);
        checkingAccount.setPrimaryOwner(primaryOwner);
        checkingAccount.setSecondaryOwner(secondaryOwner);
        checkingAccount.setType(accountType);
        checkingAccount.setCreatedBy(createdBy);
        checkingAccount.setPenaltyFee(penalityFee);
        checkingAccount.setMinimumBalance(minimumBalance);
        checkingAccount.setMonthlyMaintenanceFee(monthlyMaintenanceFee);
        return checkingAccount;
    }

    public StudentAccount toStudentAccount() {
        StudentAccount studentAccount = new StudentAccount();
        studentAccount.setId(UUID.fromString(id));
        studentAccount.getAccountNumber();
        studentAccount.setBalance(new Money(balance));
        studentAccount.setSecretKey(secretKey);
        studentAccount.setPrimaryOwner(primaryOwner);
        studentAccount.setSecondaryOwner(secondaryOwner);
        studentAccount.setType(accountType);
        studentAccount.setCreatedBy(createdBy);
        studentAccount.setMinimumBalance(minimumBalance);
        studentAccount.setMonthlyMaintenanceFee(monthlyMaintenanceFee);
        return studentAccount;
    }
}
