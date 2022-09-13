package com.example.joaquimpinedatheironbank.service;

import com.example.joaquimpinedatheironbank.entities.Email;

public interface EmailService {

    String SendSimpleMail (Email email) ;
    String sendMailWithAttachment (Email email) ;
}
