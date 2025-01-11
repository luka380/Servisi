package com.project.prjx.Services;

import com.project.prjx.Data.Mappers.Mappers;
import com.project.prjx.Data.Model.Dto.Notifications.NotificationData;
import com.project.prjx.Data.Model.Dto.Restaurants.ReservationDto;
import com.project.prjx.Data.Model.Dto.Users.BaseUserDto;
import com.project.prjx.Data.Model.Dto.Users.EmailDto;
import com.project.prjx.Data.Model.Entity.Restaurants.Reservation;
import com.project.prjx.Data.Model.Entity.Restaurants.Restaurant;
import com.project.prjx.Data.Model.Entity.Restaurants.Schedule;
import com.project.prjx.Data.Model.Entity.Restaurants.SchedulePerDay;
import com.project.prjx.Data.Model.MessageType;
import com.project.prjx.Data.Model.Status;
import com.project.prjx.Data.Repositories.ReservationRepository;
import com.project.prjx.Data.Repositories.RestaurantRepository;
import com.project.prjx.Data.Repositories.SchedulePerDayRepository;
import com.project.prjx.Security.UserAuthentication;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

@Service
public class ReservationService {
    private final SchedulePerDayRepository schedulePerDayRepository;
    private final ReservationRepository reservationRepository;
    private final RestaurantRepository restaurantRepository;
    private final MessageService messageService;

    public ReservationService(SchedulePerDayRepository schedulePerDayRepository, ReservationRepository reservationRepository, RestaurantRepository restaurantRepository, MessageService messageService) {
        this.schedulePerDayRepository = schedulePerDayRepository;
        this.reservationRepository = reservationRepository;
        this.restaurantRepository = restaurantRepository;
        this.messageService = messageService;
    }

