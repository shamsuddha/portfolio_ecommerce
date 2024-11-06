package com.example.ecommerce_api.service.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import com.example.ecommerce_api.entity.QAppConfigInfo;

import com.example.ecommerce_api.controller.request_dto.AppConfigInfoSearchDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class AppConfigInfoPredicate {

    private static final QAppConfigInfo qAppConfigInfo = QAppConfigInfo.appConfigInfo;
    public static Predicate makePredicate(AppConfigInfoSearchDto appConfigInfoSearchDto) {

        BooleanBuilder builder = new BooleanBuilder();
        if (!CollectionUtils.isEmpty(appConfigInfoSearchDto.getIdList())) {
            builder.and(qAppConfigInfo.id.in(appConfigInfoSearchDto.getIdList()));
        }
        if (!CollectionUtils.isEmpty(appConfigInfoSearchDto.getIdNotEqualList())) {
            builder.and(qAppConfigInfo.id.notIn(appConfigInfoSearchDto.getIdNotEqualList()));
        }

        if (StringUtils.isNotBlank(appConfigInfoSearchDto.getMultiSearchProp())) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
           booleanBuilder.or(qAppConfigInfo.id.containsIgnoreCase(appConfigInfoSearchDto.getMultiSearchProp()));
            builder.and(booleanBuilder);
        }

        return builder;
    }
}
