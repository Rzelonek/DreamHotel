package com.example.hotelreservation.service;

import com.example.hotelreservation.entity.ContactInfo;
import com.example.hotelreservation.repository.ContactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactInfoService {

      @Autowired
      private ContactInfoRepository contactInfoRepository;

      public ContactInfo getContactInfo() {
            return contactInfoRepository.findAll().stream().findFirst().orElse(new ContactInfo());
      }

      public ContactInfo saveContactInfo(ContactInfo contactInfo) {
            return contactInfoRepository.save(contactInfo);
      }
}
