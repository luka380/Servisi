package com.project.prjx.Data.Model.Dto.Restaurants;

import com.project.prjx.Data.Model.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ReservationDto {
    private int id;
    private RestaurantDto restaurant;
    private TableDto tables;
    private Status status;
    private String note;
    private int numOfGuests;
    private UUID clientId;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
}
