package com.project.prjx.API;

import com.project.prjx.Data.Model.Dto.Restaurants.ReservationDto;
import com.project.prjx.Security.UserAuthentication;
import com.project.prjx.Services.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class Reservation {
    private final ReservationService reservationService;

    public Reservation(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/newReservation")
    public ResponseEntity<ReservationDto> makeReservation(@Valid @RequestBody ReservationDto reservation){
        UserAuthentication auth = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
        reservation.setClientId(auth.getUser().getId());
        boolean res = reservationService.makeReservation(reservation);
        if(res){
            return ResponseEntity.ok(reservation);
        }
        else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/cancelReservation")
    public ResponseEntity<ReservationDto> cancelReservation(@Valid @RequestBody ReservationDto reservation){
        UserAuthentication auth = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
        reservation.setClientId(auth.getUser().getId());
        boolean res = reservationService.cancelReservation(reservation);
        if(res){
            return ResponseEntity.ok(reservation);
        }
        else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/cancelReservationManager")
    public ResponseEntity<ReservationDto> cancelReservationManager(@Valid @RequestBody ReservationDto reservation, @RequestParam(required = false) String permanent){
        boolean res = reservationService.cancelReservation(reservation, permanent != null);
        if(res){
            return ResponseEntity.ok(reservation);
        }
        else return ResponseEntity.badRequest().build();
    }
}