    @Transactional
    public boolean makeReservation(@Valid ReservationDto reservation) {

        UserAuthentication userAuthentication = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
        BaseUserDto user = userAuthentication.getUser();

        SchedulePerDay schedule = schedulePerDayRepository.findByRestaurant_IdAndDate(reservation.getRestaurant().getId(), reservation.getDate());
        String timeStart = reservation.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm"));
        String timeEnd = reservation.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm"));

        Map<String, Map<String, String>> orgSchedule = schedule.getSchedulePerDay();
        try {
            String res = orgSchedule.get(timeStart + '-' + timeEnd).get(String.valueOf(reservation.getTables().getId()));
            if (res != null) {
                throw new RuntimeException("Table is already reserved");
            }

            Restaurant restaurant = restaurantRepository.findById(reservation.getRestaurant().getId()).get();

            Reservation newReservation = reservationRepository.save(Reservation.builder()
                    .date(reservation.getDate())
                    .note(reservation.getNote())
                    .endTime(reservation.getEndTime())
                    .startTime(reservation.getStartTime())
                    .restaurant(restaurant)
                    .numOfGuests(reservation.getNumOfGuests())
                    .tables(Mappers.TableMapper.map(reservation.getTables()))
                    .status(Status.CONFIRMED)
                    .clientId(reservation.getClientId())
                    .build());

            orgSchedule.get(timeStart + '-' + timeEnd).put(String.valueOf(reservation.getTables().getId()), String.valueOf(newReservation.getId()));
            schedule.setSchedulePerDay(orgSchedule);
            schedulePerDayRepository.save(schedule);

            messageService.sendNotification(NotificationData.builder()
                    .reservationId(String.valueOf(newReservation.getId()))
                            .reservationStatus(Status.CONFIRMED.toString())
                            .restaurantAddress(restaurant.getAddress())
                            .reservationTime(newReservation.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")) + " - " + newReservation.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                            .reservationDate(newReservation.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                            .receiverEmail(user.getEmail().email())
                            .numberOfSeats(newReservation.getNumOfGuests())
                            .receiverName(user.getFirstName() + " " + user.getLastName())
                            .type(MessageType.RESERVATION_CONFIRMED)
                            .restaurantId(String.valueOf(newReservation.getRestaurant().getId()))
                            .restaurantName(newReservation.getRestaurant().getName())
                    .build());
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean cancelReservation(@Valid ReservationDto reservation) {
        Optional<Reservation> reservationToCancel = reservationRepository.findById(reservation.getId());

        UserAuthentication userAuthentication = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
        BaseUserDto user = userAuthentication.getUser();

        if(reservationToCancel.isEmpty() || reservationToCancel.get().getClientId().equals(user.getId())) {
            return false;
        }

        Reservation reservationToCancelEntity = reservationToCancel.get();

        SchedulePerDay schedule = schedulePerDayRepository.findByRestaurant_IdAndDate(reservation.getRestaurant().getId(), reservation.getDate());
        String timeStart = reservation.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm"));
        String timeEnd = reservation.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm"));

        Map<String, Map<String, String>> orgSchedule = schedule.getSchedulePerDay();
        try {
            String email = "luka26597@gmail.com";
            Restaurant restaurant = restaurantRepository.findById(reservation.getRestaurant().getId()).get();

            orgSchedule.get(timeStart + '-' + timeEnd).put(String.valueOf(reservation.getTables().getId()), null);
            schedule.setSchedulePerDay(orgSchedule);
            schedulePerDayRepository.save(schedule);

            reservationRepository.deleteById(reservation.getId());

            messageService.sendNotification(NotificationData.builder()
                    .reservationId(String.valueOf(reservationToCancelEntity.getId()))
                    .reservationStatus(Status.CANCELLED.toString())
                    .restaurantAddress(restaurant.getAddress())
                    .reservationTime(reservationToCancelEntity.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")) + " - " + reservationToCancelEntity.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                    .reservationDate(reservationToCancelEntity.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                    .receiverEmail(user.getEmail().email())
                    .numberOfSeats(reservationToCancelEntity.getNumOfGuests())
                    .receiverName(user.getFirstName() + " " + user.getLastName())
                    .type(MessageType.RESERVATION_CANCELLED)
                    .restaurantId(String.valueOf(reservationToCancelEntity.getRestaurant().getId()))
                    .restaurantName(reservationToCancelEntity.getRestaurant().getName())
                    .build());

            messageService.sendNotification(NotificationData.builder()
                    .reservationId(String.valueOf(reservationToCancelEntity.getId()))
                    .reservationStatus(Status.CANCELLED.toString())
                    .restaurantAddress(restaurant.getAddress())
                    .reservationTime(reservationToCancelEntity.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")) + " - " + reservationToCancelEntity.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                    .reservationDate(reservationToCancelEntity.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                    .receiverEmail(email)
                    .numberOfSeats(reservationToCancelEntity.getNumOfGuests())
                    .receiverName("manager.getFirstName()")
                    .type(MessageType.RESERVATION_CANCELLED)
                    .restaurantId(String.valueOf(reservationToCancelEntity.getRestaurant().getId()))
                    .restaurantName(reservationToCancelEntity.getRestaurant().getName())
                    .build());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean cancelReservation(@Valid ReservationDto reservation, boolean b) {
        Optional<Reservation> reservationToCancel = reservationRepository.findById(reservation.getId());

        UserAuthentication userAuthentication = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
        BaseUserDto user = userAuthentication.getUser();

        if(reservationToCancel.isEmpty()) {
            return false;
        }

        Reservation reservationToCancelEntity = reservationToCancel.get();

        SchedulePerDay schedule = schedulePerDayRepository.findByRestaurant_IdAndDate(reservation.getRestaurant().getId(), reservation.getDate());
        String timeStart = reservation.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm"));
        String timeEnd = reservation.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm"));

        Map<String, Map<String, String>> orgSchedule = schedule.getSchedulePerDay();
        try {
            String res = orgSchedule.get(timeStart + '-' + timeEnd).get(String.valueOf(reservation.getTables().getId()));
            if (res != null) {
                return false;
            }

            Restaurant restaurant = restaurantRepository.findById(reservation.getRestaurant().getId()).get();

            if(!b) {
                orgSchedule.get(timeStart + '-' + timeEnd).put(String.valueOf(reservation.getTables().getId()), null);
            }
            else {
                orgSchedule.get(timeStart + '-' + timeEnd).put(String.valueOf(reservation.getTables().getId()), "taken");
            }

            schedule.setSchedulePerDay(orgSchedule);
            schedulePerDayRepository.save(schedule);

            reservationRepository.deleteById(reservation.getId());

            messageService.sendNotification(NotificationData.builder()
                    .reservationId(String.valueOf(reservationToCancelEntity.getId()))
                    .reservationStatus(Status.CANCELLED.toString())
                    .restaurantAddress(restaurant.getAddress())
                    .reservationTime(reservationToCancelEntity.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")) + " - " + reservationToCancelEntity.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                    .reservationDate(reservationToCancelEntity.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                    .receiverEmail(user.getEmail().email())
                    .numberOfSeats(reservationToCancelEntity.getNumOfGuests())
                    .receiverName(user.getFirstName() + " " + user.getLastName())
                    .type(MessageType.RESERVATION_CANCELLED)
                    .restaurantId(String.valueOf(reservationToCancelEntity.getRestaurant().getId()))
                    .restaurantName(reservationToCancelEntity.getRestaurant().getName())
                    .build());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
