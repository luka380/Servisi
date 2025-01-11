package com.project.prjx.Services;

import com.project.prjx.Data.Model.Dto.Notifications.NotificationData;
import com.project.prjx.Data.Model.Dto.Users.BaseUserDto;
import com.project.prjx.Data.Model.Dto.Users.EmailDto;
import com.project.prjx.MessageBroker.RabbitMQProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;

@Service
public class MessageService {
    private final RestTemplate restTemplate;
    private final RabbitMQProducer rabbitMQProducer;


    public MessageService(RestTemplate restTemplate, RabbitMQProducer rabbitMQProducer) {
        this.restTemplate = restTemplate;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    public BaseUserDto getUser(String userId, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = "http://localhost:8081/userservice/account/";
        ResponseEntity<Map> unfinished = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Map.class
        );

        Map<String, String> response = unfinished.getBody();
        if(response == null)
            return null;

        return BaseUserDto.builder()
                .id(UUID.fromString(response.get("id")))
                .username(response.get("username"))
                .email(new EmailDto(0, response.get("email"), response.get("email_verified").equals("true")))
                .role(response.get("role"))
                .birthday(null)
                .firstName(response.get("firstName"))
                .lastName(response.get("lastName"))
                .password(null)
                .phoneNumber(null)
                .isEnabled(response.get("enabled").equals("true"))
                .build();
    }

    public void sendNotification(NotificationData data){
        rabbitMQProducer.sendMessage("notificationQueue", data);
    }
}
