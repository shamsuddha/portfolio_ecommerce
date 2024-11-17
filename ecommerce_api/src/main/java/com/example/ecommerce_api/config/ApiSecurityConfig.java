// package com.example.ecommerce_api.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// //@EnableWebSecurity
// public class ApiSecurityConfig {

//   @Autowired
//   private JwtRequestFilter jwtRequestFilter;

//   private static final String[] AUTH_PERMIT_ALL = {
//     "/room/search",
//     "/swagger-resources/**",
//   };

//   @Bean
//   public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//     http
//       .csrf().disable()
//       .authorizeHttpRequests(requests -> requests
//         .requestMatchers(AUTH_PERMIT_ALL).permitAll()
//         .requestMatchers(HttpMethod.GET, "/", "/api/users").permitAll()
//         .requestMatchers(HttpMethod.POST, "/file-upload/**").permitAll()
//         .requestMatchers(HttpMethod.GET, "/file-download/**").permitAll()
//         .requestMatchers(HttpMethod.POST, "/app-config-info/**").permitAll()
//         .requestMatchers(HttpMethod.POST, "/category/**").permitAll()
//         .requestMatchers(HttpMethod.POST, "/country/**").permitAll()
//         .requestMatchers(HttpMethod.POST, "/coupon/**").permitAll()
//         .requestMatchers(HttpMethod.POST, "/guest-info/**").permitAll()
//         .requestMatchers(HttpMethod.POST, "/hotel-config-info/**").permitAll()
//         .requestMatchers(HttpMethod.POST, "/lookup-feature/**").permitAll()
//         .requestMatchers(HttpMethod.POST, "/room-category-rate-type/**").permitAll()
//         .requestMatchers(HttpMethod.POST, "/room-feature/**").permitAll()
//         .requestMatchers(HttpMethod.POST, "/room-feature-template/**").permitAll()
//         .requestMatchers(HttpMethod.POST, "/room-feature-template-detail/**").permitAll()
//         .requestMatchers(HttpMethod.POST, "/room-rate-detail/**").permitAll()
//         .requestMatchers(HttpMethod.POST, "/room-rate-type/**").permitAll()
//         .requestMatchers(HttpMethod.POST, "/room-category/**").permitAll()
//         .requestMatchers(HttpMethod.POST, "/booking/**").permitAll()
//         .requestMatchers(HttpMethod.POST, "/booking-cart/**").permitAll()
//         .requestMatchers(HttpMethod.POST, "/booking-detail/**").permitAll()
//         .requestMatchers(HttpMethod.POST, "/booking-payable/**").permitAll()
//         .requestMatchers(HttpMethod.POST, "/booking-receivable/**").permitAll()
//         .requestMatchers(HttpMethod.POST, "/floor/**").permitAll()
//         .requestMatchers(HttpMethod.POST, "/bed/**").permitAll()
//         .requestMatchers(HttpMethod.POST, "/notification/**").permitAll()
//         .requestMatchers(HttpMethod.POST, "/login/**").permitAll()
//         .requestMatchers(HttpMethod.GET, "/api/users/login", "/api/users/{username}").authenticated()
//         .anyRequest().denyAll())
//       .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//       .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
//       .httpBasic();
//     return http.build();
//   }

//   @Bean
//   public UserDetailsService userDetailsService() {
//     UserDetails user = User.withDefaultPasswordEncoder()
//       .username("user1").password("password1").roles("SuperAdmin", "SystemAdmin", "Admin").build();
//     return new InMemoryUserDetailsManager(user);
//   }

// }