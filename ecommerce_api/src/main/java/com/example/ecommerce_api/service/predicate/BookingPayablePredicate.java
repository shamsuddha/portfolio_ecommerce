package com.example.ecommerce_api.service.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.example.ecommerce_api.controller.request_dto.BookingPayableSearchDto;
import com.example.ecommerce_api.entity.QBookingPayable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class BookingPayablePredicate {


    private static final QBookingPayable qBookingPayable = QBookingPayable.bookingPayable;

    public static Predicate makePredicate(BookingPayableSearchDto bookingPayableSearchDto) {

        BooleanBuilder builder = new BooleanBuilder();
        if (!CollectionUtils.isEmpty(bookingPayableSearchDto.getIdList())) {
            builder.and(qBookingPayable.id.in(bookingPayableSearchDto.getIdList()));
        }
        if (!CollectionUtils.isEmpty(bookingPayableSearchDto.getIdNotEqualList())) {
            builder.and(qBookingPayable.id.notIn(bookingPayableSearchDto.getIdNotEqualList()));
        }

    /*if (StringUtils.isNotBlank(bookingPayableSearchDto.getBookingPayableCategoryId())) {
      builder.and(qBookingPayable.bookingPayableCategoryId.equalsIgnoreCase(bookingPayableSearchDto.getBookingPayableCategoryId()));
    }*/
        if (StringUtils.isNotBlank(bookingPayableSearchDto.getMultiSearchProp())) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.or(qBookingPayable.id.containsIgnoreCase(bookingPayableSearchDto.getMultiSearchProp()));
            builder.and(booleanBuilder);
        }

        return builder;
    }
}
