package com.project.prjx.Data.Model.Entity.Restaurants;

import com.project.prjx.Data.Model.Status;
import com.project.prjx.Data.Model.Entity.Users.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@jakarta.persistence.Table(uniqueConstraints = @UniqueConstraint(columnNames = {"restaurant_id", "tables_id", "date"}))
public class Reservation {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
    @ManyToOne
    @JoinColumn(name = "tables_id", nullable = false)
    private Table tables;
    private Status status;
    private String note;
    private int numOfGuests;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = true)
    private Client client;
    private LocalDateTime date;
}
