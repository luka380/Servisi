package com.project.prjx.Data.Repositories;

import com.project.prjx.Data.Model.Entity.Restaurants.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findReservationsByStartDateBetween(LocalDateTime startDateAfter, LocalDateTime startDateBefore);
}
