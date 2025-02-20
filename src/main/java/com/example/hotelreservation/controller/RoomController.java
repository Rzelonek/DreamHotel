package com.example.hotelreservation.controller;

import com.example.hotelreservation.dto.ReservationDTO;
import com.example.hotelreservation.entity.RoomCategory;
import com.example.hotelreservation.repository.RoomCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
public class RoomController {

      @Autowired
      private RoomCategoryRepository roomCategoryRepository;

      @GetMapping("/room/{id}")
      public String roomDetails(@PathVariable Long id, Model model, Principal principal) {
            Optional<RoomCategory> roomOpt = roomCategoryRepository.findById(id);
            if (roomOpt.isEmpty()) {
                  return "redirect:/"; // or show an error page if the room is not found
            }
            RoomCategory room = roomOpt.get();
            model.addAttribute("room", room);

            // Add meal options to the model so that the view can display them
            model.addAttribute("mealOptions", com.example.hotelreservation.enums.MealOption.values());

            // If the user is logged in, prepare a reservation DTO with the room
            // pre-selected.
            if (principal != null) {
                  ReservationDTO dto = new ReservationDTO();
                  dto.setRoomCategoryId(room.getId());
                  model.addAttribute("reservationDTO", dto);
            }
            return "roomDetails";
      }

}
