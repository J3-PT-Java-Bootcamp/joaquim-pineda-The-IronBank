package com.example.joaquimpinedatheironbank.entities;

import com.example.joaquimpinedatheironbank.enums.AccountStatus;
import com.example.joaquimpinedatheironbank.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Entity
@Getter
@Setter
@AllArgsConstructor
public class SavingsAccount extends Account {


}
