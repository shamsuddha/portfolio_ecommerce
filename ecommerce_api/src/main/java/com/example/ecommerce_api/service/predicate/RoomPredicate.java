package com.example.ecommerce_api.service.predicate;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.example.ecommerce_api.controller.request_dto.RoomSearchDto;
import com.example.ecommerce_api.entity.QRoom;
import com.example.ecommerce_api.entity.QRoomCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Objects;

public class RoomPredicate {

  private static final QRoom qRoom = QRoom.room;

  public static Predicate makePredicate(RoomSearchDto roomSearchDto) {

    BooleanBuilder builder = new BooleanBuilder();

    if (!CollectionUtils.isEmpty(roomSearchDto.getIdList())) {
      builder.and(qRoom.id.in(roomSearchDto.getIdList()));
    }
    if (!CollectionUtils.isEmpty(roomSearchDto.getIdNotEqualList())) {
      builder.and(qRoom.id.notIn(roomSearchDto.getIdNotEqualList()));
    }
    if (StringUtils.isNotBlank(roomSearchDto.getCode())) {
      builder.and(qRoom.code.eq(roomSearchDto.getCode()));
    }
    if (StringUtils.isNotBlank(roomSearchDto.getCodeIgnoreCase())) {
      builder.and(qRoom.code.equalsIgnoreCase(roomSearchDto.getCodeIgnoreCase()));
    }
    if (StringUtils.isNotBlank(roomSearchDto.getName())) {
      builder.and(qRoom.name.equalsIgnoreCase(roomSearchDto.getName()));
    }
    if (Objects.nonNull(roomSearchDto.getNumberOfAdultGuest())) {
      builder.and(qRoom.numberOfAdultGuest.eq(roomSearchDto.getNumberOfAdultGuest()));
    }

    if (Objects.nonNull(roomSearchDto.getNumberOfChildGuest())) {
      builder.and(qRoom.numberOfChildGuest.eq(roomSearchDto.getNumberOfChildGuest()));
    }

    if (StringUtils.isNotBlank(roomSearchDto.getName())) {
      builder.and(qRoom.name.eq(roomSearchDto.getName()));
    }

    /*if (StringUtils.isNotBlank(roomSearchDto.getRoomCategoryId())) {
      builder.and(qRoom.roomCategoryId.equalsIgnoreCase(roomSearchDto.getRoomCategoryId()));
    }*/
    if (StringUtils.isNotBlank(roomSearchDto.getMultiSearchProp())) {
      BooleanBuilder booleanBuilder = new BooleanBuilder();
      booleanBuilder.or(qRoom.code.containsIgnoreCase(roomSearchDto.getMultiSearchProp()))
        .or(qRoom.name.containsIgnoreCase(roomSearchDto.getMultiSearchProp()))
        .or(qRoom.roomCategoryId.containsIgnoreCase(roomSearchDto.getMultiSearchProp()));
      builder.and(booleanBuilder);
    }
    return builder;
  }
}
