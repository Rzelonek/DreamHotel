package com.example.hotelreservation.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contact_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfo {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      private String locationName;
      private String address;
      private String phone;
      private String email;

}
