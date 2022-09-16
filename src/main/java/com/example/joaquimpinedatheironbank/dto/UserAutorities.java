package com.example.joaquimpinedatheironbank.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

public class UserAutorities {
    private String username;
    private ArrayList<String> role;

    public UserAutorities(String username, ArrayList<String> role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }




    public ArrayList<String> getRole() {
        return role;
    }




}
