package com.project.prjx.Data.Repositories;

import com.project.prjx.API.RestaurantManagement;
import com.project.prjx.Data.Model.Dto.Restaurants.RestaurantDto;
import com.project.prjx.Data.Model.Entity.KitchenType;
import com.project.prjx.Data.Model.Entity.Restaurants.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query("SELECT r FROM Restaurant r JOIN Zone z ON (z.restaurant.id = r.id) JOIN TableType tt ON (tt.restaurant.id = r.id)" +
            "WHERE (:kitchenType IS NULL OR :kitchenType = r.kitchenType) AND " +
            "(:smoking IS NULL OR :smoking = z.isSmoking) AND" +
            "(:capacity IS NULL OR :capacity <= tt.seatingCapacity)")
    List<RestaurantDto> findByCriteria(@Param("kitchenType") KitchenType kitchenType,
                                       @Param("smoking") Boolean smokingAllowed,
                                       @Param("capacity") Integer minCapacity);

    List<Restaurant> findAllByOwner_IdOrManager_Id(UUID ownerId, UUID managerId);
}
