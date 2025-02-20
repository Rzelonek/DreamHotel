
package com.example.hotelreservation.entity;

import com.example.hotelreservation.enums.MealOption;
import com.example.hotelreservation.enums.RoomType;
import java.time.LocalDate;
import jakarta.persistence.*;
import java.time.temporal.ChronoUnit;

@Entity
public class Reservation {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      private String guestName;

      @ManyToOne
      @JoinColumn(name = "room_category_id")
      private RoomCategory roomCategory;

      @Enumerated(EnumType.STRING)
      private MealOption mealOption;

      private LocalDate checkInDate;
      private LocalDate checkOutDate;

      // Number of persons
      private int numberOfPersons;

      // Total price for the reservation (calculated at creation or update)
      private double totalPrice;

      // The original check-in date used for update restrictions
      private LocalDate originalCheckInDate;

      public Reservation() {
      }

      public Reservation(String guestName, RoomCategory roomCategory, MealOption mealOption,
                  LocalDate checkInDate, LocalDate checkOutDate, int numberOfPersons,
                  double totalPrice, LocalDate originalCheckInDate) {
            this.guestName = guestName;
            this.roomCategory = roomCategory;
            this.mealOption = mealOption;
            this.checkInDate = checkInDate;
            this.checkOutDate = checkOutDate;
            this.numberOfPersons = numberOfPersons;
            this.totalPrice = totalPrice;
            this.originalCheckInDate = originalCheckInDate;
      }

      // Getters and setters...

      public Long getId() {
            return id;
      }

      public String getGuestName() {
            return guestName;
      }

      public void setGuestName(String guestName) {
            this.guestName = guestName;
      }

      public RoomCategory getRoomCategory() {
            return roomCategory;
      }

      public void setRoomCategory(RoomCategory roomCategory) {
            this.roomCategory = roomCategory;
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

      public double getTotalPrice() {
            return totalPrice;
      }

      public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
      }

      public LocalDate getOriginalCheckInDate() {
            return originalCheckInDate;
      }

      public void setOriginalCheckInDate(LocalDate originalCheckInDate) {
            this.originalCheckInDate = originalCheckInDate;
      }

      // Helper method to compute total days of the reservation
      public long getTotalDays() {
            return ChronoUnit.DAYS.between(checkInDate, checkOutDate);
      }

      // Returns the room price per night from the associated RoomCategory.
      public double getRoomPricePerNight() {
            return roomCategory.getPricePerNight();
      }

      // Returns the additional cost from the selected meal option.
      public double getMealOptionPrice() {
            return mealOption.getAdditionalCost();
      }
}