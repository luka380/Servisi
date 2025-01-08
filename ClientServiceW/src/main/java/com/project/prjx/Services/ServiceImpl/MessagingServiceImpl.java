package com.project.prjx.Services.ServiceImpl;

import com.project.prjx.MessageBroker.RabbitMQProducer;
import org.springframework.stereotype.Service;

@Service
public class MessagingServiceImpl {
    private final RabbitMQProducer rabbitMQProducer;

    public MessagingServiceImpl(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }

    public void sendAsyncMessage(String message){
        rabbitMQProducer.sendMessage("notifications", message);
    }
}
