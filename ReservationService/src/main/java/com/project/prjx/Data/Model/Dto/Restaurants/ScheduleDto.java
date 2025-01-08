package com.project.prjx.Data.Model.Dto.Restaurants;

import com.project.prjx.Data.Model.Entity.Restaurants.Restaurant;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto{
    private int id;
    private RestaurantDto restaurant;
    private Map<String, Map<String, String>> Schedule;
}