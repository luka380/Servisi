package com.project.prjx.Data.Model.Entity.Restaurants;

import com.project.prjx.Data.Model.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"restaurant_id", "tables_id", "date"}))
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
    private UUID clientId;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
}
