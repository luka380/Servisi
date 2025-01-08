package com.project.prjx.Data.Model.Dto.Restaurants;

import com.project.prjx.Data.Model.Entity.Restaurants.Restaurant;

public record ZoneDto (
        int id,
        Restaurant restaurant,
        String name,
        String description,
        String location,
        Boolean isSmoking) {}
