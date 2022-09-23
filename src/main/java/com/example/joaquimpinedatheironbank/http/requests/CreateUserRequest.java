package com.example.joaquimpinedatheironbank.http.requests;


import com.example.joaquimpinedatheironbank.entities.users.UserAddress;
import com.example.joaquimpinedatheironbank.enums.TypeOfUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {

    String id;
    String username;
    String password;
    String email;
    String firstName;
    String lastName;

    UserAddressRequest address;
    String role = "members";

    TypeOfUser typeOfUser;
    String hashedKey;
    String birthDate;




    public String getRole() {
        return role.toUpperCase();
    }

    public String ToString() {
        return "CreateUserRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", address=" + address.toString() +

                '}';
    }


}
