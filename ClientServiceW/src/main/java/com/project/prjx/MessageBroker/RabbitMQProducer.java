package com.project.prjx.MessageBroker;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(String queue, String message) {
        amqpTemplate.convertAndSend(queue, message);
        System.out.println("Sent: " + message);
    }
}

