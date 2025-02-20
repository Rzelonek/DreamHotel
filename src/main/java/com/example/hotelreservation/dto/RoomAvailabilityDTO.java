package com.example.hotelreservation.dto;

public class RoomAvailabilityDTO {
      private Long roomCategoryId;
      private String roomName;
      private int freeCount;

      public RoomAvailabilityDTO(Long roomCategoryId, String roomName, int freeCount) {
            this.roomCategoryId = roomCategoryId;
            this.roomName = roomName;
            this.freeCount = freeCount;
      }

      public Long getRoomCategoryId() {
            return roomCategoryId;
      }

      public void setRoomCategoryId(Long roomCategoryId) {
            this.roomCategoryId = roomCategoryId;
      }

      public String getRoomName() {
            return roomName;
      }

      public void setRoomName(String roomName) {
            this.roomName = roomName;
      }

      public int getFreeCount() {
            return freeCount;
      }

      public void setFreeCount(int freeCount) {
            this.freeCount = freeCount;
      }
}
