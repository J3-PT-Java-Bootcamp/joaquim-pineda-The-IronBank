package com.example.joaquimpinedatheironbank.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Setter
@Getter
@Table(name = "Savings")
public class SavingsAccount extends Account {


}
