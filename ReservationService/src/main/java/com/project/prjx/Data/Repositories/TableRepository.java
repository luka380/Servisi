package com.project.prjx.Data.Repositories;

import com.project.prjx.Data.Model.Dto.Restaurants.TableDto;
import com.project.prjx.Data.Model.Entity.Restaurants.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<Tables, Integer> {
    List<Tables> findAllByRestaurant_Id(int restaurantId);
}
