package com.example.joaquimpinedatheironbank.service.user;

import com.example.joaquimpinedatheironbank.entities.users.User;
import com.example.joaquimpinedatheironbank.entities.users.UserAddress;
import com.example.joaquimpinedatheironbank.enums.TypeOfUser;

public interface UserService {

    void create(String id, String token, String email, String userName, TypeOfUser typeOfUser, String hashedKey, UserAddress address, String birthDate, String firstName, String lastName);

    User findByEmail(String email);

    void save(User user);

    User findUserById(String id);

}
