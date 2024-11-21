package com.example.ecommerce_api.common;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto {

  private Integer page = 0;
  private Integer size = 10;
  private String multiSearchProp;

}
