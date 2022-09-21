package com.example.joaquimpinedatheironbank.entities.accounts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import java.math.BigDecimal;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckingAccount extends Account {
    @Min(0)
    private BigDecimal monthlyMaintenanceFee ;
    @Min(0)
    private BigDecimal minimumBalance ;
    @Min(0)
    private BigDecimal penaltyFee ;

}
