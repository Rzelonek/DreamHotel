package com.example.hotelreservation.controller;

import com.example.hotelreservation.dto.ReservationDTO;
import com.example.hotelreservation.entity.RoomCategory;
import com.example.hotelreservation.repository.RoomCategoryRepository;
import com.example.hotelreservation.service.RoomCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Controller
public class HomeController {

      @Autowired
      private RoomCategoryRepository roomCategoryRepository;

      @Autowired
      private RoomCategoryService roomCategoryService;

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

            return "index";
      }

}
