package com.example.ecommerce_api.service.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import io.hms.api.controller.request_dto.ComplainSearchDto;
import io.hms.api.entity.QComplain;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class ComplainPredicate {

    private static final QComplain qComplain = QComplain.complain;

    public static Predicate makePredicate(ComplainSearchDto complainSearchDto) {

        BooleanBuilder builder = new BooleanBuilder();
        if (!CollectionUtils.isEmpty(complainSearchDto.getIdList())) {
            builder.and(qComplain.id.in(complainSearchDto.getIdList()));
        }
        if (!CollectionUtils.isEmpty(complainSearchDto.getIdNotEqualList())) {
            builder.and(qComplain.id.notIn(complainSearchDto.getIdNotEqualList()));
        }

    /*if (StringUtils.isNotBlank(complainSearchDto.getComplainCategoryId())) {
      builder.and(qComplain.complainCategoryId.equalsIgnoreCase(complainSearchDto.getComplainCategoryId()));
    }*/
        if (StringUtils.isNotBlank(complainSearchDto.getMultiSearchProp())) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.or(qComplain.id.containsIgnoreCase(complainSearchDto.getMultiSearchProp()));
            builder.and(booleanBuilder);
        }

        return builder;
    }
}
