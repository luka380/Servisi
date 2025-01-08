package com.project.prjx;

import com.project.prjx.Data.Repositories.EmailsRepository;
import com.project.prjx.Data.Repositories.NotificationTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RunnerRun implements CommandLineRunner {
    @Autowired
    private NotificationTemplateRepository notificationTemplateRepository;
    @Autowired
    private EmailsRepository emailsRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
//        Client client = Client.builder()
//                .phoneNumber(34342342)
//                .role("user")
//                .birthday(LocalDateTime.now())
//                .firstName("LL")
//                .lastName("LL")
//                .username("luka26597")
//                .isEnabled(true)
//                .password("333333")
//                .id(null).build();
//
//        client = clientRepository.save(client);
//
//        Email em = emailsRepository.save(new Email(null, "luka26597@gmail.com", false, client));
//
//        client.setEmail(em);
//        client = clientRepository.save(client);
//
//        NotificationTemplate nt = new NotificationTemplate();
//        nt.setBody("Hello, $receiver_name$ here is your code $security_code$ and id $receiver_id$");
//        nt.setType(MessageType.ACTIVATION);
//        nt.setTitle("Activation code");
//
//        notificationTemplateRepository.save(nt);
//
//        NotificationTemplate nt1 = new NotificationTemplate();
//        nt1.setBody("Hello, $receiver_name$ here is your reservation id $reservation_id$");
//        nt1.setType(MessageType.REMINDER);
//        nt1.setTitle("Activation code");
//
//        notificationTemplateRepository.save(nt1);
//
//        NotificationData data = NotificationData.builder()
//                .type(MessageType.ACTIVATION)
//                .receiverEmail("luka26597@gmail.com")
//                .receiverName(client.getFirstName())
//                .receiverId(client.getId().toString())
//                .receiverPhone(client.getPhoneNumber().toString())
//                .securityCode("fdfdfdfd")
//                .build();
//
//        rabbitMQProducer.sendMessage("notificationQueue", data);
    }
}
