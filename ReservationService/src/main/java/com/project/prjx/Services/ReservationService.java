package com.project.prjx.Services;

import com.project.prjx.Data.Model.Dto.Restaurants.ReservationDto;
import com.project.prjx.Data.Model.Entity.Restaurants.SchedulePerDay;
import com.project.prjx.Data.Repositories.SchedulePerDayRepository;
import com.project.prjx.Data.Repositories.ScheduleRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReservationService {
    private final SchedulePerDayRepository schedulePerDayRepository;

    public ReservationService(SchedulePerDayRepository schedulePerDayRepository) {
        this.schedulePerDayRepository = schedulePerDayRepository;
    }

    public boolean makeReservation(ReservationDto reservation) {

        SchedulePerDay schedule = schedulePerDayRepository.findByRestaurant_IdAndDate(reservation.getRestaurant().getId(), reservation.getStartDate().toLocalDate()).getFirst();

        return false;
    }

    public boolean cancelReservation(@Valid ReservationDto reservation, boolean b) {
        return false;
    }

    public boolean cancelReservation(@Valid ReservationDto reservation) {
        return false;
    }


}
