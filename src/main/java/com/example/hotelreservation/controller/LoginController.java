package com.example.hotelreservation.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

      @Autowired
      HttpSession httpSession;

      @GetMapping("/login")
      public String login() {
            return "login"; // Return login.html template
      }

      // Logout (GET)
      @GetMapping("/logout")
      public String logout(HttpSession session) {
            session.invalidate(); // Invalidate the session
            return "redirect:/home"; // Redirect to home page after logout
      }
}
