package com.example.Messaging;

import com.example.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by ivan on 05/06/16.
 */
@Component
public class Publisher {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void publishMessage(User user) {

        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(user);
            }
        };

        //  JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        System.out.println("Sending user over to email-sender");
        jmsTemplate.send("mailbox-destination", messageCreator);
    }


}
