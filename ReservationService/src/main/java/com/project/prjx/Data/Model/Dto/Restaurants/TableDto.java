package com.project.prjx.Data.Model.Dto.Restaurants;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TableDto {
    private int id;
    private String tableName;
    private int tableNumber;
    @NotNull
    private TableTypeDto tableType;
    @NotNull
    private ZoneDto zone;
    @NotNull
    private RestaurantDto restaurant;
    @NotNull
    private Boolean isDeleted;
}
