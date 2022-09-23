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
public class AdminUser extends User {
    private String firstName;
    private String lastName;

    public AdminUser(String id, TypeOfUser typeOfUser, String Token, String email, String userName, String firstName, String lastName) {
        super(id, typeOfUser, Token, email, userName);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
