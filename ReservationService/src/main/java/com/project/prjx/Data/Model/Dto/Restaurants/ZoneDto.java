package com.project.prjx.Data.Model.Dto.Restaurants;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.prjx.Data.Model.Entity.Restaurants.Restaurant;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ZoneDto {
    private int id;
    private RestaurantDto restaurant;
    @NotNull
    private String name;
    private String description;
    @NotNull
    private String location;
    @NotNull
    private Boolean isSmoking;
}
