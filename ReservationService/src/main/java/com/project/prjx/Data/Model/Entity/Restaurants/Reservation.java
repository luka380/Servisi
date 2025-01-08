package com.project.prjx.Data.Model.Entity.Restaurants;

import com.project.prjx.Data.Model.Entity.Users.Client;
import com.project.prjx.Data.Model.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"restaurant_id", "tables_id", "startDate"}))
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
    @ManyToOne
    @JoinColumn(name = "tables_id", nullable = false)
    private Tables tables;
    private Status status;
    private String note;
    private int numOfGuests;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = true)
    private Client client;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
