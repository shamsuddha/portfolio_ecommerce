package com.example.ecommerce_api.service.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.example.ecommerce_api.controller.request_dto.CategorySearchDto;
import com.example.ecommerce_api.entity.QCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class CategoryPredicate {

    private static final QCategory qCategory = QCategory.category;
    
    public static Predicate makePredicate(CategorySearchDto categorySearchDto) {

        BooleanBuilder builder = new BooleanBuilder();

        if (!CollectionUtils.isEmpty(categorySearchDto.getIdList())) {
            builder.and(qCategory.id.in(categorySearchDto.getIdList()));
        }
        if (!CollectionUtils.isEmpty(categorySearchDto.getIdNotEqualList())) {
            builder.and(qCategory.id.notIn(categorySearchDto.getIdNotEqualList()));
        }
        if (StringUtils.isNotBlank(categorySearchDto.getName())) {
            builder.and(qCategory.name.equalsIgnoreCase(categorySearchDto.getName()));
        }
        /*
         * if (StringUtils.isNotBlank(categorySearchDto.getCategoryCategoryId())) {
         * builder.and(qCategory.categoryCategoryId.equalsIgnoreCase(categorySearchDto.getCategoryCategoryId
         * ()));
         * }
         */
        return builder;
    }
}
