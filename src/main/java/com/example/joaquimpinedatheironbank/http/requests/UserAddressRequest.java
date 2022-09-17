package com.example.joaquimpinedatheironbank.http.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAddressRequest {
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
