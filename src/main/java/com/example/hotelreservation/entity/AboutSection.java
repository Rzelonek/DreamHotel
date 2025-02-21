package com.example.hotelreservation.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "about_section")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AboutSection {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      private String title;

      @Column(length = 2000)
      private String description;

      @Lob
      private byte[] image;
}
