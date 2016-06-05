package com.example.App;


import com.example.Messaging.MailMessage;
import com.example.Messaging.SpringMailSenderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ivan on 05/06/16.
 */
@RestController
public class MailController {

    /*@Autowired
    MailSender sender;*/

    @RequestMapping("/message")
    public void sendMessage() {

        MailMessage mailMessage = new MailMessage();
        mailMessage.setSubject("hello");
        mailMessage.setBody("hellohello");
        mailMessage.setSender("vaano94@yahoo.com");
        mailMessage.setReplyTo("vaano94@gmail.com");
        SpringMailSenderService service = new SpringMailSenderService();
        service.sendMail(mailMessage);

    }

}
