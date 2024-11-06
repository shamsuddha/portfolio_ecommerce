package com.example.ecommerce_api.service.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import io.hms.api.controller.request_dto.BookingSearchDto;
import io.hms.api.entity.QBooking;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class BookingPredicate {


    private static final QBooking qBooking = QBooking.booking;

    public static Predicate makePredicate(BookingSearchDto bookingSearchDto) {

        BooleanBuilder builder = new BooleanBuilder();
        if (!CollectionUtils.isEmpty(bookingSearchDto.getIdList())) {
            builder.and(qBooking.id.in(bookingSearchDto.getIdList()));
        }
        if (!CollectionUtils.isEmpty(bookingSearchDto.getIdNotEqualList())) {
            builder.and(qBooking.id.notIn(bookingSearchDto.getIdNotEqualList()));
        }

    /*if (StringUtils.isNotBlank(bookingSearchDto.getBookingCategoryId())) {
      builder.and(qBooking.bookingCategoryId.equalsIgnoreCase(bookingSearchDto.getBookingCategoryId()));
    }*/
        if (StringUtils.isNotBlank(bookingSearchDto.getMultiSearchProp())) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.or(qBooking.id.containsIgnoreCase(bookingSearchDto.getMultiSearchProp()));
            builder.and(booleanBuilder);
        }

        return builder;
    }
}
