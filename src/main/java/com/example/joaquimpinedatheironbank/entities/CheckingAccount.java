package com.example.joaquimpinedatheironbank.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.math.BigDecimal;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckingAccount extends Account {

    private BigDecimal monthlyMaintenanceFee ;
    private BigDecimal minimumBalance ;

}
