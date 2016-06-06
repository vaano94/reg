package com.example.Messaging;

import java.io.File;

import com.example.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

@Component
public class Receiver {

    /**
     * Get a copy of the application context
     */
    @Autowired
    ConfigurableApplicationContext context;

    /**
     * When receive a message, send email to user
     */
    @JmsListener(destination = "mailbox-destination", containerFactory = "factory")
    public void receiveMessage(User user) {

        System.out.println("RECEIVE MESSAGE TRIGGERED");
        MailMessage mailMessage = new MailMessage();
        mailMessage.setAuthor("vaano94@gmail.com");
        mailMessage.setRecipient(user.getEmail());
        System.out.println("EMAIL TO SEND: " + user.getEmail());

        String codedPass = user.getPassword();
        for (int i = 0; i < codedPass.length()-2; i++) {
            codedPass=codedPass.replace(codedPass.charAt(i),'*');
        }
        mailMessage.setPassfield(codedPass);
        //SendHTMLEmail.sendMessage(mailMessage);

    }
}