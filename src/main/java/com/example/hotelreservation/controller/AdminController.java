package com.example.hotelreservation.controller;

import com.example.hotelreservation.entity.Reservation;
import com.example.hotelreservation.service.AboutSectionService;
import com.example.hotelreservation.service.ContactInfoService;
import com.example.hotelreservation.service.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.hotelreservation.entity.AboutSection;
import com.example.hotelreservation.entity.ContactInfo;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

      @Autowired
      private ReservationService reservationService;

      @Autowired
      private AboutSectionService aboutSectionService;

      @Autowired
      private ContactInfoService contactInfoService;

      @GetMapping("")
      public String adminPage() {
            return "admin"; // Render admin page

      }

      @GetMapping("/reservations")
      public String listReservations(Model model) {
            List<Reservation> reservations = reservationService.getAllReservations();
            model.addAttribute("reservations", reservations);
            return "adminReservations";
      }

      @GetMapping("/edit-about")
      public String editAbout(Model model) {
            AboutSection aboutSection = aboutSectionService.getAboutSection();
            model.addAttribute("aboutSection", aboutSection);
            return "admin/edit-about";
      }

      @PostMapping("/edit-about")
      public String saveAbout(AboutSection aboutSection, @RequestParam("imageFile") MultipartFile imageFile)
                  throws IOException {
            if (!imageFile.isEmpty()) {
                  aboutSection.setImage(imageFile.getBytes());
            }
            aboutSectionService.saveAboutSection(aboutSection);
            return "redirect:/admin/edit-about?success";
      }

      @GetMapping("/edit-contact")
      public String editContact(Model model) {
            ContactInfo contactInfo = contactInfoService.getContactInfo();
            model.addAttribute("contactInfo", contactInfo);
            return "admin/edit-contact";
      }

      @PostMapping("/edit-contact")
      public String saveContact(ContactInfo contactInfo) {
            contactInfoService.saveContactInfo(contactInfo);
            return "redirect:/admin/edit-contact?success";
      }

}
