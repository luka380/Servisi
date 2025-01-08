package com.project.prjx.Data.Model.Dto.Users;

import com.project.prjx.Data.Model.Dto.Restaurants.RestaurantDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ManagerDto extends BaseUserDto {
    private LocalDateTime hiringDate;
    private RestaurantDto restaurant;

    @Builder
    public ManagerDto(UUID id, String username, String password, EmailDto email, LocalDateTime birthday, String firstName, String lastName, Number phoneNumber, Boolean isEnabled, LocalDateTime hiringDate, RestaurantDto restaurant) {
        super(id, username, password, email, birthday, firstName, lastName, phoneNumber, isEnabled, "ROLE_MANAGER");
        this.hiringDate = hiringDate;
        this.restaurant = restaurant;
    }
}
