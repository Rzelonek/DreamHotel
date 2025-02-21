package com.example.hotelreservation.service;

import com.example.hotelreservation.dto.ReservationDTO;
import com.example.hotelreservation.entity.Reservation;
import com.example.hotelreservation.entity.RoomCategory;
import com.example.hotelreservation.repository.ReservationRepository;
import com.example.hotelreservation.repository.RoomCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

      @Autowired
      private ReservationRepository reservationRepository;

      @Autowired
      private RoomCategoryRepository roomCategoryRepository;

      @Transactional
      public Reservation createReservation(ReservationDTO dto) throws Exception {
            LocalDate today = LocalDate.now();
            if (dto.getCheckInDate().isBefore(today.plusDays(1))) {
                  throw new Exception("Check-In Date must be later than today.");
            }
            if (!dto.getCheckOutDate().isAfter(dto.getCheckInDate())) {
                  throw new Exception("Check-Out Date must be after Check-In Date.");
            }
            Optional<RoomCategory> optCategory = roomCategoryRepository.findById(dto.getRoomCategoryId());
            if (!optCategory.isPresent()) {
                  throw new Exception("Room category not found.");
            }
            RoomCategory roomCategory = optCategory.get();

            if (dto.getNumberOfPersons() > roomCategory.getCapacity()) {
                  throw new Exception("Number of persons exceeds the capacity for the selected room.");
            }

            // Check availability for each day in the reservation period
            LocalDate date = dto.getCheckInDate();
            while (date.isBefore(dto.getCheckOutDate())) {
                  long reservedCount = reservationRepository
                              .countByRoomCategoryAndCheckInDateLessThanEqualAndCheckOutDateGreaterThan(roomCategory,
                                          date, date);
                  if (reservedCount >= roomCategory.getAmount()) {
                        throw new Exception("No available rooms in the selected category on: " + date);
                  }
                  date = date.plusDays(1);
            }

            // Calculate total price:
            long nights = ChronoUnit.DAYS.between(dto.getCheckInDate(), dto.getCheckOutDate());
            double totalPrice = (roomCategory.getPricePerNight() + dto.getMealOption().getAdditionalCost()) * nights;

            // Create reservation. Set originalCheckInDate to the initial check-in date.
            Reservation reservation = new Reservation(
                        dto.getGuestName(),
                        roomCategory,
                        dto.getMealOption(),
                        dto.getCheckInDate(),
                        dto.getCheckOutDate(),
                        dto.getNumberOfPersons(),
                        totalPrice,
                        dto.getCheckInDate());
            return reservationRepository.save(reservation);
      }

      @Transactional
      public Reservation updateReservationDates(Long reservationId, LocalDate newCheckIn, LocalDate newCheckOut)
                  throws Exception {
            Optional<Reservation> optReservation = reservationRepository.findById(reservationId);
            if (!optReservation.isPresent()) {
                  throw new Exception("Reservation not found.");
            }
            Reservation reservation = optReservation.get();
            LocalDate today = LocalDate.now();
            if (newCheckIn.isBefore(today.plusDays(1))) {
                  throw new Exception("New Check-In Date must be later than today.");
            }
            if (!newCheckOut.isAfter(newCheckIn)) {
                  throw new Exception("New Check-Out Date must be after the new Check-In Date.");
            }
            // Enforce: new check-in must not be earlier than the original check-in
            // and must not be later than one month from the original check-in.
            LocalDate originalCheckIn = reservation.getOriginalCheckInDate();
            if (newCheckIn.isBefore(originalCheckIn)) {
                  throw new Exception("New Check-In Date cannot be earlier than the original Check-In Date ("
                              + originalCheckIn + ").");
            }
            if (newCheckIn.isAfter(originalCheckIn.plusMonths(1))) {
                  throw new Exception(
                              "New Check-In Date cannot be later than one month from the original Check-In Date ("
                                          + originalCheckIn + ").");
            }
            // Check availability for each day in the new reservation period.
            RoomCategory roomCategory = reservation.getRoomCategory();
            LocalDate date = newCheckIn;
            while (date.isBefore(newCheckOut)) {
                  long reservedCount = reservationRepository
                              .countByRoomCategoryAndCheckInDateLessThanEqualAndCheckOutDateGreaterThan(roomCategory,
                                          date, date);
                  // If the date is within the current reservation period, subtract one so as not
                  // to count the current reservation.
                  if (!date.isBefore(reservation.getCheckInDate()) && date.isBefore(reservation.getCheckOutDate())) {
                        reservedCount--;
                  }
                  if (reservedCount >= roomCategory.getAmount()) {
                        throw new Exception("No available rooms in the selected category on: " + date);
                  }
                  date = date.plusDays(1);
            }
            // Update the reservation dates.
            reservation.setCheckInDate(newCheckIn);
            reservation.setCheckOutDate(newCheckOut);
            // Recalculate total price.
            long nights = ChronoUnit.DAYS.between(newCheckIn, newCheckOut);
            double totalPrice = (roomCategory.getPricePerNight() + reservation.getMealOption().getAdditionalCost())
                        * nights;
            reservation.setTotalPrice(totalPrice);
            return reservationRepository.save(reservation);
      }

      public List<Reservation> getReservationsByUser(String guestName) {
            return reservationRepository.findByGuestName(guestName);
      }

      public List<Reservation> getAllReservations() {
            return reservationRepository.findAll();
      }

      public Optional<Reservation> findById(Long reservationId) {
            return reservationRepository.findById(reservationId);
      }
}
