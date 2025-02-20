package com.example.hotelreservation.repository;

import com.example.hotelreservation.entity.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomCategoryRepository extends JpaRepository<RoomCategory, Long> {
      List<RoomCategory> findByFeaturedTrueOrderByDisplayOrderAsc();
}
