package com.example.hotelreservation.controller;

import com.example.hotelreservation.entity.Reservation;
import com.example.hotelreservation.service.ReservationService;
import com.example.hotelreservation.session.SessionConstants;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

      @Autowired
      private ReservationService reservationService;

      @GetMapping("/reservations")
      public String listReservations(Model model) {
            List<Reservation> reservations = reservationService.getAllReservations();
            model.addAttribute("reservations", reservations);
            return "adminReservations";
      }

      @GetMapping("/admin")
      public String adminPage(HttpSession session) {
            String role = (String) session.getAttribute(SessionConstants.ROLE_KEY);
            if ("ADMIN".equals(role)) {
                  return "admin"; // Render admin page
            } else {
                  return "redirect:/"; // Redirect to home if not admin
            }
      }
}
