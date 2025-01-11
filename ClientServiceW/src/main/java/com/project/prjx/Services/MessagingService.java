package com.project.prjx.Services;

import com.project.prjx.MessageBroker.RabbitMQProducer;
import org.springframework.stereotype.Service;

@Service
public class MessagingService {
    private final RabbitMQProducer rabbitMQProducer;

    public MessagingService(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }

    public void sendAsyncMessage(Object message) {
        rabbitMQProducer.sendMessage("notificationQueue", message);
    }
}
