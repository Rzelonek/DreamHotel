package com.example.hotelreservation.controller;

import com.example.hotelreservation.dto.ReservationDTO;
import com.example.hotelreservation.entity.Reservation;
import com.example.hotelreservation.service.ReservationService;
import com.example.hotelreservation.service.RoomCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class ReservationController {

      @Autowired
      private ReservationService reservationService;

      @Autowired
      private RoomCategoryService roomCategoryService;

      @PostMapping("/reserve")
      public String createReservation(@ModelAttribute("reservationDTO") ReservationDTO dto,
                  Model model, Principal principal) {
            try {
                  dto.setGuestName(principal.getName());
                  Reservation reservation = reservationService.createReservation(dto);
                  model.addAttribute("message", "Reservation created successfully! ID: " + reservation.getId());
            } catch (Exception e) {
                  model.addAttribute("error", "Reservation failed: " + e.getMessage());
            }
            return "index"; // Or the view you want to return to
      }

      @GetMapping("/myReservations")
      public String myReservations(Model model, Principal principal) {
            List<Reservation> reservations = reservationService.getReservationsByUser(principal.getName());
            model.addAttribute("reservations", reservations);
            return "myReservations";
      }

      @GetMapping("/updateReservationForm/{reservationId}")
      public String showUpdateReservationForm(@PathVariable Long reservationId, Model model) {
            Optional<Reservation> optReservation = reservationService.findById(reservationId);
            if (!optReservation.isPresent()) {
                  model.addAttribute("error", "Reservation not found.");
                  return "redirect:/myReservations";
            }
            model.addAttribute("reservation", optReservation.get());
            return "updateReservationForm";
      }

      @PostMapping("/updateReservation")
      public String updateReservation(@RequestParam Long reservationId,
                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate newCheckIn,
                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate newCheckOut,
                  Model model) {
            try {
                  Reservation reservation = reservationService.updateReservationDates(reservationId, newCheckIn,
                              newCheckOut);
                  model.addAttribute("message", "Reservation (ID: " + reservation.getId() + ") updated successfully.");
            } catch (Exception e) {
                  model.addAttribute("error", "Date change failed: " + e.getMessage());
            }
            return "redirect:/myReservations";
      }
}
