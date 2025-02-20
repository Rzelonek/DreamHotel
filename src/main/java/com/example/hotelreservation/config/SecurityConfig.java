package com.example.hotelreservation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import com.example.hotelreservation.Filters.AdminFilter;
import com.example.hotelreservation.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

      // used from another project ( dream travel ) just for testing resons
      // @Bean
      // public FilterRegistrationBean<AdminFilter> filterRegistrationBean() {
      // FilterRegistrationBean<AdminFilter> registrationBean = new
      // FilterRegistrationBean<>();

      // registrationBean.setFilter(new AdminFilter());
      // registrationBean.addUrlPatterns("/admin/*");
      // registrationBean.setOrder(1);

      // return registrationBean;
      // }

      @Autowired
      private UserRepository userRepository;

      @Bean
      public UserDetailsService userDetailsService() {
            return username -> {
                  com.example.hotelreservation.model.User appUser = userRepository.findByLogin(username); // Look up
                                                                                                          // user by
                                                                                                          // login
                  if (appUser == null) {
                        throw new UsernameNotFoundException("User not found");
                  }
                  return org.springframework.security.core.userdetails.User
                              .withUsername(appUser.getLogin())
                              .password(appUser.getPassword()) // Get password from the database (hashed)
                              .roles(appUser.getRole().name())
                              .build();
            };
      }

      @Bean
      public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                        .authorizeHttpRequests((requests) -> requests
                                    .requestMatchers("/", "/home", "/freeRooms", "/freeRooms/**", "/room/**",
                                                "/rooms/image/**", "/css/**", "/images/**")
                                    .permitAll()
                                    .anyRequest().authenticated())

                        .formLogin((form) -> form
                                    .loginPage("/login")
                                    .permitAll())
                        .logout((logout) -> logout
                                    .permitAll()
                                    .logoutSuccessUrl("/home") // Redirect to home page after logout
                                    .invalidateHttpSession(true) // Invalidate the session on logout
                                    .clearAuthentication(true) // Clear authentication
                                    .deleteCookies("JSESSIONID") // Remove the session cookie
                        )
                        .sessionManagement((session) -> session
                                    .invalidSessionUrl("/login") // Redirect to login page if session is invalid
                                    .maximumSessions(1) // Optional: limit to one session per user
                                    .expiredUrl("/login?expired") // Optional: redirect when session expires
                        )

            ;

            return http.build();
      }

      @Bean
      public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
      }

}
