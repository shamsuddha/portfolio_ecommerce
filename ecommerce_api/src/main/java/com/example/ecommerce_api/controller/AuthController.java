package com.example.ecommerce_api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class AuthController {

  private final AuthService loginService;

  @PostMapping("/customer-login")
  public ResponseEntity<String> customerLogin(@RequestBody LoginInfoDto loginInfoDto) {
    String accessToken = loginService.customerLogin(loginInfoDto);
    if(Objects.isNull(accessToken)){
      return new ResponseEntity(null, HttpStatusCode.valueOf(401));
    }
    return new ResponseEntity(accessToken, HttpStatusCode.valueOf(200));
  }

  @PostMapping("/admin-login")
  public ResponseEntity<String> adminLogin(@RequestBody LoginInfoDto loginInfoDto) {
    String accessToken = loginService.adminLogin(loginInfoDto);
    if(Objects.isNull(accessToken)){
      return new ResponseEntity(null, HttpStatusCode.valueOf(401));
    }
    return new ResponseEntity(accessToken, HttpStatusCode.valueOf(200));
  }

}
