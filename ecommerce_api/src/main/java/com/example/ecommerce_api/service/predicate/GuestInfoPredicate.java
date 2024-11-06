package com.example.ecommerce_api.service.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.example.ecommerce_api.controller.request_dto.GuestInfoSearchDto;
import com.example.ecommerce_api.controller.request_dto.RoomSearchDto;
import com.example.ecommerce_api.entity.QGuestInfo;
import com.example.ecommerce_api.entity.QRoom;
import com.example.ecommerce_api.entity.QRoomCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class GuestInfoPredicate {


    private static final QGuestInfo qGuestInfo = QGuestInfo.guestInfo;

    public static Predicate makePredicate(GuestInfoSearchDto guestInfoSearchDto) {

        BooleanBuilder builder = new BooleanBuilder();
        if (!CollectionUtils.isEmpty(guestInfoSearchDto.getIdList())) {
            builder.and(qGuestInfo.id.in(guestInfoSearchDto.getIdList()));
        }
        if (!CollectionUtils.isEmpty(guestInfoSearchDto.getIdNotEqualList())) {
            builder.and(qGuestInfo.id.notIn(guestInfoSearchDto.getIdNotEqualList()));
        }

    /*if (StringUtils.isNotBlank(guestInfoSearchDto.getGuestInfoCategoryId())) {
      builder.and(qGuestInfo.guestInfoCategoryId.equalsIgnoreCase(guestInfoSearchDto.getGuestInfoCategoryId()));
    }*/
        if (StringUtils.isNotBlank(guestInfoSearchDto.getMultiSearchProp())) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.or(qGuestInfo.id.containsIgnoreCase(guestInfoSearchDto.getMultiSearchProp()));
            builder.and(booleanBuilder);
        }

        return builder;
    }
}
