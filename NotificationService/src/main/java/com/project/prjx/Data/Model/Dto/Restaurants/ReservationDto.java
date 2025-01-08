package com.project.prjx.Data.Model.Dto.Restaurants;

import com.project.prjx.Data.Model.Dto.Users.ClientDto;
import com.project.prjx.Data.Model.Status;
import lombok.Builder;

import java.util.Date;

@Builder
public record ReservationDto(
        int id,
        RestaurantDto restaurant,
        TableDto tables,
        String timeFrom,
        String timeTo,
        Status status,
        String note,
        int numOfGuests,
        ClientDto client,
        Date date) {
}
