package com.example.ecommerce_api.controller.request_dto;


import lombok.*;
import lombok.experimental.Accessors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class GuestInfoLoginDto {

  private String email;
  private String password;
}
