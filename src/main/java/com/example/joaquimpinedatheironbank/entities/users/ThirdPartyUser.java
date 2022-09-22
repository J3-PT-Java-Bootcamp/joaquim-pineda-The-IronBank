package com.example.joaquimpinedatheironbank.entities.users;

import com.example.joaquimpinedatheironbank.enums.TypeOfUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.Length;
import org.checkerframework.common.value.qual.MinLen;

import javax.persistence.Entity;
import javax.validation.constraints.Size;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThirdPartyUser extends User {
@Size(min = 24, max = 24)
    private String hashedKey;

    public ThirdPartyUser(String id, TypeOfUser typeOfUser, String Token, String email, String userName, String hashedKey) {
        super(id, typeOfUser, Token, email, userName);
        this.hashedKey = hashedKey;
    }
}

