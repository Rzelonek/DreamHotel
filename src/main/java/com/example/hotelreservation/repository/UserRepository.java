package com.example.hotelreservation.repository;

import com.example.hotelreservation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
      Optional<User> findByUsername(String username); // To find a user by their login
}
