package com.project.prjx.Data.Model.Entity.Notifications;

import com.project.prjx.Data.Model.MessageType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("VERIFICATION")
public class VerificationNotification extends BaseNotification {
    private String verificationCode;

    @Builder
    public VerificationNotification(UUID id, LocalDateTime createdAt, boolean isRead, MessageType type, String restaurantName, String restaurantAddress, String reservationTime, String reservationDate, String reservationStatus, String receiverEmail, String receiverName, String receiverPhone, UUID receiverId, String senderName, String verificationCode) {
        super(id, createdAt, isRead, type, restaurantName, restaurantAddress, reservationTime, reservationDate, reservationStatus, receiverEmail, receiverName, receiverPhone, receiverId, senderName);
        this.verificationCode = verificationCode;
    }
}
