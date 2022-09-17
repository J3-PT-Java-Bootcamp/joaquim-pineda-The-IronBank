package com.example.joaquimpinedatheironbank.entities.users;

import com.example.joaquimpinedatheironbank.entities.accounts.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String UUID;
    private String name;
    private String Token;
    private String email;
    private String userName;

}
