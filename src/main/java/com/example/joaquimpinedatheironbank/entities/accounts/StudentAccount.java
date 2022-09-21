package com.example.joaquimpinedatheironbank.entities.accounts;

import com.example.joaquimpinedatheironbank.entities.accounts.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class StudentAccount extends Account {
    @Min(0)
    private BigDecimal minimumBalance;
    @Min(0)
    private BigDecimal monthlyMaintenanceFee;


}
