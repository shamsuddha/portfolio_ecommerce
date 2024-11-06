package com.example.ecommerce_api.service.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import io.hms.api.controller.request_dto.RoomFeatureTemplateSearchDto;
import io.hms.api.controller.request_dto.RoomSearchDto;
import io.hms.api.entity.QRoomFeatureTemplate;
import io.hms.api.entity.QRoom;
import io.hms.api.entity.QRoomCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class RoomFeatureTemplatePredicate {


    private static final QRoomFeatureTemplate qRoomFeatureTemplate = QRoomFeatureTemplate.roomFeatureTemplate;

    public static Predicate makePredicate(RoomFeatureTemplateSearchDto roomFeatureTemplateSearchDto) {

        BooleanBuilder builder = new BooleanBuilder();
        if (!CollectionUtils.isEmpty(roomFeatureTemplateSearchDto.getIdList())) {
            builder.and(qRoomFeatureTemplate.id.in(roomFeatureTemplateSearchDto.getIdList()));
        }
        if (!CollectionUtils.isEmpty(roomFeatureTemplateSearchDto.getIdNotEqualList())) {
            builder.and(qRoomFeatureTemplate.id.notIn(roomFeatureTemplateSearchDto.getIdNotEqualList()));
        }

    /*if (StringUtils.isNotBlank(roomFeatureTemplateSearchDto.getRoomFeatureTemplateCategoryId())) {
      builder.and(qRoomFeatureTemplate.roomFeatureTemplateCategoryId.equalsIgnoreCase(roomFeatureTemplateSearchDto.getRoomFeatureTemplateCategoryId()));
    }*/
        if (StringUtils.isNotBlank(roomFeatureTemplateSearchDto.getMultiSearchProp())) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.or(qRoomFeatureTemplate.id.containsIgnoreCase(roomFeatureTemplateSearchDto.getMultiSearchProp()));
            builder.and(booleanBuilder);
        }

        return builder;
    }
}
