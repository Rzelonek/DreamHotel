package com.example.hotelreservation.controller;

import com.example.hotelreservation.dto.ReservationDTO;
import com.example.hotelreservation.entity.RoomCategory;
import com.example.hotelreservation.repository.RoomCategoryRepository;
import com.example.hotelreservation.service.AboutSectionService;
import com.example.hotelreservation.service.ContactInfoService;
import com.example.hotelreservation.service.RoomCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.hotelreservation.entity.AboutSection;
import com.example.hotelreservation.entity.ContactInfo;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import java.util.Base64;

@Controller
public class HomeController {

      @Autowired
      private RoomCategoryRepository roomCategoryRepository;

      @Autowired
      private RoomCategoryService roomCategoryService;

      @Autowired
      private AboutSectionService aboutSectionService;

      @Autowired
      private ContactInfoService contactInfoService;

      @GetMapping({ "/", "/home", "/reservationForm" })
      public String index(Model model, Principal principal) {
            // Reservation form attributes
            model.addAttribute("reservationDTO", new ReservationDTO());
            model.addAttribute("roomCategories", roomCategoryService.getAllRoomCategories());
            model.addAttribute("mealOptions", com.example.hotelreservation.enums.MealOption.values());
            if (principal != null) {
                  model.addAttribute("username", principal.getName());
            }

            // Featured rooms for the main page
            List<RoomCategory> featured = roomCategoryRepository.findByFeaturedTrueOrderByDisplayOrderAsc();
            List<RoomCategory> featuredLimited = featured.stream().limit(3).collect(Collectors.toList());
            model.addAttribute("featuredRooms", featuredLimited);

            AboutSection aboutSection = aboutSectionService.getAboutSection();
            if (aboutSection.getImage() != null && aboutSection.getImage().length > 0) {
                  String base64Image = Base64.getEncoder().encodeToString(aboutSection.getImage());
                  model.addAttribute("base64Image", base64Image);
            }
            model.addAttribute("aboutSection", aboutSection);

            ContactInfo contactInfo = contactInfoService.getContactInfo();
            model.addAttribute("contactInfo", contactInfo);

            return "index";
      }

}
