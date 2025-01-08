package com.project.prjx.Data.Repositories;

import com.project.prjx.Data.Model.Dto.Restaurants.ZoneDto;
import com.project.prjx.Data.Model.Entity.Restaurants.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Integer> {
    List<Zone> findAllByRestaurant_Id(int restaurantId);
}
