package com.example.hotelreservation.service;

import com.example.hotelreservation.model.User;
import com.example.hotelreservation.entity.RoomCategory;
import com.example.hotelreservation.repository.RoomCategoryRepository;
import com.example.hotelreservation.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StreamUtils;
import org.springframework.stereotype.Service;

import org.springframework.util.DigestUtils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import java.io.IOException;

@Configuration
public class DataInitializer {

      @Autowired
      private BCryptPasswordEncoder passwordEncoder;

      @Bean
      CommandLineRunner initDatabase(RoomCategoryRepository roomCategoryRepository, UserRepository userRepository) {
            return args -> {
                  // Initialize Room Categories
                  if (roomCategoryRepository.count() == 0) {
                        RoomCategory apartment1 = new RoomCategory(
                                    "Apartment",
                                    1,
                                    2,
                                    "Apartment 1",
                                    "Nice apartment for 2 persons.",
                                    120.0,
                                    readImage("static/images/a1.jpg"));
                        RoomCategory apartment2 = new RoomCategory(
                                    "Apartment",
                                    1,
                                    2,
                                    "Apartment 2",
                                    "Nice apartment for 2 persons.",
                                    130.0,
                                    readImage("static/images/a2.jpg"));
                        RoomCategory apartment3 = new RoomCategory(
                                    "Apartment",
                                    1,
                                    2,
                                    "Apartment 3",
                                    "Nice apartment for 2 persons.",
                                    140.0,
                                    readImage("static/images/a3.jpg"));

                        // Mark these apartments as featured and assign display order
                        apartment1.setFeatured(true);
                        apartment1.setDisplayOrder(1);

                        apartment2.setFeatured(true);
                        apartment2.setDisplayOrder(2);

                        apartment3.setFeatured(true);
                        apartment3.setDisplayOrder(3);

                        roomCategoryRepository.save(apartment1);
                        roomCategoryRepository.save(apartment2);
                        roomCategoryRepository.save(apartment3);
                  }

                  if (userRepository.count() == 0) {
                        String adminPassword = passwordEncoder.encode("admin");
                        String userPassword = passwordEncoder.encode("user");

                        User admin = new User(null, "Admin", "Admin", "admin", adminPassword, User.Role.ADMIN);
                        User user = new User(null, "User", "User", "user", userPassword, User.Role.USER);

                        // Save users to the database
                        userRepository.save(admin);
                        userRepository.save(user);
                  }
            };
      }

      private byte[] readImage(String path) {
            try {
                  ClassPathResource resource = new ClassPathResource(path);
                  if (!resource.exists()) {
                        System.err.println("Warning: Image not found: " + path);
                        return new byte[0];
                  }
                  return StreamUtils.copyToByteArray(resource.getInputStream());
            } catch (IOException e) {
                  e.printStackTrace();
                  return new byte[0];
            }
      }
}
