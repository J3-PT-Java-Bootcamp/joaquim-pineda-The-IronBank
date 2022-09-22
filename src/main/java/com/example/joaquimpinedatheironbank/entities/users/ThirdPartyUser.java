package com.example.joaquimpinedatheironbank.entities.users;

import com.example.joaquimpinedatheironbank.enums.TypeOfUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThirdPartyUser extends User {
    private String hashedKey;

    public ThirdPartyUser(String id, TypeOfUser typeOfUser, String Token, String email, String userName, String hashedKey) {
        super(id, typeOfUser, Token, email, userName);
        this.hashedKey = hashedKey;
    }
}

