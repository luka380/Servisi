package com.project.prjx.Data.Repositories;

import com.project.prjx.Data.Model.Dto.Restaurants.SchedulePerDayDto;
import com.project.prjx.Data.Model.Entity.Restaurants.SchedulePerDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SchedulePerDayRepository extends JpaRepository<SchedulePerDay, Integer> {
    SchedulePerDay findAllByRestaurant_Id(int restaurantId);

    SchedulePerDay findAllByRestaurant_Id(int restaurantId, LocalDate date);
}
