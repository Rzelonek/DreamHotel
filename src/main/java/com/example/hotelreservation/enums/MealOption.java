package com.example.hotelreservation.enums;

public enum MealOption {
      NONE(0.0),
      BREAKFAST(10.0),
      BREAKFAST_AND_DINNER(20.0);

      private final double additionalCost;

      MealOption(double additionalCost) {
            this.additionalCost = additionalCost;
      }

      public double getAdditionalCost() {
            return additionalCost;
      }
}
