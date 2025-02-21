package com.example.hotelreservation.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "tuser")
public class User {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
      private String name;
      private String surname;
      private String username;
      private String password;
      @Enumerated(EnumType.STRING)
      private Role role;

      public User(Long id, String name, String surname, String username, String password, Role role) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.username = username;
            this.password = password;
            this.role = role;
      }

      public User(Long id) {
            this.id = id;
      }

      // g^s

      public enum Role {
            ADMIN,
            USER
      }

      public User orElseThrow(Object object) {
            throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
      }

}
