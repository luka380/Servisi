package com.project.prjx.Services;

import com.project.prjx.Data.Model.Dto.Notifications.NotificationData;
import com.project.prjx.Data.Model.Entity.Notifications.ReminderNotification;
import com.project.prjx.Data.Model.Entity.Restaurants.Reservation;
import com.project.prjx.Data.Model.MessageType;
import com.project.prjx.Data.Repositories.BaseNotificationRepository;
import com.project.prjx.Data.Repositories.ReservationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SchedulerService {
    private final MailService mailService;
    private final ReservationRepository reservationRepository;
    private final BaseNotificationRepository notificationRepository;

    public SchedulerService(MailService mailService, ReservationRepository reservationRepository, BaseNotificationRepository notificationRepository) {
        this.mailService = mailService;
        this.reservationRepository = reservationRepository;
        this.notificationRepository = notificationRepository;
    }


    //    @Scheduled(cron = "0 0/15 * * * *")
    @Scheduled(cron = "0 * * * * *")
    public void reminderNotification() {
        LocalDateTime currentTime = LocalDateTime.now();
        List<Reservation> reservations = reservationRepository.findReservationsByStartDateBetween(currentTime.plusMinutes(59), currentTime.plusMinutes(61));

        System.out.println("hey!!!");

        for (Reservation reservation : reservations) {
            ReminderNotification reminderNotification = ReminderNotification.builder()
                    .type(MessageType.REMINDER)
                    .receiverId(reservation.getClient())
                    .isRead(false)
                    .build();

            notificationRepository.save(reminderNotification);
            mailService.sendEmail(NotificationData.builder()
                    .receiverName(reservation.getClient().getLastName())
                    .receiverEmail(reservation.getClient().getEmail().getEmail())
                    .receiverId(reservation.getClient().getId().toString())
                    .reservationId(String.valueOf(reservation.getId()))
                    .receiverPhone(reservation.getClient().getPhoneNumber().toString())
                    .numberOfSeats(reservation.getNumOfGuests())
                    .restaurantId(String.valueOf(reservation.getRestaurant().getId()))
                    .build());
        }
    }
}
