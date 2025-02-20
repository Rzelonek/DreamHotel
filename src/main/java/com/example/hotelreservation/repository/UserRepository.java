package com.example.hotelreservation.repository;

import com.example.hotelreservation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
      User findByLogin(String login); // To find a user by their login
}
