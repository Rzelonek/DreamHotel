package com.example.hotelreservation.controller;

import com.example.hotelreservation.model.User;
import com.example.hotelreservation.repository.UserRepository;
import com.example.hotelreservation.session.SessionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

      @Autowired
      private UserRepository userRepository;

      @Autowired
      private BCryptPasswordEncoder passwordEncoder;

      // Login page (GET)
      @GetMapping("/login")
      public String loginPage(Model model) {
            return "login"; // Return login form
      }

      // Logout (GET)
      @GetMapping("/logout")
      public String logout(HttpSession session) {
            session.invalidate(); // Invalidate the session
            return "redirect:/home"; // Redirect to home page after logout
      }
}
