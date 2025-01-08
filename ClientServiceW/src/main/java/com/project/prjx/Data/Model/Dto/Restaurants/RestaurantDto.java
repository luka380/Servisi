package com.project.prjx.Data.Model.Dto.Restaurants;

import lombok.Builder;

@Builder
public record RestaurantDto(
        int id,
        String name,
        String address,
        String phone) {}
