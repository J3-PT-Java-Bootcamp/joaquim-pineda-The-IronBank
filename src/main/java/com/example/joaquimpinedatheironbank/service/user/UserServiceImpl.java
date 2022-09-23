package com.example.joaquimpinedatheironbank.service.user;

import com.example.joaquimpinedatheironbank.entities.users.*;
import com.example.joaquimpinedatheironbank.enums.TypeOfUser;
import com.example.joaquimpinedatheironbank.repository.users.AccountHolderRepository;
import com.example.joaquimpinedatheironbank.repository.users.AdminUserRepository;
import com.example.joaquimpinedatheironbank.repository.users.ThirdPartyUserRepository;
import com.example.joaquimpinedatheironbank.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private AdminUserRepository adminUserRepository;
    @Autowired
    private ThirdPartyUserRepository thirdPartyUserRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void create(String id, String token, String email, String userName, TypeOfUser typeOfUser, String hashedKey, UserAddress address, String birthDate, String firstName, String lastName) {

        switch (typeOfUser) {
            case ACCOUNT_HOLDER:
                AccountHolder accountHolder = new AccountHolder(id, typeOfUser, token, email, userName, address, birthDate, firstName, lastName);
                accountHolderRepository.save(accountHolder);
                break;
            case ADMIN:
                AdminUser adminUser = new AdminUser(id, typeOfUser, token, email, userName, firstName, lastName);
                adminUserRepository.save(adminUser);
                break;

            case THIRD_PARTY:
                ThirdPartyUser thirdPartyUser = new ThirdPartyUser(id, typeOfUser, token, email, userName, hashedKey);
                thirdPartyUserRepository.save(thirdPartyUser);
                break;
            default:
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User type not valid");
        }


    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserById(String autenticatedUser) throws HttpClientErrorException {
        try {
            return userRepository.findById(autenticatedUser).get();
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}


