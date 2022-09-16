package com.example.joaquimpinedatheironbank.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "checkingAccount")
public class CheckingAccount extends Account {

    @OneToOne
    private Money monthlyMaintenanceFee = new Money(new BigDecimal("0"));
    @OneToOne
    private Money minimumBalance = new Money(new BigDecimal("12.4"));


}