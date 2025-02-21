package com.example.hotelreservation.service;

public interface IAuthenticationService {
      void login(String login, String password);

      void logout();

      String getLoginInfo();
}
