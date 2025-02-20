package com.example.hotelreservation.dto;

import com.example.hotelreservation.enums.MealOption;
import java.time.LocalDate;

public class ReservationDTO {
      // Instead of using a RoomType enum, we use the ID of a RoomCategory
      private Long roomCategoryId;
      private MealOption mealOption;
      private LocalDate checkInDate;
      private LocalDate checkOutDate;
      private int numberOfPersons;
      private String guestName; // to be set from Principal

      // Getters and setters

      public Long getRoomCategoryId() {
            return roomCategoryId;
      }

      public void setRoomCategoryId(Long roomCategoryId) {
            this.roomCategoryId = roomCategoryId;
      }

      public MealOption getMealOption() {
            return mealOption;
      }

      public void setMealOption(MealOption mealOption) {
            this.mealOption = mealOption;
      }

      public LocalDate getCheckInDate() {
            return checkInDate;
      }

      public void setCheckInDate(LocalDate checkInDate) {
            this.checkInDate = checkInDate;
      }

      public LocalDate getCheckOutDate() {
            return checkOutDate;
      }

      public void setCheckOutDate(LocalDate checkOutDate) {
            this.checkOutDate = checkOutDate;
      }

      public int getNumberOfPersons() {
            return numberOfPersons;
      }

      public void setNumberOfPersons(int numberOfPersons) {
            this.numberOfPersons = numberOfPersons;
      }

      public String getGuestName() {
            return guestName;
      }

      public void setGuestName(String guestName) {
            this.guestName = guestName;
      }
}
