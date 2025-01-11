package com.project.prjx.Data.Model.Entity.Notifications;

import com.project.prjx.Data.Model.MessageType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Entity
@DiscriminatorValue("REMINDER")
public class ReminderNotification extends BaseNotification {

    @Builder
    public ReminderNotification(UUID id, LocalDateTime createdAt, boolean isRead, MessageType type, String restaurantName, String restaurantAddress, String reservationTime, String reservationDate, String reservationStatus, String receiverEmail, String receiverName, String receiverPhone, UUID receiverId, String senderName) {
        super(id, createdAt, isRead, type, restaurantName, restaurantAddress, reservationTime, reservationDate, reservationStatus, receiverEmail, receiverName, receiverPhone, receiverId, senderName);
    }
}
