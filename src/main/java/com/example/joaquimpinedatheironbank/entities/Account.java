package com.example.joaquimpinedatheironbank.entities;

import com.example.joaquimpinedatheironbank.enums.AccountStatus;
import com.example.joaquimpinedatheironbank.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account implements AccountInterface{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @OneToOne
    private Money balance;
    private String SecretKey;
    private String PrimaryOwner;
    private String SecondaryOwner;
    private AccountType Type;
    private BigDecimal penaltyFee;
    private String CreatedBy;


    @OneToMany
    @JoinColumn(name ="id")
    private List<Transaction> transactions;

    @CreatedDate
    @Column(name = "created_at", nullable = false,updatable = false)
    private Instant creationDate;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updateDate;
    private AccountStatus status;



}
