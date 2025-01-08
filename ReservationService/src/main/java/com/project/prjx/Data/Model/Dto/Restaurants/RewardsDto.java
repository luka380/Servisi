package com.project.prjx.Data.Model.Dto.Restaurants;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RewardsDto {
    private int rewardId;
    private RestaurantDto restaurant;
    private int requirement;
    private String reward;
}
