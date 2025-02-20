package com.example.hotelreservation.session;

import lombok.Getter;
import lombok.Setter;
import com.example.hotelreservation.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Getter
@Setter
public class SessionObject {
      User user;
      String info;
      int cos;
}