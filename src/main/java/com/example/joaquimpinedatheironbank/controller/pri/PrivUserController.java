package com.example.joaquimpinedatheironbank.controller.pri;

import com.example.joaquimpinedatheironbank.config.KeycloakProvider;
import com.example.joaquimpinedatheironbank.dto.KeyClockUserRequest;
import com.example.joaquimpinedatheironbank.dto.UserAutorities;
import com.example.joaquimpinedatheironbank.entities.User;
import com.example.joaquimpinedatheironbank.enums.UserRoles;
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
import java.security.Principal;

@RestController
@RequestMapping("/pri/user")
public class PrivUserController {

    @Autowired
    EmailService emailService;

    @Autowired
    UserService userService;
    private final KeycloakAdminClientService kcAdminClient;

    private final KeycloakProvider kcProvider;


    public PrivUserController(KeycloakAdminClientService kcAdminClient, KeycloakProvider kcProvider) {
        this.kcProvider = kcProvider;
        this.kcAdminClient = kcAdminClient;
    }


    @PostMapping(value = "/create")
    public ResponseEntity<?> createUser(Principal principal, @RequestBody CreateUserRequest user) {

        UserAutorities autenticatedUser = kcAdminClient.getUser(principal.getName());

        if (user.getRole().equals(UserRoles.ADMINS.name().toUpperCase()) && autenticatedUser.getRole().stream().anyMatch(x -> x.equals(UserRoles.ADMINS.name()))) {
            Response createdResponse = kcAdminClient.createKeycloakUser(KeyClockUserRequest.from(user), UserRoles.ADMINS.name());
            System.out.println("admin");
            return ResponseEntity.status(createdResponse.getStatus()).build();
        } else if (user.getRole().equals(UserRoles.MANAGERS.name().toUpperCase())) {
            System.out.println("manager");
            Response createdResponse = kcAdminClient.createKeycloakUser(KeyClockUserRequest.from(user), UserRoles.MANAGERS.name());
            return ResponseEntity.status(createdResponse.getStatus()).build();
        }
        System.out.println("user");
        Response createdResponse = kcAdminClient.createKeycloakUser(KeyClockUserRequest.from(user), UserRoles.MEMBERS.name());
        return ResponseEntity.status(createdResponse.getStatus()).build();


    }


}
