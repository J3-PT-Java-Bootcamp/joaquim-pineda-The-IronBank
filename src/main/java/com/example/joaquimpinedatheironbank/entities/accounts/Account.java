package com.example.joaquimpinedatheironbank.entities.accounts;

import com.example.joaquimpinedatheironbank.entities.Money;
import com.example.joaquimpinedatheironbank.enums.AccountStatus;
import com.example.joaquimpinedatheironbank.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
public class Account implements AccountInterface {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @GenericGenerator(name = "AccountNumber", strategy = "com.example.joaquimpinedatheironbank.utils.AccountNumberGenerator")
    @GeneratedValue(generator = "AccountNumber")
    private String accountNumber;
    @Embedded
    private Money balance;
    private String SecretKey;
    private String PrimaryOwner;
    private String SecondaryOwner;
    @Enumerated(EnumType.STRING)
    private AccountType Type;
    private String CreatedBy;

    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.ACTIVE;


    @OneToMany
    @JoinColumn(name = "id")
    private List<Transaction> transactions;


    /*    @CreatedDate
        @Column(name = "created_at", nullable = false,updatable = false)
        private Instant creationDate;

        @LastModifiedDate
        @Column(name = "updated_at")
        private Instant updateDate;*/
    public Account( Money balance, String secretKey, String primaryOwner, String secondaryOwner, AccountType type, String createdBy, AccountStatus status, List<Transaction> transactions) {
        this.accountNumber = new Random().nextInt(1000000000)+ "";
        this.balance = balance;
        SecretKey = secretKey;
        PrimaryOwner = primaryOwner;
        SecondaryOwner = secondaryOwner;
        Type = type;
        CreatedBy = createdBy;
        this.status = status;
        this.transactions = transactions;
    }

    public Account() {
        this.accountNumber = new Random().nextInt(1000000000)+ "";

    }

    public void subtractBalance(BigDecimal amount){
        this.setBalance(new Money(this.getBalance().getAmount().subtract(amount)));
    }

    public void addBalance(BigDecimal amount){
        this.setBalance(new Money(this.getBalance().getAmount().add(amount)));
    }
    public void addTransaction(Transaction transaction){
        this.transactions.add(transaction);
    }



}
