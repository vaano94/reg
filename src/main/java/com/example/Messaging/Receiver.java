package com.example.Messaging;


import com.example.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * ActiveMQ representation of message receiver.
 */
@Component
public class Receiver {

    /**
     * Autowired copy of the application context.
     */
    @Autowired
    private ConfigurableApplicationContext context;
    /**
     * Autowired instance of emailComposer.
     */
    @Autowired
    private SendHTMLEmail emailComposer;

    /**
     * When receive a message, send email to user.
     * @param user User received from {@link Publisher} class
     */
    @JmsListener(destination = "mailbox-destination", containerFactory = "factory")
    public void receiveMessage(User user) {

        System.out.println("RECEIVE MESSAGE TRIGGERED");
        MailMessage mailMessage = new MailMessage();
        mailMessage.setAuthor("vaano94@gmail.com");
        mailMessage.setRecipient(user.getEmail());
        emailComposer.sendMessage(mailMessage, user);

    }
}
