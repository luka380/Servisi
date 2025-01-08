package com.project.prjx.Data.Model.Dto.Restaurants;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SchedulePerDayDto {
    private int id;
    private LocalDate date;
    private ScheduleDto schedule;
    private RestaurantDto restaurant;
}
