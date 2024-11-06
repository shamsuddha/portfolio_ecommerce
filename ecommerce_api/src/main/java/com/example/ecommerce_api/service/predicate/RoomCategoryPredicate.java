package com.example.ecommerce_api.service.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.example.ecommerce_api.controller.request_dto.RoomCategorySearchDto;
import com.example.ecommerce_api.entity.QRoomCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class RoomCategoryPredicate {

    private static final QRoomCategory qRoomCategory = QRoomCategory.roomCategory;
   // private static final QRoomCategoryCategory qRoomCategoryCategory = QRoomCategoryCategory.roomCategoryCategory;

    public static Predicate makePredicate(RoomCategorySearchDto roomCategorySearchDto) {

        BooleanBuilder builder = new BooleanBuilder();
        if (!CollectionUtils.isEmpty(roomCategorySearchDto.getIdList())) {
            builder.and(qRoomCategory.id.in(roomCategorySearchDto.getIdList()));
        }
        if (!CollectionUtils.isEmpty(roomCategorySearchDto.getIdNotEqualList())) {
            builder.and(qRoomCategory.id.notIn(roomCategorySearchDto.getIdNotEqualList()));
        }
        if (StringUtils.isNotBlank(roomCategorySearchDto.getCode())) {
            builder.and(qRoomCategory.code.eq(roomCategorySearchDto.getCode()));
        }
        if (StringUtils.isNotBlank(roomCategorySearchDto.getCodeIgnoreCase())) {
            builder.and(qRoomCategory.code.equalsIgnoreCase(roomCategorySearchDto.getCodeIgnoreCase()));
        }
        if (StringUtils.isNotBlank(roomCategorySearchDto.getName())) {
            builder.and(qRoomCategory.name.equalsIgnoreCase(roomCategorySearchDto.getName()));
        }
    /*if (StringUtils.isNotBlank(roomCategorySearchDto.getRoomCategoryCategoryId())) {
      builder.and(qRoomCategory.roomCategoryCategoryId.equalsIgnoreCase(roomCategorySearchDto.getRoomCategoryCategoryId()));
    }*/
        if (StringUtils.isNotBlank(roomCategorySearchDto.getMultiSearchProp())) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.or(qRoomCategory.code.containsIgnoreCase(roomCategorySearchDto.getMultiSearchProp()))
                    .or(qRoomCategory.name.containsIgnoreCase(roomCategorySearchDto.getMultiSearchProp()));
                    //.or(qRoomCategory.roomCategoryCategoryId.containsIgnoreCase(roomCategorySearchDto.getMultiSearchProp()));
            builder.and(booleanBuilder);
        }

        return builder;
    }

}
