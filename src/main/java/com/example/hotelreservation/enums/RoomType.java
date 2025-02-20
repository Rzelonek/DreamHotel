package com.example.hotelreservation.enums;

public enum RoomType {
      STANDARD(4, 4), // 4 pokoje, 4-osobowe
      APARTMENT(4, 2), // 4 pokoje, 2-osobowe
      STUDIO(2, 5); // 2 pokoje, 5-osobowe

      private final int availableRooms;
      private final int capacity;

      RoomType(int availableRooms, int capacity) {
            this.availableRooms = availableRooms;
            this.capacity = capacity;
      }

      public int getAvailableRooms() {
            return availableRooms;
      }

      public int getCapacity() {
            return capacity;
      }
}
