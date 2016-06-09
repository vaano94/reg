package com.example.Config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

/**
 * Class for configuring JMS system.
 */
@Configuration
public class Jmsconfig {

    /**
     * ActiveMQ connection factory configuration.
     * Creates connection factory on localhost saying it's only persistent
     * during app lifecycle
     * @return instance of ActiveMQConnectionFactory
     */
    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(
                "vm://localhost?broker.persistent=false");
        activeMQConnectionFactory.setTrustAllPackages(true);
        return activeMQConnectionFactory;
    }

    /**
     * Defines SimpleJMSListenerContainerFactory.
     * @return an instance of factory
     */
    @Bean
    public JmsListenerContainerFactory<?> factory() {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        return factory;
    }


    /**
     * Defines jmsTemplate with destination address.
     * 'mailbox-destination' for current ActiveMQ configuration
     * @return instance of jmsTempate
     */
    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setDefaultDestination(new ActiveMQQueue("mailbox-destination"));
        return jmsTemplate;
    }


}
