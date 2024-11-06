package com.example.ecommerce_api.controller.request_dto;
import com.example.ecommerce_api.common.SearchDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CountrySearchDto extends SearchDto {

    private List<String> idList;
    private List<String> idNeList;

    private String name;
    private String nameIc;

    private String code;
    private String codeIc;
}
