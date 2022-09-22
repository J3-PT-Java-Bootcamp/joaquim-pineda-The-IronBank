package com.example.joaquimpinedatheironbank.entities.users;

import com.example.joaquimpinedatheironbank.enums.TypeOfUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountHolder extends User {
    @Embedded
    private UserAddress address;
    private String birthDate;
    private String firstName;
    private String lastName;

    public AccountHolder(String id, TypeOfUser typeOfUser, String Token, String email, String userName, UserAddress address, String birthDate, String firstName, String lastName) {
        super(id, typeOfUser, Token, email, userName);
        this.address = address;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
