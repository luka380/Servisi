package com.project.prjx.Data.Repositories;

import com.project.prjx.Data.Model.Dto.Restaurants.TableTypeDto;
import com.project.prjx.Data.Model.Entity.Restaurants.TableType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableTypeRepository extends JpaRepository<TableType, Integer> {
    List<TableType> findAllByRestaurant_Id(int restaurantId);
}
