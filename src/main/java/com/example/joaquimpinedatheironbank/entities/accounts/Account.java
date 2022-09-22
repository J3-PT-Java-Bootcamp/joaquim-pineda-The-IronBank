package com.example.joaquimpinedatheironbank.entities.accounts;

import com.example.joaquimpinedatheironbank.entities.Money;
import com.example.joaquimpinedatheironbank.entities.transaction.Transaction;
import com.example.joaquimpinedatheironbank.enums.AccountStatus;
import com.example.joaquimpinedatheironbank.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

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
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
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


    @OneToMany(mappedBy = "originAccount", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transaction> from;

    @OneToMany(mappedBy = "destinationAccount")
    @JsonIgnore
    private List<Transaction> to;


    /*    @CreatedDate
        @Column(name = "created_at", nullable = false,updatable = false)
        private Instant creationDate;

        @LastModifiedDate
        @Column(name = "updated_at")
        private Instant updateDate;*/

    public Account(String accountNumber, Money balance, String secretKey, String primaryOwner, String secondaryOwner, AccountType type, String createdBy, AccountStatus status, List<Transaction> from, List<Transaction> to) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        SecretKey = secretKey;
        PrimaryOwner = primaryOwner;
        SecondaryOwner = secondaryOwner;
        Type = type;
        CreatedBy = createdBy;
        this.status = status;
        this.from = from;
        this.to = to;
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

    public boolean isFrozen(){
        return this.status == AccountStatus.FROZEN;
    }
    public boolean isActive(){
        return this.status == AccountStatus.ACTIVE;
    }
    public boolean isClosed(){
        return this.status == AccountStatus.CLOSED;
    }


}
