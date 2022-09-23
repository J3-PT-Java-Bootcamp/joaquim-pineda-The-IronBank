package com.example.joaquimpinedatheironbank.controller.pri;

import com.example.joaquimpinedatheironbank.config.KeycloakProvider;
import com.example.joaquimpinedatheironbank.dto.UserAutorities;
import com.example.joaquimpinedatheironbank.enums.UserRoles;
import com.example.joaquimpinedatheironbank.http.requests.CreateUserRequest;
import com.example.joaquimpinedatheironbank.service.email.EmailService;
import com.example.joaquimpinedatheironbank.service.keycloak.KeycloakAdminClientService;
import com.example.joaquimpinedatheironbank.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            Response createdResponse = kcAdminClient.createKeycloakUser(user, UserRoles.ADMINS.name());
            System.out.println("admin");
            return ResponseEntity.status(createdResponse.getStatus()).build();
        } else if (user.getRole().equals(UserRoles.MANAGERS.name().toUpperCase())) {
            System.out.println("manager");
            Response createdResponse = kcAdminClient.createKeycloakUser(user, UserRoles.MANAGERS.name());
            return ResponseEntity.status(createdResponse.getStatus()).build();
        }
        System.out.println("user");
        Response createdResponse = kcAdminClient.createKeycloakUser(user, UserRoles.MEMBERS.name());
        return ResponseEntity.status(createdResponse.getStatus()).build();


    }
    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers(Principal principal) {

            return ResponseEntity.status(200).body(userService.getAllUsers());

    }



}
