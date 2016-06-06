package com.example.Messaging;

import com.example.RegApplication;
import org.springframework.util.Base64Utils;

import javax.crypto.SecretKey;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class SendHTMLEmail {

    private static InputStream input = null;
    private SecretKey key;

    public static void sendMessage(MailMessage message)  {

        // Recipient's email ID needs to be mentioned.
        String recipient = message.getRecipient();

        // Sender's email ID needs to be mentioned
        String author = message.getAuthor();

        Properties prop = new Properties();
        String filename = "mail.properties";
        input = RegApplication.class.getClassLoader().getResourceAsStream(filename);
        if(input==null){
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
            mail.setFrom(new InternetAddress(author));

            // Set To: header field of the header.
            mail.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("vaano94@gmail.com"));

            // Set Subject: header field
            mail.setSubject("3MonthJavaJunior");
            // Encrypt user email
            // get Encoded string
            String encryptedEmail = Base64Utils.encodeToString((message.getPassfield()+"|"+message.getRecipient()).getBytes());

            String link = "\"https://www.youtube.com/watch?v=dQw4w9WgXcQ\"";

            // Send the actual HTML message, as big as you like
            mail.setContent(
                    "<h3>You have successfully created an account on our website </h3>"+
                    "<h4>Your email is: +"+message.getRecipient()+"</h4>"+
                    "<h4>and your pass ends on"+message.getPassfield()+"</h4>"+
                    "<p>To confirm your account please follow this link:<a href=localhost:8080/confirm/|"+encryptedEmail+"</a>Click me</p>",

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

    public SecretKey getKey() {
        return key;
    }

    public void setKey(SecretKey key) {
        this.key = key;
    }
}