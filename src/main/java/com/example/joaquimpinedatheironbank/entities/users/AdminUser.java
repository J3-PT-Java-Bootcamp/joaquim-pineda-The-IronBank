package com.example.joaquimpinedatheironbank.entities.users;

import com.example.joaquimpinedatheironbank.entities.users.User;
import com.example.joaquimpinedatheironbank.entities.users.UserAddress;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
public class AdminUser extends User {
    @Embedded
    private UserAddress address;

    public AdminUser(String UUID, String name, String Token, String email,  UserAddress address, String userName) {
        super(UUID, name, Token, email,userName);
        this.address = address;
    }
}
