package com.example.ecommerce_api.service.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import io.hms.api.controller.request_dto.CouponSearchDto;
import io.hms.api.controller.request_dto.RoomSearchDto;
import io.hms.api.entity.QCoupon;
import io.hms.api.entity.QRoom;
import io.hms.api.entity.QRoomCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class CouponPredicate {


    private static final QCoupon qCoupon = QCoupon.coupon;

    public static Predicate makePredicate(CouponSearchDto couponSearchDto) {

        BooleanBuilder builder = new BooleanBuilder();
        if (!CollectionUtils.isEmpty(couponSearchDto.getIdList())) {
            builder.and(qCoupon.id.in(couponSearchDto.getIdList()));
        }
        if (!CollectionUtils.isEmpty(couponSearchDto.getIdNotEqualList())) {
            builder.and(qCoupon.id.notIn(couponSearchDto.getIdNotEqualList()));
        }

    /*if (StringUtils.isNotBlank(couponSearchDto.getCouponCategoryId())) {
      builder.and(qCoupon.couponCategoryId.equalsIgnoreCase(couponSearchDto.getCouponCategoryId()));
    }*/
        if (StringUtils.isNotBlank(couponSearchDto.getMultiSearchProp())) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.or(qCoupon.id.containsIgnoreCase(couponSearchDto.getMultiSearchProp()));
            builder.and(booleanBuilder);
        }

        return builder;
    }
}
