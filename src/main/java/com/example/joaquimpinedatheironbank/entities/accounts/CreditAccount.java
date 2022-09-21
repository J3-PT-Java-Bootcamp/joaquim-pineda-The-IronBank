package com.example.joaquimpinedatheironbank.entities.accounts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CreditAccount extends Account {

    private BigDecimal creditLimit;
    @Min(0)
    private BigDecimal interestRate;
    @Min(0)
    private BigDecimal penaltyFee;


}
