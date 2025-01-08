package com.project.prjx.Data.Model.Dto.Restaurants;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.prjx.Data.Model.Dto.Users.BaseUserDto;
import com.project.prjx.Data.Model.Dto.Users.ClientDto;
import com.project.prjx.Data.Model.Entity.Restaurants.Restaurant;
import com.project.prjx.Data.Model.Entity.Restaurants.Tables;
import com.project.prjx.Data.Model.Entity.Users.Client;
import com.project.prjx.Data.Model.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
    private ClientDto client;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
