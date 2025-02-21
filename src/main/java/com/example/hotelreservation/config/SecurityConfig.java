package com.example.hotelreservation.config;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.hotelreservation.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.example.hotelreservation.session.SessionConstants;
import jakarta.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

      @Autowired
      private CustomUserDetailsService customUserDetailsService;

      @Bean
      public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
      }

      @Bean
      public DaoAuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(customUserDetailsService);
            authProvider.setPasswordEncoder(passwordEncoder());
            return authProvider;
      }

      @Bean
      public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                        .authorizeHttpRequests((requests) -> requests
                                    .requestMatchers("/", "/home", "/freeRooms", "/freeRooms/**", "/room/**",
                                                "/rooms/image/**", "/css/**", "/images/**")
                                    .permitAll()
                                    .requestMatchers("/admin/**").hasRole("ADMIN")
                                    .anyRequest().authenticated())
                        .formLogin((form) -> form
                                    .loginPage("/login")
                                    .permitAll()
                                    .successHandler((request, response, authentication) -> {
                                          String role = authentication.getAuthorities().stream()
                                                      .map(grantedAuthority -> grantedAuthority.getAuthority())
                                                      .findFirst().orElse(null);
                                          HttpSession session = request.getSession();
                                          session.setAttribute(SessionConstants.USER_KEY, role);
                                          response.sendRedirect("/home");
                                    }))
                        .logout((logout) -> logout
                                    .permitAll()
                                    .logoutSuccessUrl("/home")
                                    .invalidateHttpSession(true)
                                    .clearAuthentication(true)
                                    .deleteCookies("JSESSIONID"))
                        .exceptionHandling(exception -> exception
                                    .accessDeniedPage("/access-denied"))
                        .sessionManagement((session) -> session
                                    .invalidSessionUrl("/login")
                                    .maximumSessions(1)
                                    .expiredUrl("/login?expired"));

            return http.build();
      }

}
