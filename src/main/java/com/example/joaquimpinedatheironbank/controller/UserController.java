package com.example.joaquimpinedatheironbank.controller;

import com.example.joaquimpinedatheironbank.config.KeycloakProvider;
import com.example.joaquimpinedatheironbank.entities.User;
import com.example.joaquimpinedatheironbank.http.requests.CreateUserRequest;
import com.example.joaquimpinedatheironbank.http.requests.LoginRequest;
import com.example.joaquimpinedatheironbank.http.requests.ValidateEmailRequest;
import com.example.joaquimpinedatheironbank.service.EmailService;
import com.example.joaquimpinedatheironbank.service.KeycloakAdminClientService;
import com.example.joaquimpinedatheironbank.service.UserService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    EmailService emailService;

    @Autowired
    UserService userService;
    private final KeycloakAdminClientService kcAdminClient;

    private final KeycloakProvider kcProvider;


    public UserController(KeycloakAdminClientService kcAdminClient, KeycloakProvider kcProvider) {
        this.kcProvider = kcProvider;
        this.kcAdminClient = kcAdminClient;
    }


    @PostMapping(value = "/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest user) {
        System.out.println(user.ToString());
        Response createdResponse = kcAdminClient.createKeycloakUser(user, "members");
        return ResponseEntity.status(createdResponse.getStatus()).build();

    }


    @GetMapping(value = "validate")
    public ResponseEntity<?> validateEmail(@RequestParam String email, @RequestParam String token) {
        ValidateEmailRequest validateEmailRequest = new ValidateEmailRequest(email, token);
        System.out.println(validateEmailRequest.getEmail());
        System.out.println(validateEmailRequest.getToken());
        User user = userService.findByEmail(validateEmailRequest.getEmail());
        if (user != null) {
            if (user.getToken() == null) {
                return ResponseEntity.status(HttpStatus.OK).build();
            } else if (user.getToken().equals(validateEmailRequest.getToken())) {
                System.out.println("Token correcto");
                user.setToken(null);
/*
                userService.save(user);
*/
                Response createdResponse = kcAdminClient.validateEmail(validateEmailRequest);
                return ResponseEntity.status(createdResponse.getStatus()).build();
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/get-token")
    public ResponseEntity<AccessTokenResponse> login(@NotNull @RequestBody LoginRequest loginRequest) {
        Keycloak keycloak = kcProvider.newKeycloakBuilderWithPasswordCredentials(loginRequest.getUsername(), loginRequest.getPassword()).build();

        AccessTokenResponse accessTokenResponse = null;
        try {
            accessTokenResponse = keycloak.tokenManager().getAccessToken();
            return ResponseEntity.status(HttpStatus.OK).body(accessTokenResponse);
        } catch (BadRequestException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(accessTokenResponse);
        }

    }
}
