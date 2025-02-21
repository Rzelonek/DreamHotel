package com.example.hotelreservation.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

      // Handle 404 errors (Page Not Found)
      @ExceptionHandler(NoHandlerFoundException.class)
      public String handleNoHandlerFoundException(NoHandlerFoundException ex, Model model) {
            model.addAttribute("errorMessage", "The page you are looking for does not exist.");
            return "error/404";
      }

      // Handle any general exceptions
      @ExceptionHandler(Exception.class)
      public String handleException(Exception ex, Model model) {
            model.addAttribute("errorMessage", "An unexpected error occurred: " + ex.getMessage());
            return "error/error";
      }
}