package com.example.hotelreservation.controller;

import com.example.hotelreservation.dto.RoomAvailabilityDTO;
import com.example.hotelreservation.service.RoomCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class FreeRoomsController {

      @Autowired
      private RoomCategoryService roomCategoryService;

      @GetMapping("/freeRooms")
      public String freeRoomsForm(Model model) {
            return "freeRooms";
      }

      @PostMapping("/freeRooms")
      public String checkFreeRooms(
                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
                  Model model) {
            List<RoomAvailabilityDTO> availability = roomCategoryService.getAvailableRooms(checkInDate, checkOutDate);
            model.addAttribute("availability", availability);
            return "freeRooms";
      }
}
