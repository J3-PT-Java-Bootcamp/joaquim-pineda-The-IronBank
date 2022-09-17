package com.example.joaquimpinedatheironbank.http.requests;


import com.example.joaquimpinedatheironbank.entities.users.UserAddress;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    String username;
    String password;
    String email;
    String firstname;
    String lastname;

    UserAddressRequest address;
    String role = "members";


    public String getRole() {
        return role.toUpperCase();
    }

    public String ToString() {
        return "CreateUserRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address=" + address.toString() +

                '}';
    }
}
