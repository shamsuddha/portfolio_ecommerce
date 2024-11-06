package com.example.ecommerce_api.service.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import io.hms.api.controller.request_dto.RoomRateTypeSearchDto;
import io.hms.api.controller.request_dto.RoomSearchDto;
import io.hms.api.entity.QRoomRateType;
import io.hms.api.entity.QRoom;
import io.hms.api.entity.QRoomCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class RoomRateTypePredicate {


    private static final QRoomRateType qRoomRateType = QRoomRateType.roomRateType;

    public static Predicate makePredicate(RoomRateTypeSearchDto roomRateTypeSearchDto) {

        BooleanBuilder builder = new BooleanBuilder();
        if (!CollectionUtils.isEmpty(roomRateTypeSearchDto.getIdList())) {
            builder.and(qRoomRateType.id.in(roomRateTypeSearchDto.getIdList()));
        }
        if (!CollectionUtils.isEmpty(roomRateTypeSearchDto.getIdNotEqualList())) {
            builder.and(qRoomRateType.id.notIn(roomRateTypeSearchDto.getIdNotEqualList()));
        }

    /*if (StringUtils.isNotBlank(roomRateTypeSearchDto.getRoomRateTypeCategoryId())) {
      builder.and(qRoomRateType.roomRateTypeCategoryId.equalsIgnoreCase(roomRateTypeSearchDto.getRoomRateTypeCategoryId()));
    }*/
        if (StringUtils.isNotBlank(roomRateTypeSearchDto.getMultiSearchProp())) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.or(qRoomRateType.id.containsIgnoreCase(roomRateTypeSearchDto.getMultiSearchProp()));
            builder.and(booleanBuilder);
        }

        return builder;
    }
}
