package com.project.prjx.Services;

import com.project.prjx.Data.Model.Entity.Restaurants.Reservation;
import com.project.prjx.Data.Repositories.ReservationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SchedulerService {
    private final ReservationRepository reservationRepository;
    private final MessageService messageService;

    public SchedulerService(ReservationRepository reservationRepository, MessageService messageService) {
        this.reservationRepository = reservationRepository;
        this.messageService = messageService;
    }

    @Scheduled(cron = "0 0/15 * * * *")
    public void reminderNotification() {
        LocalDateTime currentTime = LocalDateTime.now();
        List<Reservation> reservations = reservationRepository.findReservationsByStartTimeBetween(currentTime.plusMinutes(58), currentTime.plusMinutes(62));

        for (Reservation reservation : reservations) {

        }
    }
}
