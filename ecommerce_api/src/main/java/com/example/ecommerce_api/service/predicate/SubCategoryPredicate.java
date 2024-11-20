package com.example.ecommerce_api.service.predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.example.ecommerce_api.controller.request_dto.CategorySearchDto;
import com.example.ecommerce_api.controller.request_dto.SubCategorySearchDto;
import com.example.ecommerce_api.entity.QCategory;
import com.example.ecommerce_api.entity.QSubCategory;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public class SubCategoryPredicate {

    private static final QSubCategory qSubCategory = QSubCategory.subCategory;

    public static Predicate makePredicate(SubCategorySearchDto subCategorySearchDto) {

        BooleanBuilder builder = new BooleanBuilder();

        if (!CollectionUtils.isEmpty(subCategorySearchDto.getIdList())) {
            builder.and(qSubCategory.id.in(subCategorySearchDto.getIdList()));
        }
        if (!CollectionUtils.isEmpty(subCategorySearchDto.getIdNotEqualList())) {
            builder.and(qSubCategory.id.notIn(subCategorySearchDto.getIdNotEqualList()));
        }
        if (StringUtils.isNotBlank(subCategorySearchDto.getName())) {
            builder.and(qSubCategory.name.equalsIgnoreCase(subCategorySearchDto.getName()));
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
