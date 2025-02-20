package com.example.hotelreservation.controller;

import com.example.hotelreservation.entity.RoomCategory;
import com.example.hotelreservation.service.RoomCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import com.example.hotelreservation.repository.RoomCategoryRepository;

@Controller
@RequestMapping("/admin")
public class AdminRoomController {

      @Autowired
      private RoomCategoryService roomCategoryService;

      @Autowired
      private RoomCategoryRepository roomCategoryRepository;

      // List all room categories
      @GetMapping("/rooms")
      public String listRooms(Model model) {
            model.addAttribute("rooms", roomCategoryService.getAllRoomCategories());
            return "adminRooms";
      }

      // Form to add a new room category
      @GetMapping("/rooms/add")
      public String addRoomForm(Model model) {
            model.addAttribute("roomCategory", new RoomCategory());
            return "addRoom";
      }

      // Handle submission of a new room category
      @PostMapping("/rooms/add")
      public String addRoom(@ModelAttribute RoomCategory roomCategory,
                  @RequestParam("imageFile") MultipartFile imageFile) {
            if (!imageFile.isEmpty()) {
                  try {
                        roomCategory.setImage(imageFile.getBytes());
                  } catch (IOException e) {
                        e.printStackTrace();
                  }
            }
            roomCategoryRepository.save(roomCategory);
            return "redirect:/admin/rooms";
      }

      // Formularz edycji istniejącej kategorii
      @GetMapping("/rooms/edit/{id}")
      public String editRoomForm(@PathVariable Long id, Model model) {
            Optional<RoomCategory> opt = roomCategoryRepository.findById(id);
            if (opt.isPresent()) {
                  model.addAttribute("roomCategory", opt.get());
                  return "editRoom";
            } else {
                  model.addAttribute("error", "Nie znaleziono kategorii pokoju o ID: " + id);
                  return "redirect:/admin/rooms";
            }
      }

      // Obsługa edycji (aktualizacji) kategorii pokoju
      @PostMapping("/rooms/edit")
      public String editRoom(@ModelAttribute RoomCategory roomCategory,
                  @RequestParam("imageFile") MultipartFile imageFile) {
            Optional<RoomCategory> existingOpt = roomCategoryRepository.findById(roomCategory.getId());
            if (existingOpt.isPresent()) {
                  RoomCategory existing = existingOpt.get();
                  existing.setType(roomCategory.getType());
                  existing.setName(roomCategory.getName());
                  existing.setCapacity(roomCategory.getCapacity());
                  existing.setAmount(roomCategory.getAmount());
                  existing.setDescription(roomCategory.getDescription());
                  existing.setPricePerNight(roomCategory.getPricePerNight());
                  if (!imageFile.isEmpty()) {
                        try {
                              existing.setImage(imageFile.getBytes());
                        } catch (IOException e) {
                              e.printStackTrace();
                        }
                  }
                  roomCategoryRepository.save(existing);
            }
            return "redirect:/admin/rooms";
      }

      // // Endpoint to serve the image for a given room category
      // @GetMapping("/image/{id}")
      // @ResponseBody
      // public ResponseEntity<byte[]> getRoomImage(@PathVariable Long id) {
      // Optional<RoomCategory> opt = roomCategoryRepository.findById(id);
      // if (opt.isPresent() && opt.get().getImage() != null) {
      // byte[] image = opt.get().getImage();
      // HttpHeaders headers = new HttpHeaders();
      // // Determine content type (assuming jpg if not detected)
      // headers.setContentType(MediaType.IMAGE_JPEG);
      // return new ResponseEntity<>(image, headers, HttpStatus.OK);
      // }
      // return ResponseEntity.notFound().build();
      // }

      @GetMapping("/image/{id}")
      public ResponseEntity<byte[]> getTripPhoto(@PathVariable Long id) {
            Optional<RoomCategory> tripOpt = roomCategoryRepository.findById(id);
            if (tripOpt.isEmpty() || tripOpt.map(RoomCategory::getImage).orElse(null) == null) {
                  return ResponseEntity.notFound().build();
            }

            byte[] photo = tripOpt.get().getImage();
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(photo);
      }

}
