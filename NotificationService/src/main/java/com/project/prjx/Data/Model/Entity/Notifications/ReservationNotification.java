package com.project.prjx.Data.Model.Entity.Notifications;


import com.project.prjx.Data.Model.MessageType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("RESERVATION")
public class ReservationNotification extends BaseNotification {
    private int restaurantId;
    private int reservationId;
    private int numberOfSeats;

    @Builder
    public ReservationNotification(UUID id, LocalDateTime createdAt, boolean isRead, MessageType type, String restaurantName, String restaurantAddress, String reservationTime, String reservationDate, String reservationStatus, String receiverEmail, String receiverName, String receiverPhone, UUID receiverId, String senderName, int restaurantId, int reservationId, int numberOfSeats) {
        super(id, createdAt, isRead, type, restaurantName, restaurantAddress, reservationTime, reservationDate, reservationStatus, receiverEmail, receiverName, receiverPhone, receiverId, senderName);
        this.restaurantId = restaurantId;
        this.reservationId = reservationId;
        this.numberOfSeats = numberOfSeats;
    }
}
