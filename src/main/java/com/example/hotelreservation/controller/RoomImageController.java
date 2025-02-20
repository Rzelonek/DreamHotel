package com.example.hotelreservation.controller;

import com.example.hotelreservation.entity.RoomCategory;
import com.example.hotelreservation.repository.RoomCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/rooms")
public class RoomImageController {

      @Autowired
      private RoomCategoryRepository roomCategoryRepository;

      @GetMapping("/image/{id}")
      @ResponseBody
      public ResponseEntity<byte[]> getRoomImage(@PathVariable Long id) {
            Optional<RoomCategory> opt = roomCategoryRepository.findById(id);
            if (opt.isPresent() && opt.get().getImage() != null) {
                  byte[] image = opt.get().getImage();
                  HttpHeaders headers = new HttpHeaders();
                  // Assuming JPEG; you can improve this by checking the file type if needed.
                  headers.setContentType(MediaType.IMAGE_JPEG);
                  return new ResponseEntity<>(image, headers, HttpStatus.OK);
            }
            return ResponseEntity.notFound().build();
      }
}
