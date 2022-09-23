package com.example.joaquimpinedatheironbank.entities.users;

import com.example.joaquimpinedatheironbank.http.requests.UserAddressRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor

public class UserAddress {
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;



}
