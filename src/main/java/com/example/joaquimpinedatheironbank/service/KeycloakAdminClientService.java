package com.example.joaquimpinedatheironbank.service;


import com.example.joaquimpinedatheironbank.config.KeycloakProvider;
import com.example.joaquimpinedatheironbank.entities.Email;
import com.example.joaquimpinedatheironbank.entities.User;
import com.example.joaquimpinedatheironbank.http.requests.CreateUserRequest;
import com.example.joaquimpinedatheironbank.http.requests.ValidateEmailRequest;
import lombok.extern.java.Log;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Service
@Log
public class KeycloakAdminClientService {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    private final KeycloakProvider kcProvider;
    @Value("${keycloak.realm}")
    public String realm;
    @Value(("${keycloak.resource}"))
    public String clientId;


    public KeycloakAdminClientService(KeycloakProvider keycloakProvider) {
        this.kcProvider = keycloakProvider;
    }

    private static CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

    public Response createKeycloakUser(CreateUserRequest user, String group) {
        var adminKeycloak = kcProvider.getInstance();
        UsersResource usersResource = kcProvider.getInstance().realm(realm).users();
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(user.getPassword());

        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(user.getEmail());
        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
        kcUser.setFirstName(user.getFirstname());
        kcUser.setLastName(user.getLastname());
        kcUser.setEmail(user.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);

//        Change this to change the group logic
        kcUser.setGroups(List.of(group));


        Response response = usersResource.create(kcUser);

        if (response.getStatus() == 201) {
            List<UserRepresentation> userList = adminKeycloak.realm(realm).users().search(kcUser.getUsername()).stream()
                    .filter(userRep -> userRep.getUsername().equals(kcUser.getUsername())).toList();
            var createdUser = userList.get(0);
            log.info("User with id: " + createdUser.getId() + " created");
            User userEntinty = new User(createdUser.getId(), createdUser.getFirstName(), createdUser.getEmail(), UUID.randomUUID().toString());
            userService.create(userEntinty);
            Email email = new Email(user.getEmail(), "<html><body>please validate email <a href=\"http://localhost:8099/user/validate?email=" + userEntinty.getEmail() + "&token=" + userEntinty.getToken() + "\">Validate</a><body></html> ", "Validate Email", "");
            emailService.SendSimpleMail(email);
//            TODO you may add you logic to store and connect the keycloak user to the local user here

        }

        return response;

    }


    public Response validateEmail(ValidateEmailRequest user) {
        var adminKeycloak = kcProvider.getInstance();
        UsersResource usersResource = kcProvider.getInstance().realm(realm).users();
        UserRepresentation test = usersResource.search(user.getEmail()).get(0);
        test.setEmailVerified(true);
        usersResource.get(test.getId()).update(test);
        return Response.ok().build();
    }
}