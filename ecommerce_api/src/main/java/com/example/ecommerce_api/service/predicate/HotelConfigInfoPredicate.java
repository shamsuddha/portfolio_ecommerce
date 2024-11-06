package com.example.ecommerce_api.service.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.example.ecommerce_api.controller.request_dto.HotelConfigInfoSearchDto;
import com.example.ecommerce_api.controller.request_dto.RoomSearchDto;
import com.example.ecommerce_api.entity.QHotelConfigInfo;
import com.example.ecommerce_api.entity.QRoom;
import com.example.ecommerce_api.entity.QRoomCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class HotelConfigInfoPredicate {


    private static final QHotelConfigInfo qHotelConfigInfo = QHotelConfigInfo.hotelConfigInfo;

    public static Predicate makePredicate(HotelConfigInfoSearchDto hotelConfigInfoSearchDto) {

        BooleanBuilder builder = new BooleanBuilder();
        if (!CollectionUtils.isEmpty(hotelConfigInfoSearchDto.getIdList())) {
            builder.and(qHotelConfigInfo.id.in(hotelConfigInfoSearchDto.getIdList()));
        }
        if (!CollectionUtils.isEmpty(hotelConfigInfoSearchDto.getIdNotEqualList())) {
            builder.and(qHotelConfigInfo.id.notIn(hotelConfigInfoSearchDto.getIdNotEqualList()));
        }

    /*if (StringUtils.isNotBlank(hotelConfigInfoSearchDto.getHotelConfigInfoCategoryId())) {
      builder.and(qHotelConfigInfo.hotelConfigInfoCategoryId.equalsIgnoreCase(hotelConfigInfoSearchDto.getHotelConfigInfoCategoryId()));
    }*/
        if (StringUtils.isNotBlank(hotelConfigInfoSearchDto.getMultiSearchProp())) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            booleanBuilder.or(qHotelConfigInfo.id.containsIgnoreCase(hotelConfigInfoSearchDto.getMultiSearchProp()));
            builder.and(booleanBuilder);
        }

        return builder;
    }
}
