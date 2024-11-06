package com.example.ecommerce_api.service.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.example.ecommerce_api.controller.request_dto.BookingDetailSearchDto;
import com.example.ecommerce_api.entity.QBookingDetail;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class BookingDetailPredicate {


    private static final QBookingDetail qBookingDetail = QBookingDetail.bookingDetail;

    public static Predicate makePredicate(BookingDetailSearchDto bookingDetailSearchDto) {

        BooleanBuilder builder = new BooleanBuilder();
        if (!CollectionUtils.isEmpty(bookingDetailSearchDto.getIdList())) {
            builder.and(qBookingDetail.id.in(bookingDetailSearchDto.getIdList()));
        }
        if (!CollectionUtils.isEmpty(bookingDetailSearchDto.getIdNotEqualList())) {
            builder.and(qBookingDetail.id.notIn(bookingDetailSearchDto.getIdNotEqualList()));
        }

    /*if (StringUtils.isNotBlank(bookingDetailSearchDto.getBookingDetailCategoryId())) {
      builder.and(qBookingDetail.bookingDetailCategoryId.equalsIgnoreCase(bookingDetailSearchDto.getBookingDetailCategoryId()));
    }*/
        if (StringUtils.isNotBlank(bookingDetailSearchDto.getMultiSearchProp())) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.or(qBookingDetail.id.containsIgnoreCase(bookingDetailSearchDto.getMultiSearchProp()));
            builder.and(booleanBuilder);
        }

        return builder;
    }
}
