package com.project.prjx;

import com.project.prjx.Data.Model.Entity.Notifications.NotificationTemplate;
import com.project.prjx.Data.Model.MessageType;
import com.project.prjx.Data.Repositories.NotificationTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class RunnerRun implements CommandLineRunner {
    @Autowired
    private NotificationTemplateRepository notificationTemplateRepository;

    @Override
    @Transactional
    public void run(String... args) {

        NotificationTemplate nt1 = new NotificationTemplate();
        nt1.setBody("Hello, $receiver_name$ here is your code $security_code$ and id $receiver_id$");
        nt1.setType(MessageType.ACTIVATION);
        nt1.setTitle("Activation code");

        NotificationTemplate nt2 = new NotificationTemplate();
        nt2.setBody("Hello, $receiver_name$ here is your reservation id $reservation_id$, this is a reminder $reservation_time$");
        nt2.setType(MessageType.REMINDER);
        nt2.setTitle("Reservation Reminder");

        NotificationTemplate nt3 = new NotificationTemplate();
        nt3.setBody("Hello, $receiver_name$ here is your password reset code $security_code$ and id $receiver_id$");
        nt3.setType(MessageType.PASSWORD_RESET);
        nt3.setTitle("Password Reset Code");

        NotificationTemplate nt4 = new NotificationTemplate();
        nt4.setBody("Hello, $receiver_name$ reservation with number $reservation_id$ has been cancelled $restaurant_name$");
        nt4.setType(MessageType.RESERVATION_CANCELLED);
        nt4.setTitle("Reservation Cancelled");

        NotificationTemplate nt5 = new NotificationTemplate();
        nt5.setBody("Hello, $receiver_name$ reservation with number $reservation_id$ has been confirmed $restaurant_name$ $number_of_seats$  $reservation_time$");
        nt5.setType(MessageType.RESERVATION_CONFIRMED);
        nt5.setTitle("Reservation Confirmed");

        notificationTemplateRepository.saveAll(List.of(nt1, nt2, nt3, nt4, nt5));
    }
}
