package com.example.hotelreservation.repository;

import com.example.hotelreservation.entity.Reservation;
import com.example.hotelreservation.entity.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
      List<Reservation> findByGuestName(String guestName);

      long countByRoomCategoryAndCheckInDateLessThanEqualAndCheckOutDateGreaterThan(RoomCategory roomCategory,
                  LocalDate day, LocalDate day2);
}
