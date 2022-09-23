package com.example.joaquimpinedatheironbank.dto;

import com.example.joaquimpinedatheironbank.enums.UserRoles;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

public class UserAutorities {
    private String username;
    private final ArrayList<UserRoles> role;

    public UserAutorities(String username, ArrayList<UserRoles> role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }




    public ArrayList<UserRoles> getRole() {
        return role;
    }




}
