package com.project.prjx.Data.Mappers;

import com.project.prjx.Data.Model.Dto.Notifications.NotificationData;
import com.project.prjx.Data.Model.Entity.Notifications.BaseNotification;
import com.project.prjx.Data.Model.Entity.Notifications.ReminderNotification;
import com.project.prjx.Data.Model.Entity.Notifications.ReservationNotification;
import com.project.prjx.Data.Model.Entity.Notifications.VerificationNotification;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Mappers {
    public static NotificationMapper NotificationMapper = new NotificationMapper();

    public static class NotificationMapper implements Mapper<NotificationData, BaseNotification> {
        @Override
        public BaseNotification map(NotificationData object) {
            BaseNotification baseNotification = switch (object.getType()) {
                case ACTIVATION, PASSWORD_RESET -> VerificationNotification.builder()
                        .isRead(object.getIsRead())
                        .type(object.getType())
                        .verificationCode(object.getSecurityCode())
                        .receiverId(UUID.fromString(object.getReceiverId()))
                        .receiverEmail(object.getReceiverEmail())
                        .receiverPhone(object.getReceiverPhone())
                        .senderName(object.getSenderName())
                        .receiverName(object.getReceiverName())
                        .restaurantName(object.getRestaurantName())
                        .restaurantAddress(object.getRestaurantAddress())
                        .reservationTime(object.getReservationTime())
                        .reservationDate(object.getReservationDate())
                        .reservationStatus(object.getReservationStatus())
                        .build();
                case RESERVATION_CONFIRMED, RESERVATION_CANCELLED -> ReservationNotification.builder()
                        .isRead(object.getIsRead())
                        .type(object.getType())
                        .numberOfSeats(object.getNumberOfSeats())
                        .restaurantId(Integer.parseInt(object.getRestaurantId()))
                        .receiverId(UUID.fromString(object.getReceiverId()))
                        .restaurantName(object.getRestaurantName())
                        .restaurantAddress(object.getRestaurantAddress())
                        .reservationTime(object.getReservationTime())
                        .reservationDate(object.getReservationDate())
                        .reservationStatus(object.getReservationStatus())
                        .receiverEmail(object.getReceiverEmail())
                        .receiverPhone(object.getReceiverPhone())
                        .receiverName(object.getReceiverName())
                        .senderName(object.getSenderName())
                        .build();
                case REMINDER -> ReminderNotification.builder()
                        .isRead(object.getIsRead())
                        .type(object.getType())
                        .receiverId(UUID.fromString(object.getReceiverId()))
                        .receiverEmail(object.getReceiverEmail())
                        .receiverPhone(object.getReceiverPhone())
                        .receiverName(object.getReceiverName())
                        .senderName(object.getSenderName())
                        .restaurantName(object.getRestaurantName())
                        .restaurantAddress(object.getRestaurantAddress())
                        .reservationTime(object.getReservationTime())
                        .reservationDate(object.getReservationDate())
                        .reservationStatus(object.getReservationStatus())
                        .build();
            };
            return baseNotification;
        }

        @Override
        public NotificationData reverseMap(BaseNotification object) {
            NotificationData notificationData = switch (object) {
                case ReservationNotification reservationNotification -> NotificationData.builder()
                        .restaurantId(String.valueOf(reservationNotification.getRestaurantId()))
                        .numberOfSeats(reservationNotification.getNumberOfSeats())
                        .receiverPhone(reservationNotification.getReceiverId().toString())
                        .receiverId(reservationNotification.getReceiverId().toString())
                        .type(reservationNotification.getType())
                        .receiverName(reservationNotification.getReceiverName())
                        .receiverEmail(reservationNotification.getReceiverEmail())
                        .reservationTime(reservationNotification.getReservationTime())
                        .reservationDate(reservationNotification.getReservationDate())
                        .reservationStatus(reservationNotification.getReservationStatus())
                        .restaurantAddress(reservationNotification.getRestaurantAddress())
                        .restaurantName(reservationNotification.getRestaurantName())
                        .senderName(reservationNotification.getSenderName())
                        .isRead(reservationNotification.isRead())
                        .reservationId(String.valueOf(reservationNotification.getReservationId()))
                        .build();
                case ReminderNotification reminderNotification -> NotificationData.builder()
                        .receiverPhone(reminderNotification.getReceiverId().toString())
                        .receiverId(reminderNotification.getReceiverId().toString())
                        .type(reminderNotification.getType())
                        .receiverName(reminderNotification.getReceiverName())
                        .receiverEmail(reminderNotification.getReceiverEmail())
                        .restaurantName(reminderNotification.getRestaurantName())
                        .restaurantAddress(reminderNotification.getRestaurantAddress())
                        .reservationTime(reminderNotification.getReservationTime())
                        .reservationDate(reminderNotification.getReservationDate())
                        .reservationStatus(reminderNotification.getReservationStatus())
                        .senderName(reminderNotification.getSenderName())
                        .isRead(reminderNotification.isRead())
                        .build();
                case VerificationNotification verificationNotification -> NotificationData.builder()
                        .receiverPhone(verificationNotification.getReceiverPhone())
                        .receiverId(verificationNotification.getReceiverId().toString())
                        .type(verificationNotification.getType())
                        .receiverName(verificationNotification.getReceiverName())
                        .receiverEmail(verificationNotification.getReceiverEmail())
                        .securityCode(verificationNotification.getVerificationCode())
                        .restaurantName(verificationNotification.getRestaurantName())
                        .restaurantAddress(verificationNotification.getRestaurantAddress())
                        .reservationTime(verificationNotification.getReservationTime())
                        .reservationDate(verificationNotification.getReservationDate())
                        .reservationStatus(verificationNotification.getReservationStatus())
                        .senderName(verificationNotification.getSenderName())
                        .isRead(verificationNotification.isRead())
                        .build();
                case BaseNotification baseNotification -> NotificationData.builder()
                        .receiverPhone(baseNotification.getReceiverPhone())
                        .receiverId(baseNotification.getReceiverId().toString())
                        .type(baseNotification.getType())
                        .receiverName(baseNotification.getReceiverName())
                        .receiverEmail(baseNotification.getReceiverEmail())
                        .restaurantName(baseNotification.getRestaurantName())
                        .restaurantAddress(baseNotification.getRestaurantAddress())
                        .reservationTime(baseNotification.getReservationTime())
                        .reservationDate(baseNotification.getReservationDate())
                        .reservationStatus(baseNotification.getReservationStatus())
                        .senderName(baseNotification.getSenderName())
                        .isRead(baseNotification.isRead())
                        .build();
            };
            return notificationData;
        }
    }
}
