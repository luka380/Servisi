package com.project.prjx.Services;

import com.project.prjx.Data.Model.Dto.Notifications.NotificationData;
import com.project.prjx.Data.Model.Dto.Users.BaseUserDto;
import com.project.prjx.Data.Model.Entity.Notifications.*;
import com.project.prjx.Data.Model.Entity.Restaurants.Reservation;
import com.project.prjx.Data.Model.Entity.Restaurants.Restaurant;
import com.project.prjx.Data.Model.Entity.Users.BaseUser;
import com.project.prjx.Data.Model.MessageType;
import com.project.prjx.Data.Repositories.BaseNotificationRepository;
import com.project.prjx.Data.Repositories.NotificationTemplateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class NotificationService {
    private final BaseNotificationRepository notificationRepository;
    private final NotificationTemplateRepository notificationTemplateRepository;

    public NotificationService(BaseNotificationRepository notificationRepository, NotificationTemplateRepository notificationTemplateRepository) {
        this.notificationRepository = notificationRepository;
        this.notificationTemplateRepository = notificationTemplateRepository;
    }

    public NotificationTemplate getTemplate(MessageType type) {
        return notificationTemplateRepository.findNotificationTemplateByType(type);
    }

    public NotificationData save(NotificationData notificationData) {
        BaseUser user = new BaseUser();
        user.setId(UUID.fromString(notificationData.getReceiverId()));
        BaseNotification baseNotification = switch (notificationData.getType()) {
            case ACTIVATION, PASSWORD_RESET -> VerificationNotification.builder()
                    .receiverId(user)
                    .verificationCode(notificationData.getSecurityCode())
                    .type(notificationData.getType())
                    .isRead(false)
                    .build();
            case RESERVATION_CANCELLED, RESERVATION_CONFIRMED -> ReservationNotification.builder()
                    .receiverId(user)
                    .isRead(false)
                    .type(notificationData.getType())
                    .numberOfSeats(notificationData.getNumberOfSeats())
                    .reservationId(Reservation.builder().id(Integer.parseInt(notificationData.getReservationId())).build())
                    .restaurantId(Restaurant.builder().id(Integer.parseInt(notificationData.getRestaurantId())).build())
                    .build();
            case REMINDER -> ReminderNotification.builder()
                    .isRead(false)
                    .receiverId(user)
                    .type(notificationData.getType())
                    .build();
        };

        notificationRepository.save(baseNotification);
        return notificationData;
    }

    public List<BaseNotification> getByFilter(MessageType type, LocalDateTime startDate, LocalDateTime endDate, String email, BaseUserDto user) {
        return notificationRepository.findByCriteria(String.valueOf(type), startDate, endDate, email, user.getId());
    }

    public List<NotificationData> getAll() {
        return List.of();
    }

    public List<NotificationData> getAll(BaseUserDto user) {
        return List.of();
    }

    public List<NotificationData> getUnread(BaseUserDto user) {
        return List.of();
    }

    public void markAsRead(BaseNotification baseNotification) {

    }

    public void markAsRead(List<BaseNotification> baseNotifications) {

    }
}
