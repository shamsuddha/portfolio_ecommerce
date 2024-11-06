package com.example.ecommerce_api.dto;

import lombok.*;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class AccessTokenDto {

  private String accessToken;

}
