package com.example.joaquimpinedatheironbank.http.requests;

import com.example.joaquimpinedatheironbank.entities.users.UserAddress;
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

    public static UserAddress toUserAddress(UserAddressRequest userAddressRequest) {

        return new UserAddress(userAddressRequest.getStreet(),
                userAddressRequest.getCity(),
                userAddressRequest.getState(),
                userAddressRequest.getZipCode(),
                userAddressRequest.getCountry());

    }
}
