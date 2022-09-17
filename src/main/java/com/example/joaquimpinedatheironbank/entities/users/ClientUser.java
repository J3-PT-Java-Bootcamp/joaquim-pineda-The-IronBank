package com.example.joaquimpinedatheironbank.entities.users;

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
public class ClientUser extends User {
    @Embedded
    private UserAddress address;

    public ClientUser(String UUID, String name, String Token, String email, UserAddress address, String userName) {
        super(UUID, name, Token, email,userName);
        this.address = address;
    }
}
