package com.example.ecommerce_api.service.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.example.ecommerce_api.controller.request_dto.BookingReceivableSearchDto;
import com.example.ecommerce_api.entity.QBookingReceivable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class BookingReceivablePredicate {


    private static final QBookingReceivable qBookingReceivable = QBookingReceivable.bookingReceivable;

    public static Predicate makePredicate(BookingReceivableSearchDto bookingReceivableSearchDto) {

        BooleanBuilder builder = new BooleanBuilder();
        if (!CollectionUtils.isEmpty(bookingReceivableSearchDto.getIdList())) {
            builder.and(qBookingReceivable.id.in(bookingReceivableSearchDto.getIdList()));
        }
        if (!CollectionUtils.isEmpty(bookingReceivableSearchDto.getIdNotEqualList())) {
            builder.and(qBookingReceivable.id.notIn(bookingReceivableSearchDto.getIdNotEqualList()));
        }

    /*if (StringUtils.isNotBlank(bookingReceivableSearchDto.getBookingReceivableCategoryId())) {
      builder.and(qBookingReceivable.bookingReceivableCategoryId.equalsIgnoreCase(bookingReceivableSearchDto.getBookingReceivableCategoryId()));
    }*/
        if (StringUtils.isNotBlank(bookingReceivableSearchDto.getMultiSearchProp())) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.or(qBookingReceivable.id.containsIgnoreCase(bookingReceivableSearchDto.getMultiSearchProp()));
            builder.and(booleanBuilder);
        }

        return builder;
    }
}
