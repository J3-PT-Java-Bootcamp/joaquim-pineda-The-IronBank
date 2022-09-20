package com.example.joaquimpinedatheironbank.service.email;

import com.example.joaquimpinedatheironbank.entities.email.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired private JavaMailSender javaMailSender;



    @Value("${spring.mail.username}") private String sender;



    @Override
    public String SendSimpleMail(Email email) {
        try {
            MimeMessage mimeMessage
                    = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper;
            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            try {

                // Setting multipart as true for attachments to
                // be send
                mimeMessageHelper
                        = new MimeMessageHelper(mimeMessage, true);
                mimeMessageHelper.setFrom(sender);
                mimeMessageHelper.setTo(email.getRecipient());
                mimeMessageHelper.setText(email.getMsgBody(), true);
                mimeMessageHelper.setSubject(
                        email.getSubject());

                // Adding the attachment

                // Sending the mail
                javaMailSender.send(mimeMessage);
                return "Mail sent Successfully";
            }

            // Catch block to handle MessagingException
            catch (MessagingException e) {

                // Display message when exception occurred
                return "Error while sending mail!!!";
            }


        }catch (Exception e) {
            return "Error while sending mail!!!";
        }
    }

    @Override
    public String sendMailWithAttachment(Email email) {
        // Creating a mime message
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            // Setting multipart as true for attachments to
            // be send
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(email.getRecipient());
            mimeMessageHelper.setText(email.getMsgBody());
            mimeMessageHelper.setSubject(
                    email.getSubject());

            // Adding the attachment
            FileSystemResource file
                    = new FileSystemResource(
                    new File(email.getAttachment()));

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }
}
