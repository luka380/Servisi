package com.project.prjx.Data.Model.Dto.Restaurants;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableTypeDto {
    private int id;
    @NotNull
    private String name;
    @NotNull
    private int seatingCapacity;
    private RestaurantDto restaurant;
}
