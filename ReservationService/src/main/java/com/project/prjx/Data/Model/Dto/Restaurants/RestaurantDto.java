package com.project.prjx.Data.Model.Dto.Restaurants;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.prjx.Data.Model.Dto.Users.ManagerDto;
import com.project.prjx.Data.Model.Entity.KitchenType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
    private ManagerDto owner;
    private String description;
    private ManagerDto manager;
}
