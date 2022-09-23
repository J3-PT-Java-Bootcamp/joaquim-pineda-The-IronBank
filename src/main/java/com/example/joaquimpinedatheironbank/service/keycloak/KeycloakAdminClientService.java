package com.example.joaquimpinedatheironbank.service.keycloak;


import com.example.joaquimpinedatheironbank.config.KeycloakProvider;
import com.example.joaquimpinedatheironbank.constants.constants;
import com.example.joaquimpinedatheironbank.dto.KeyClockUserRequest;
import com.example.joaquimpinedatheironbank.dto.UserAutorities;
import com.example.joaquimpinedatheironbank.entities.email.Email;
import com.example.joaquimpinedatheironbank.entities.users.AccountHolder;
import com.example.joaquimpinedatheironbank.entities.users.User;
import com.example.joaquimpinedatheironbank.entities.users.UserAddress;
import com.example.joaquimpinedatheironbank.enums.TypeOfUser;
import com.example.joaquimpinedatheironbank.enums.UserRoles;
import com.example.joaquimpinedatheironbank.http.requests.CreateUserRequest;
import com.example.joaquimpinedatheironbank.service.email.EmailService;
import com.example.joaquimpinedatheironbank.service.user.UserService;
import lombok.extern.java.Log;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
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

    public Response createKeycloakUser(CreateUserRequest user1, String group) {
        KeyClockUserRequest userRequest = KeyClockUserRequest.from(user1);
        var adminKeycloak = kcProvider.getInstance();
        UsersResource usersResource = kcProvider.getInstance().realm(realm).users();
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(userRequest.getPassword());

        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(userRequest.getUsername());
        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
        kcUser.setFirstName(userRequest.getFirstname());
        kcUser.setLastName(userRequest.getLastname());
        kcUser.setEmail(userRequest.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);
        kcUser.setGroups(List.of(group));


        Response response = usersResource.create(kcUser);

        if (response.getStatus() == 201) {
            List<UserRepresentation> userList = adminKeycloak.realm(realm).users().search(kcUser.getUsername());
            var createdUser = userList.get(0);
            log.info("User with id: " + createdUser.getId() + " created");
            String token = UUID.randomUUID().toString();

            AccountHolder userEntinty = new AccountHolder();
            userEntinty.setId(createdUser.getId());
            userEntinty.setToken(token);
            userEntinty.setEmail(userRequest.getEmail());
            userEntinty.setFirstName(userRequest.getFirstname());
            userEntinty.setLastName(userRequest.getLastname());
            userEntinty.setUserName(userRequest.getUsername());
            userEntinty.setAddress(new UserAddress());
            userEntinty.setBirthDate("01/01/2000");
            userEntinty.setTypeOfUser(TypeOfUser.ADMIN);

            userService.create(
                    userEntinty.getId(),
                   token,
                    user1.getEmail(),
                    user1.getUsername(),
                    user1.getTypeOfUser(),
                    user1.getHashedKey(),
                    user1.getAddress(),
                    user1.getBirthDate(),
                    user1.getFirstName(),
                    user1.getLastName()
            );
            Email email = new Email(user1.getEmail(), "<html><body>please validate email <a href=\"" + constants.URL + "/user/validate?email=" + userEntinty.getEmail() + "&token=" + token + "\">Validate</a><body></html> ", "Validate Email", "");
            emailService.SendSimpleMail(email);
//            TODO you may add you logic to store and connect the keycloak user to the local user here

        }

        return response;

    }


    public Response validateEmail(User user) {
        var adminKeycloak = kcProvider.getInstance();
        UsersResource usersResource = kcProvider.getInstance().realm(realm).users();
        UserRepresentation test = usersResource.search(user.getUserName()).get(0);
        test.setEmailVerified(true);
        usersResource.get(test.getId()).update(test);
        return Response.ok().build();
    }

    public UserAutorities getUser(String userId) {
        ArrayList<String> roles = new ArrayList<>();
        var adminKeycloak = kcProvider.getInstance();
        UsersResource usersResource = kcProvider.getInstance().realm(realm).users();
        var pepito = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        pepito.forEach(temp -> {
            switch (temp.getAuthority()) {
                case "ROLE_admin":
                    roles.add(UserRoles.ADMINS.name());
                    break;
                case "ROLE_moderator":
                    roles.add(UserRoles.MANAGERS.name());
                    break;
                case "ROLE_member":
                    roles.add(UserRoles.MEMBERS.name());
                    break;
            }
        });

        return new UserAutorities(userId, roles);


    }
}