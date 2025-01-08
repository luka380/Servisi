package com.project.prjx.Data.Repositories;

import com.project.prjx.Data.Model.Dto.Restaurants.RewardsDto;
import com.project.prjx.Data.Model.Entity.Restaurants.Rewards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardsRepository extends JpaRepository<Rewards, Integer> {
    Rewards findAllByRestaurant_Id(int restaurantId);
}
