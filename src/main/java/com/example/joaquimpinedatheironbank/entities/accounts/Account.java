package com.example.joaquimpinedatheironbank.entities.accounts;

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
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account implements AccountInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @GenericGenerator(name = "AccountNumber", strategy = "com.example.joaquimpinedatheironbank.utils.AccountNumberGenerator")
    @GeneratedValue(generator = "AccountNumber")
    private String accountNumber = "00000000";
    private BigDecimal balance;
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


}
