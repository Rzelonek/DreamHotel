package com.example.hotelreservation.entity;

import jakarta.persistence.*;

@Entity
public class RoomCategory {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      private String type;
      private int amount;
      private int capacity;
      private String name;
      private String description;

      // New field: price per night for this room category.
      private double pricePerNight;

      // New field: image for this room category.
      private byte[] image;

      // New fields for featured selection
      private boolean featured; // whether this category is featured on main page
      private Integer displayOrder; // optional order for featured display

      public RoomCategory() {
      }

      public RoomCategory(String type, int amount, int capacity, String name, String description, double pricePerNight,
                  byte[] image) {
            this.type = type;
            this.amount = amount;
            this.capacity = capacity;
            this.name = name;
            this.description = description;
            this.pricePerNight = pricePerNight;
            this.image = image;
            this.featured = false; // default not featured
            this.displayOrder = null;
      }

      // Getters and setters

      public Long getId() {
            return id;
      }

      public void setId(Long id) {
            this.id = id;
      }

      public String getType() {
            return type;
      }

      public void setType(String type) {
            this.type = type;
      }

      public int getAmount() {
            return amount;
      }

      public void setAmount(int amount) {
            this.amount = amount;
      }

      public int getCapacity() {
            return capacity;
      }

      public void setCapacity(int capacity) {
            this.capacity = capacity;
      }

      public String getName() {
            return name;
      }

      public void setName(String name) {
            this.name = name;
      }

      public String getDescription() {
            return description;
      }

      public void setDescription(String description) {
            this.description = description;
      }

      public double getPricePerNight() {
            return pricePerNight;
      }

      public void setPricePerNight(double pricePerNight) {
            this.pricePerNight = pricePerNight;
      }

      public byte[] getImage() {
            return image;
      }

      public void setImage(byte[] image) {
            this.image = image;
      }

      public boolean isFeatured() {
            return featured;
      }

      public void setFeatured(boolean featured) {
            this.featured = featured;
      }

      public Integer getDisplayOrder() {
            return displayOrder;
      }

      public void setDisplayOrder(Integer displayOrder) {
            this.displayOrder = displayOrder;
      }

}