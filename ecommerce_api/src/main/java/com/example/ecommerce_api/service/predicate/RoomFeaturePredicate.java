package com.example.ecommerce_api.service.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.example.ecommerce_api.controller.request_dto.RoomFeatureSearchDto;
import com.example.ecommerce_api.controller.request_dto.RoomSearchDto;
import com.example.ecommerce_api.entity.QRoomFeature;
import com.example.ecommerce_api.entity.QRoom;
import com.example.ecommerce_api.entity.QRoomCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class RoomFeaturePredicate {


    private static final QRoomFeature qRoomFeature = QRoomFeature.roomFeature;

    public static Predicate makePredicate(RoomFeatureSearchDto roomFeatureSearchDto) {

        BooleanBuilder builder = new BooleanBuilder();
        if (!CollectionUtils.isEmpty(roomFeatureSearchDto.getIdList())) {
            builder.and(qRoomFeature.id.in(roomFeatureSearchDto.getIdList()));
        }
        if (!CollectionUtils.isEmpty(roomFeatureSearchDto.getIdNotEqualList())) {
            builder.and(qRoomFeature.id.notIn(roomFeatureSearchDto.getIdNotEqualList()));
        }

    /*if (StringUtils.isNotBlank(roomFeatureSearchDto.getRoomFeatureCategoryId())) {
      builder.and(qRoomFeature.roomFeatureCategoryId.equalsIgnoreCase(roomFeatureSearchDto.getRoomFeatureCategoryId()));
    }*/
        if (StringUtils.isNotBlank(roomFeatureSearchDto.getMultiSearchProp())) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.or(qRoomFeature.id.containsIgnoreCase(roomFeatureSearchDto.getMultiSearchProp()));
            builder.and(booleanBuilder);
        }

        return builder;
    }
}
