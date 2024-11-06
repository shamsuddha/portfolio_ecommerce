package com.example.ecommerce_api.util.api_util;


import lombok.*;
import lombok.experimental.Accessors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class ApiResponse<T> {

  private T data;
  private ApiResponseType apiResponseType;
  private UserInformErrorType userInformErrorType;
  private Boolean status;
  private String message;

}
