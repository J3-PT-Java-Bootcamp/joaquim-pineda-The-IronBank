package com.example.joaquimpinedatheironbank.controller.pub;

import com.example.joaquimpinedatheironbank.config.KeycloakProvider;
import com.example.joaquimpinedatheironbank.entities.users.User;
import com.example.joaquimpinedatheironbank.enums.TypeOfUser;
import com.example.joaquimpinedatheironbank.enums.UserRoles;
import com.example.joaquimpinedatheironbank.http.requests.CreateUserRequest;
import com.example.joaquimpinedatheironbank.http.requests.LoginRequest;
import com.example.joaquimpinedatheironbank.http.requests.ValidateEmailRequest;
import com.example.joaquimpinedatheironbank.service.email.EmailService;
import com.example.joaquimpinedatheironbank.service.keycloak.KeycloakAdminClientService;
import com.example.joaquimpinedatheironbank.service.user.UserService;
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

public class PubUserController {

    @Autowired
    EmailService emailService;

    @Autowired
    UserService userService;
    private final KeycloakAdminClientService kcAdminClient;

    private final KeycloakProvider kcProvider;


    public PubUserController(KeycloakAdminClientService kcAdminClient, KeycloakProvider kcProvider) {
        this.kcProvider = kcProvider;
        this.kcAdminClient = kcAdminClient;
    }


    @PostMapping(value = "/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest user) {
        user.setTypeOfUser(TypeOfUser.ACCOUNT_HOLDER);
        Response createdResponse = kcAdminClient.createKeycloakUser(user, UserRoles.MEMBERS.name());
        return ResponseEntity.status(createdResponse.getStatus()).build();
    }


    @GetMapping(value = "validate")
    public ResponseEntity<?> validateEmail(@RequestParam String email, @RequestParam String token) {
        try{
            ValidateEmailRequest validateEmailRequest = new ValidateEmailRequest(email, token);
            System.out.println(validateEmailRequest.getEmail());
            System.out.println(validateEmailRequest.getToken());
            User user = userService.findByEmail(validateEmailRequest.getEmail());

            if (user != null) {
                if (user.getToken() == null) {
                    return ResponseEntity.status(HttpStatus.OK).body("Email already validated");
                } else if (user.getToken().equals(validateEmailRequest.getToken())) {
                    System.out.println("Token correcto");
                    user.setToken(null);
                    userService.save(user);
                    Response createdResponse = kcAdminClient.validateEmail(user);
                    return ResponseEntity.status(createdResponse.getStatus()).build();
                }
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/get-token")
    public ResponseEntity<AccessTokenResponse> login(@NotNull @RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest.getUsername());
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
