package com.example.joaquimpinedatheironbank.dto;

import com.example.joaquimpinedatheironbank.entities.accounts.CheckingAccount;
import com.example.joaquimpinedatheironbank.entities.accounts.CreditAccount;
import com.example.joaquimpinedatheironbank.entities.accounts.SavingsAccount;
import com.example.joaquimpinedatheironbank.entities.accounts.StudentAccount;
import com.example.joaquimpinedatheironbank.enums.AccountType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Setter
public class NewAccountDTO {


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
    private BigDecimal penalityFee;
    private BigDecimal minimumBalance;
    private BigDecimal monthlyMaintenanceFee;

    private BigDecimal creditLimit;
    private BigDecimal interestRate;




    public SavingsAccount toSavingsAccount(String createdBy) {
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setBalance(new BigDecimal(0));
        savingsAccount.setSecretKey(secretKey);
        savingsAccount.setPrimaryOwner(primaryOwner);
        savingsAccount.setSecondaryOwner(secondaryOwner);
        savingsAccount.setType(accountType);
        savingsAccount.setPenaltyFee(penalityFee);
        savingsAccount.setCreatedBy(createdBy);
        return savingsAccount;
    }

    public CreditAccount toCreditAccount(String autenticatedUser) {
        CreditAccount creditAccount = new CreditAccount();
        creditAccount.setBalance(new BigDecimal(0));
        creditAccount.setSecretKey(secretKey);
        creditAccount.setPrimaryOwner(primaryOwner);
        creditAccount.setSecondaryOwner(secondaryOwner);
        creditAccount.setType(accountType);
        creditAccount.setPenaltyFee(penalityFee);
        creditAccount.setCreatedBy(autenticatedUser);
        creditAccount.setCreditLimit(creditLimit);
        creditAccount.setInterestRate(interestRate);
        return creditAccount;
    }

    public CheckingAccount toCheckingAccount(String autenticatedUser) {
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setBalance(new BigDecimal(0));
        checkingAccount.setSecretKey(secretKey);
        checkingAccount.setPrimaryOwner(primaryOwner);
        checkingAccount.setSecondaryOwner(secondaryOwner);
        checkingAccount.setType(accountType);
        checkingAccount.setPenaltyFee(penalityFee);
        checkingAccount.setCreatedBy(autenticatedUser);
        checkingAccount.setMonthlyMaintenanceFee(monthlyMaintenanceFee);
        checkingAccount.setMinimumBalance(minimumBalance);
        return checkingAccount;
    }

    public StudentAccount toStudentAccount(String autenticatedUser) {
        StudentAccount studentAccount = new StudentAccount();
        studentAccount.setBalance(new BigDecimal(0));
        studentAccount.setSecretKey(secretKey);
        studentAccount.setPrimaryOwner(primaryOwner);
        studentAccount.setSecondaryOwner(secondaryOwner);
        studentAccount.setType(accountType);
        studentAccount.setCreatedBy(autenticatedUser);
        studentAccount.setMinimumBalance(minimumBalance);
        studentAccount.setMonthlyMaintenanceFee(monthlyMaintenanceFee);
        return studentAccount;

    }
}
