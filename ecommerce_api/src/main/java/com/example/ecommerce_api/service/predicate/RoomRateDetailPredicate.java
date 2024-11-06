package com.example.ecommerce_api.service.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.example.ecommerce_api.controller.request_dto.RoomRateDetailSearchDto;
import com.example.ecommerce_api.controller.request_dto.RoomSearchDto;
import com.example.ecommerce_api.entity.QRoomRateDetail;
import com.example.ecommerce_api.entity.QRoom;
import com.example.ecommerce_api.entity.QRoomCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class RoomRateDetailPredicate {


    private static final QRoomRateDetail qRoomRateDetail = QRoomRateDetail.roomRateDetail;

    public static Predicate makePredicate(RoomRateDetailSearchDto roomRateDetailSearchDto) {

        BooleanBuilder builder = new BooleanBuilder();
        if (!CollectionUtils.isEmpty(roomRateDetailSearchDto.getIdList())) {
            builder.and(qRoomRateDetail.id.in(roomRateDetailSearchDto.getIdList()));
        }
        if (!CollectionUtils.isEmpty(roomRateDetailSearchDto.getIdNotEqualList())) {
            builder.and(qRoomRateDetail.id.notIn(roomRateDetailSearchDto.getIdNotEqualList()));
        }

    /*if (StringUtils.isNotBlank(roomRateDetailSearchDto.getRoomRateDetailCategoryId())) {
      builder.and(qRoomRateDetail.roomRateDetailCategoryId.equalsIgnoreCase(roomRateDetailSearchDto.getRoomRateDetailCategoryId()));
    }*/
        if (StringUtils.isNotBlank(roomRateDetailSearchDto.getMultiSearchProp())) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.or(qRoomRateDetail.id.containsIgnoreCase(roomRateDetailSearchDto.getMultiSearchProp()));
            builder.and(booleanBuilder);
        }

        return builder;
    }
}