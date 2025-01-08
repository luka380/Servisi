package com.project.prjx.Services;

import com.project.prjx.Data.Mappers.TemplateParser;
import com.project.prjx.Data.Model.Dto.Notifications.NotificationData;
import com.project.prjx.Data.Model.Entity.Notifications.NotificationTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MailService {
    private final JavaMailSender mailSender;
    private final NotificationService notificationService;
    private final TemplateParser templateParser;

    public MailService(JavaMailSender mailSender, NotificationService notificationService, TemplateParser templateParser) {
        this.mailSender = mailSender;
        this.notificationService = notificationService;
        this.templateParser = templateParser;
    }

    public void sendEmail(NotificationData message) {

        NotificationTemplate template = notificationService.getTemplate(message.getType());

        if (template == null) {
            return;
        }

        String email = templateParser.parseTemplate(template.getBody(), message);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(message.getReceiverEmail());
        simpleMailMessage.setSubject(template.getTitle());
        simpleMailMessage.setText(email);
        mailSender.send(simpleMailMessage);
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
