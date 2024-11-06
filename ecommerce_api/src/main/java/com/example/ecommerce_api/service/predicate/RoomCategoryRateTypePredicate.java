package com.example.ecommerce_api.service.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.example.ecommerce_api.controller.request_dto.RoomCategoryRateTypeSearchDto;
import com.example.ecommerce_api.controller.request_dto.RoomSearchDto;
import com.example.ecommerce_api.entity.QRoomCategoryRateType;
import com.example.ecommerce_api.entity.QRoom;
import com.example.ecommerce_api.entity.QRoomCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class RoomCategoryRateTypePredicate {


    private static final QRoomCategoryRateType qRoomCategoryRateType = QRoomCategoryRateType.roomCategoryRateType;

    public static Predicate makePredicate(RoomCategoryRateTypeSearchDto roomCategoryRateTypeSearchDto) {

        BooleanBuilder builder = new BooleanBuilder();
        if (!CollectionUtils.isEmpty(roomCategoryRateTypeSearchDto.getIdList())) {
            builder.and(qRoomCategoryRateType.id.in(roomCategoryRateTypeSearchDto.getIdList()));
        }
        if (!CollectionUtils.isEmpty(roomCategoryRateTypeSearchDto.getIdNotEqualList())) {
            builder.and(qRoomCategoryRateType.id.notIn(roomCategoryRateTypeSearchDto.getIdNotEqualList()));
        }

    /*if (StringUtils.isNotBlank(roomCategoryRateTypeSearchDto.getRoomCategoryRateTypeCategoryId())) {
      builder.and(qRoomCategoryRateType.roomCategoryRateTypeCategoryId.equalsIgnoreCase(roomCategoryRateTypeSearchDto.getRoomCategoryRateTypeCategoryId()));
    }*/
        if (StringUtils.isNotBlank(roomCategoryRateTypeSearchDto.getMultiSearchProp())) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.or(qRoomCategoryRateType.id.containsIgnoreCase(roomCategoryRateTypeSearchDto.getMultiSearchProp()));
            builder.and(booleanBuilder);
        }

        return builder;
    }
}
