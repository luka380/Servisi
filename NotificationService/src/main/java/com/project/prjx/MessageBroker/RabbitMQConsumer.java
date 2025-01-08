package com.project.prjx.MessageBroker;

import com.project.prjx.Data.Model.Dto.Notifications.NotificationData;
import com.project.prjx.Services.MailService;
import com.project.prjx.Services.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {
    private final NotificationService notificationService;
    private final MailService mailService;

    public RabbitMQConsumer(NotificationService notificationService, MailService mailService) {
        this.notificationService = notificationService;
        this.mailService = mailService;
    }

    @RabbitListener(queues = "notificationQueue")
    public void receiveMessage(NotificationData message) {
        notificationService.save(message);
        mailService.sendEmail(message);
    }
}
