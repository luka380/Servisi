package com.project.prjx.Data.Model.Entity.Notifications;

import com.project.prjx.Data.Model.Entity.Restaurants.Reservation;
import com.project.prjx.Data.Model.Entity.Restaurants.Restaurant;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ReservationNotification extends BaseNotification {
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurantId;
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservationId;
    private int numberOfSeats;
}
