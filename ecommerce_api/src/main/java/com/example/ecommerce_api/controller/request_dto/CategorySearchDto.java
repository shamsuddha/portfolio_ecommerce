package com.example.ecommerce_api.controller.request_dto;

import java.util.List;
import com.example.ecommerce_api.common.SearchDto;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CategorySearchDto extends SearchDto {

    private List<String> idList;
    private List<String> idNotEqualList;

    private String code;
    private String codeIgnoreCase;
    private String name;
    private String nameIgnoreCase;
}
