package com.example.joaquimpinedatheironbank.entities;

import com.example.joaquimpinedatheironbank.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Getter
@Setter
public abstract class Account implements AccountInterface{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @OneToOne
    private Money balance;
    private String SecretKey;
    private String PrimaryOwner;
    private String SecondaryOwner;
    @OneToOne
    private Money penaltyFee;
    @CreatedDate
    @Column(name = "created_at", nullable = false,updatable = false)
    private Instant creationDate;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updateDate;
    private AccountStatus status;

    @OneToMany
    @JoinColumn(name ="id")
    private List<Transaction> transactions;

    public Account(Money balance, String secretKey, String primaryOwner, String secondaryOwner, Money penaltyFee, Instant creationDate, AccountStatus status) {
        this.balance = balance;
        PrimaryOwner = primaryOwner;
        SecondaryOwner = secondaryOwner;
        this.penaltyFee = penaltyFee;
        this.creationDate = creationDate;
        this.status = status;
        setPassword(secretKey);
    }

    public void setPassword(String password) {
        String hash ;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = new String(digest.digest(password.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
            this.SecretKey= hash;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm");
        }
    }
    public boolean checkPassword(String password) {
        String hash2 = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash2 = new String(digest.digest(password.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm");
        }
        return hash2.equals(this.SecretKey);
    }
}
