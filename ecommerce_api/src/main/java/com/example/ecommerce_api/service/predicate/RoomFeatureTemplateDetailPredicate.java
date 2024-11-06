package com.example.ecommerce_api.service.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import io.hms.api.controller.request_dto.RoomFeatureTemplateDetailSearchDto;
import io.hms.api.controller.request_dto.RoomSearchDto;
import io.hms.api.entity.QRoomFeatureTemplateDetail;
import io.hms.api.entity.QRoom;
import io.hms.api.entity.QRoomCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class RoomFeatureTemplateDetailPredicate {

    private static final QRoomFeatureTemplateDetail qRoomFeatureTemplateDetail = QRoomFeatureTemplateDetail.roomFeatureTemplateDetail;

    public static Predicate makePredicate(RoomFeatureTemplateDetailSearchDto roomFeatureTemplateDetailSearchDto) {

        BooleanBuilder builder = new BooleanBuilder();
        if (!CollectionUtils.isEmpty(roomFeatureTemplateDetailSearchDto.getIdList())) {
            builder.and(qRoomFeatureTemplateDetail.id.in(roomFeatureTemplateDetailSearchDto.getIdList()));
        }
        if (!CollectionUtils.isEmpty(roomFeatureTemplateDetailSearchDto.getIdNotEqualList())) {
            builder.and(qRoomFeatureTemplateDetail.id.notIn(roomFeatureTemplateDetailSearchDto.getIdNotEqualList()));
        }

    /*if (StringUtils.isNotBlank(roomFeatureTemplateDetailSearchDto.getRoomFeatureTemplateDetailCategoryId())) {
      builder.and(qRoomFeatureTemplateDetail.roomFeatureTemplateDetailCategoryId.equalsIgnoreCase(roomFeatureTemplateDetailSearchDto.getRoomFeatureTemplateDetailCategoryId()));
    }*/
        if (StringUtils.isNotBlank(roomFeatureTemplateDetailSearchDto.getMultiSearchProp())) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.or(qRoomFeatureTemplateDetail.id.containsIgnoreCase(roomFeatureTemplateDetailSearchDto.getMultiSearchProp()));
            builder.and(booleanBuilder);
        }

        return builder;
    }
}
