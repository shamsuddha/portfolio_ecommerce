package com.example.ecommerce_api.service.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import io.hms.api.controller.request_dto.BookingCartSearchDto;
import io.hms.api.entity.QBookingCart;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class BookingCartPredicate {


    private static final QBookingCart qBookingCart = QBookingCart.bookingCart;

    public static Predicate makePredicate(BookingCartSearchDto bookingCartSearchDto) {

        BooleanBuilder builder = new BooleanBuilder();
        if (!CollectionUtils.isEmpty(bookingCartSearchDto.getIdList())) {
            builder.and(qBookingCart.id.in(bookingCartSearchDto.getIdList()));
        }
        if (!CollectionUtils.isEmpty(bookingCartSearchDto.getIdNotEqualList())) {
            builder.and(qBookingCart.id.notIn(bookingCartSearchDto.getIdNotEqualList()));
        }

    /*if (StringUtils.isNotBlank(bookingCartSearchDto.getBookingCartCategoryId())) {
      builder.and(qBookingCart.bookingCartCategoryId.equalsIgnoreCase(bookingCartSearchDto.getBookingCartCategoryId()));
    }*/
        if (StringUtils.isNotBlank(bookingCartSearchDto.getMultiSearchProp())) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.or(qBookingCart.id.containsIgnoreCase(bookingCartSearchDto.getMultiSearchProp()));
            builder.and(booleanBuilder);
        }

        return builder;
    }
}
