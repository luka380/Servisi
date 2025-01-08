package com.project.prjx.MessageBroker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {
    @RabbitListener(queues = "test-queue")
    public void receiveMessage(String message) {
        System.out.println("Received: " + message);
    }
}
