package com.example.email;

import com.example.Entity.User;
import com.example.Messaging.MailMessage;
import com.example.Messaging.SendHTMLEmail;
import com.example.RegApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.mail.MessagingException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RegApplication.class)
@WebAppConfiguration
public class emailTest {

    @Autowired
    SendHTMLEmail sender;

    @Test
    public void EmailSendWithMessageTest() {
        sender = Mockito.mock(SendHTMLEmail.class);
        User user = new User();
        user.setEmail("vaano94@yahoo.com");
        user.setPassword("v123123!!12");
        MailMessage mailMessage = new MailMessage();
        mailMessage.setAuthor("vaano94@gmail.com");
        mailMessage.setRecipient("vaano94@gmail.com");
        mailMessage.setSubject("3MonthJavaJunior");

        sender.sendMessage(mailMessage, user);
        verify(sender, times(1)).sendMessage(mailMessage,user);

    }

    /*@Test(expected = NullPointerException.class)
    public void EmailSentWithNullUserTest()
    {
        User user = null;
        MailMessage mailMessage = new MailMessage ();
        mailMessage.setAuthor (user.getEmail ());
        sender.sendMessage (mailMessage,user);
    }*/

    /*@Test(expected = MessagingException.class)
    public void EmailSentWithWrongUserTest() {
        User user = new User ();
        MailMessage mailMessage = new MailMessage ();
        mailMessage.setRecipient("abc@mail.com");
        mailMessage.setAuthor("");
        sender.sendMessage (mailMessage,user);
    }*/

}
