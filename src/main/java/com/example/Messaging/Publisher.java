package com.example.Messaging;

import com.example.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * ActiveMQ representation for sending messages.
 */
@Component
public class Publisher {
    /**
     * Autowired JmsTemplate.
     */
    @Autowired
    private JmsTemplate jmsTemplate;
    /**
     * Publishes current User for further action to ActiveMQ receiver.
     * @param user User user
     */
    public void publishMessage(User user) {
        MessageCreator messageCreator = new MessageCreator() {
            private final User finalUser = user;
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(finalUser);
            }
        };

        jmsTemplate.send("mailbox-destination", messageCreator);
    }


}
