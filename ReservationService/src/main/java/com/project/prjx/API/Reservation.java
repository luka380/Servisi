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
//        reservation.setClient(auth.getUser());
//        boolean res = reservationService.makeReservation(reservation);
//        if(res){
//            return ResponseEntity.ok(reservation);
//        }
//        else return ResponseEntity.badRequest().build();
        return null;
    }

    @PostMapping("/cancelReservation")
    public ResponseEntity<ReservationDto> cancelReservation(@Valid @RequestBody ReservationDto reservation){
//        UserAuthentication auth = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
//        reservation.setClient(auth.getUser());
//        boolean res = reservationService.cancelReservation(reservation);
//        if(res){
//            return ResponseEntity.ok(reservation);
//        }
//        else return ResponseEntity.badRequest().build();
        return null;
    }

    @PostMapping("/cancelReservationManager")
    public ResponseEntity<ReservationDto> cancelReservationManager(@Valid @RequestBody ReservationDto reservation, @RequestParam(required = false) String permanent){
        boolean res = reservationService.cancelReservation(reservation, permanent != null);
        if(res){
            return ResponseEntity.ok(reservation);
        }
        else return ResponseEntity.badRequest().build();
    }

    //Rezervacija stola - Klijent može da rezerviše sto u slobodnom terminu. Prilikom
    //rezervacije potrebno je: Asinhrono (pomoću mesedž brokera) obavestiti
    //servis za notifikacije da pošalje imejl koji potvrđuje da je rezervacija uspešno
    //napravljena. Pre potvrde rezervacije, sinhrono dohvatiti sa Korisničkog
    //servisa podatak o klijentovom broju prethodnih rezervacija. Ukoliko je
    //Korisnički servis trenutno nedostupan, potrebno je omogućiti ponovno slanje
    //zahteva (retry pattern). Na kraju, obavestiti Korisnički servis da je korisnik
    //napravio novu rezervaciju, i uvećati njegov broj rezervacija. Adekvatno
    //ažurirati stanje raspoloživih termina u restoranu.
    //○ Otkazivanje rezervacije - I klijent i menadžer restorana imaju mogućnost da
    //otkažu rezervaciju. Potrebno je poslati informaciju (imejl) o otkazivanju
    //rezervacije klijentu ili menadžeru, adekvatno ažurirati stanje raspoloživih
    //termina, i obavestiti korisnički servis da se broj rezervacija klijenta smanji ili
    //evidentirati da je rezervacija otkazana od strane restorana (onda se broj
    //rezervacija ne umanjuje).
    //Razmotriti različite slučajeve otkazivanja rezervacije. Ukoliko klijent otkaže
    //rezervaciju, termin postaje ponovo dostupan u sistemu. Ukoliko menadžer
    //otkaže rezervaciju, potrebno je da se klijent obavesti (imejlom), a termin
    //može da ostane nedostupan ukoliko restoran nije u mogućnosti da pruži
    //uslugu u tom terminu.
}
