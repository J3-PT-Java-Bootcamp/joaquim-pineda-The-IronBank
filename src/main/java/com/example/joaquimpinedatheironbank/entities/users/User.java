package com.example.joaquimpinedatheironbank.entities.users;

import com.example.joaquimpinedatheironbank.enums.TypeOfUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public class User {

    @Id
    private String id;
    private String Token;
    private String email;
    private String userName;
    @Enumerated(EnumType.STRING)
    private TypeOfUser typeOfUser;
    public User(String id,TypeOfUser typeOfUser, String Token, String email,String userName) {
        this.id = id;
        this.typeOfUser = typeOfUser;
        this.Token = Token;
        this.email = email;
        this.userName = userName;
    }


}
