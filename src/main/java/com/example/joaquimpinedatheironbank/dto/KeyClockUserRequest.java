package com.example.joaquimpinedatheironbank.dto;


import com.example.joaquimpinedatheironbank.http.requests.CreateUserRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class KeyClockUserRequest {
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String password;

    public static KeyClockUserRequest from(CreateUserRequest createUserRequest) {
        return new KeyClockUserRequest(
                createUserRequest.getUsername(),
                createUserRequest.getEmail(),
                createUserRequest.getFirstname(),
                createUserRequest.getLastname(),
                createUserRequest.getPassword()
        );
    }
}
