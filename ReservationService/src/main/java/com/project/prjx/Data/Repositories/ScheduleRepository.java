package com.project.prjx.Data.Repositories;

import com.project.prjx.Data.Model.Dto.Restaurants.ScheduleDto;
import com.project.prjx.Data.Model.Entity.Restaurants.Schedule;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findAllByRestaurant_Id(int restaurantId);
}
