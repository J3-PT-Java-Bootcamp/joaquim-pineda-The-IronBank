package com.example.joaquimpinedatheironbank.entities.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public class User {

    @Id
    private String id;
    private String name;
    private String Token;
    private String email;
    private String userName;
}
