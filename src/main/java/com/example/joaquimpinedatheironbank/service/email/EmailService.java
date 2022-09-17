package com.example.joaquimpinedatheironbank.service.email;

import com.example.joaquimpinedatheironbank.entities.email.Email;

public interface EmailService {

    String SendSimpleMail (Email email) ;
    String sendMailWithAttachment (Email email) ;
}
