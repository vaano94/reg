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

    @Autowired
    SendHTMLEmail emailComposer;

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
        mailMessage.setPassfield(user.getPassword());
        emailComposer.sendMessage(mailMessage, user);

    }
}