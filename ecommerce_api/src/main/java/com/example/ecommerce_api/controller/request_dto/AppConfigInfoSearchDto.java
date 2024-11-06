package com.example.ecommerce_api.controller.request_dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import com.example.ecommerce_api.common.SearchDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AppConfigInfoSearchDto extends SearchDto {

    private List<String> idList;
    private List<String> idNotEqualList;

    private String code;
    private String codeIgnoreCase;
    private String name;
    private String nameIgnoreCase;
}
