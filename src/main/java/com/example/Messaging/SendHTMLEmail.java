package com.example.Messaging;

import com.example.Entity.User;
import com.example.RegApplication;
import com.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.Transport;

/**
 * Class responsible for composing html email and sending it to user.
 */
@Component
public class SendHTMLEmail {

    /**
     * Autowired instance of userService field.
     */
    @Autowired
    private UserService userService;
    /**
     * inputStream to read properties file.
     */
    private static InputStream input = null;

    /**
     * Main method responsible for sending a message.
     * Requires user and mail
     * @param message Message composed on a previous step.
     * @param user User sent from {@link Receiver} class
     */
    public void sendMessage(MailMessage message, User user)  {

        // Recipient's email ID needs to be mentioned.
        String recipient = message.getRecipient();

        // Sender's email ID needs to be mentioned
        String author = message.getAuthor();

        Properties prop = new Properties();
        String filename = "mail.properties";
        input = RegApplication.class.getClassLoader().getResourceAsStream(filename);
        if (input == null) {
            System.out.println("Sorry, unable to find " + filename);
            return;
        }

        try {
            prop.load(input);
        } catch (IOException e) {
            System.out.println("Could not load properties file");
        }
        String username = prop.getProperty("login");
        String password = prop.getProperty("password");

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message mail = new MimeMessage(session);

            // Set From: header field of the header.
            try {
                mail.setFrom(new InternetAddress(author));
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            // Set To: header field of the header.
            mail.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(message.getRecipient()));

            // Set Subject: header field
            mail.setSubject("3MonthJavaJunior");
            // Encrypt user email
            // get Encoded string
            String encryptedEmail = userService.createConfirmationLink(user);
            String link = "\"https://www.youtube.com/watch?v=dQw4w9WgXcQ\"";

            // Send the actual HTML message, as big as you like
            mail.setContent(
                    "<h3>You have successfully created an account on our website </h3>"
                    + "<h4>Your email is: " + message.getRecipient() + "</h4>"
                    + "<h4>and your pass ends on" + "********"
                    + user.getPassword().substring(user.getPassword().length() - 2) + "</h4>"
                    + "<p>To confirm your account please follow this link:"
                    + "<a href=\"http://localhost:8080/confirm/" + encryptedEmail + "\">Click me</a>"
                    + "</p>",
                    "text/html");

            // Send message
            Transport.send(mail);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            System.out.println("Something went wrong when sending message");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
