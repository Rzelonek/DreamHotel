package com.example.hotelreservation.service;

import com.example.hotelreservation.model.User;
import com.example.hotelreservation.entity.AboutSection;
import com.example.hotelreservation.entity.ContactInfo;
import com.example.hotelreservation.entity.RoomCategory;
import com.example.hotelreservation.repository.AboutSectionRepository;
import com.example.hotelreservation.repository.ContactInfoRepository;
import com.example.hotelreservation.repository.RoomCategoryRepository;
import com.example.hotelreservation.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StreamUtils;

import java.io.IOException;

@Configuration
public class DataInitializer {

      @Autowired
      private BCryptPasswordEncoder passwordEncoder;

      @Autowired
      private AboutSectionRepository aboutSectionRepository;

      @Autowired
      private ContactInfoRepository contactInfoRepository;

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

                  // Initialize About Section (if not present)
                  if (aboutSectionRepository.count() == 0) {
                        AboutSection aboutSection = new AboutSection();
                        aboutSection.setTitle("About Dream Hotel");
                        aboutSection.setDescription(
                                    "Dream Hotel was founded by the legendary Queen of the Dream Mountains in Dreamland. Inspired by her vision of a sanctuary where luxury meets enchantment, the hotel was built to offer guests a realm of comfort and sophistication. With its regal charm and modern amenities, Dream Hotel invites you to experience a storybook escape where every detail is designed to make your stay truly magical.");
                        aboutSection.setImage(readImage("static/images/about.jpg"));

                        aboutSectionRepository.save(aboutSection);
                  }

                  // Initialize Contact Info (if not present)
                  if (contactInfoRepository.count() == 0) {
                        ContactInfo contactInfo = new ContactInfo();
                        contactInfo.setLocationName("Dream Hotel Central");
                        contactInfo.setAddress("123 Main St, Dream City, DC 12345");
                        contactInfo.setPhone("+1 (555) 123-4567");
                        contactInfo.setEmail("dreamhoterl@dreamhotel.dream");
                        contactInfoRepository.save(contactInfo);
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
