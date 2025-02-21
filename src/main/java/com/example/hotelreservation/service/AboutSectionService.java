package com.example.hotelreservation.service;

import com.example.hotelreservation.entity.AboutSection;
import com.example.hotelreservation.repository.AboutSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AboutSectionService {

      @Autowired
      private AboutSectionRepository aboutSectionRepository;

      public AboutSection getAboutSection() {
            return aboutSectionRepository.findAll().stream().findFirst().orElse(new AboutSection());
      }

      public AboutSection saveAboutSection(AboutSection aboutSection) {
            return aboutSectionRepository.save(aboutSection);
      }
}
