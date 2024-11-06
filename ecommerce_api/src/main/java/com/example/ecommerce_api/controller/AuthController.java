package com.example.ecommerce_api.controller;

import com.example.ecommerce_api.controller.request_dto.LoginInfoDto;
import com.example.ecommerce_api.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Objects;
import com.example.ecommerce_api.dto.AccessTokenDto;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
@SuppressWarnings({ "null", "rawtypes", "unchecked" })
public class AuthController {

  private final AuthService authService;
  
  @PostMapping("/customer-login")
  public ResponseEntity<?> customerLogin(@RequestBody LoginInfoDto loginInfoDto) {
    String accessToken = authService.customerLogin(loginInfoDto);
    if(Objects.isNull(accessToken)){
      return new ResponseEntity(null, HttpStatusCode.valueOf(401));
    }
    return new ResponseEntity(new AccessTokenDto(accessToken), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/admin-login")
  public ResponseEntity<?> adminLogin(@RequestBody LoginInfoDto loginInfoDto) {
    String accessToken = authService.adminLogin(loginInfoDto);
    if(Objects.isNull(accessToken)){
      return new ResponseEntity(null, HttpStatusCode.valueOf(401));
    }
    return new ResponseEntity(new AccessTokenDto(accessToken), HttpStatusCode.valueOf(200));
  }

}
