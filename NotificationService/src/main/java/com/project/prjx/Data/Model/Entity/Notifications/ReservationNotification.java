package com.project.prjx.Data.Model.Entity.Notifications;

import com.project.prjx.Data.Model.Entity.Restaurants.Reservation;
import com.project.prjx.Data.Model.Entity.Restaurants.Restaurant;
import com.project.prjx.Data.Model.Entity.Users.BaseUser;
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
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurantId;
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservationId;
    private int numberOfSeats;

    @Builder
    public ReservationNotification(UUID id, MessageType type, BaseUser receiverId, LocalDateTime createdAt, boolean isRead, Restaurant restaurantId, Reservation reservationId, int numberOfSeats) {
        super(id, type, receiverId, createdAt, isRead);
        this.restaurantId = restaurantId;
        this.reservationId = reservationId;
        this.numberOfSeats = numberOfSeats;
    }
}
