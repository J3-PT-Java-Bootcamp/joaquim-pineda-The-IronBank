package com.example.joaquimpinedatheironbank.http.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidateEmailRequest {

    String email;
    String token;



}