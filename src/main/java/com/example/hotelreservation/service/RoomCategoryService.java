package com.example.hotelreservation.service;

import com.example.hotelreservation.dto.RoomAvailabilityDTO;
import com.example.hotelreservation.entity.RoomCategory;
import com.example.hotelreservation.repository.ReservationRepository;
import com.example.hotelreservation.repository.RoomCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomCategoryService {

      @Autowired
      private RoomCategoryRepository roomCategoryRepository;

      @Autowired
      private ReservationRepository reservationRepository;

      public RoomCategory addRoomCategory(RoomCategory roomCategory) {
            return roomCategoryRepository.save(roomCategory);
      }

      public List<RoomCategory> getAllRoomCategories() {
            return roomCategoryRepository.findAll();
      }

      public List<RoomAvailabilityDTO> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) {
            List<RoomCategory> roomCategories = roomCategoryRepository.findAll();
            List<RoomAvailabilityDTO> result = new ArrayList<>();
            for (RoomCategory rc : roomCategories) {
                  int freeCount = rc.getAmount();
                  for (LocalDate date = checkInDate; date.isBefore(checkOutDate); date = date.plusDays(1)) {
                        long reserved = reservationRepository
                                    .countByRoomCategoryAndCheckInDateLessThanEqualAndCheckOutDateGreaterThan(rc, date,
                                                date);
                        int available = rc.getAmount() - (int) reserved;
                        freeCount = Math.min(freeCount, available);
                  }
                  result.add(new RoomAvailabilityDTO(rc.getId(), rc.getName(), freeCount));
            }
            return result;
      }

      public void updateRoomCategory(RoomCategory roomCategory) {
            roomCategoryRepository.save(roomCategory);
      }

}
