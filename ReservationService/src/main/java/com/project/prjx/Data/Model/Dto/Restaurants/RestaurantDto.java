package com.project.prjx.Data.Model.Dto.Restaurants;

import com.project.prjx.Data.Model.Entity.KitchenType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class RestaurantDto {
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String phone;
    @NotNull
    private KitchenType kitchenType;
    private UUID ownerId;
    private String description;
    private UUID managerId;
}
