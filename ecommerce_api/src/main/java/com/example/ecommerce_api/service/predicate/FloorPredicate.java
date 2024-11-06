package com.example.ecommerce_api.service.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.example.ecommerce_api.controller.request_dto.FloorSearchDto;
import com.example.ecommerce_api.entity.QFloor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Objects;

public class FloorPredicate {

  private static final QFloor qFloor = QFloor.floor;

  public static Predicate makePredicate(FloorSearchDto floorSearchDto) {

    BooleanBuilder builder = new BooleanBuilder();

    if (!CollectionUtils.isEmpty(floorSearchDto.getIdList())) {
      builder.and(qFloor.id.in(floorSearchDto.getIdList()));
    }
    if (!CollectionUtils.isEmpty(floorSearchDto.getIdNotEqualList())) {
      builder.and(qFloor.id.notIn(floorSearchDto.getIdNotEqualList()));
    }
    if (StringUtils.isNotBlank(floorSearchDto.getName())) {
      builder.and(qFloor.name.equalsIgnoreCase(floorSearchDto.getName()));
    }

    /*if (StringUtils.isNotBlank(floorSearchDto.getFloorCategoryId())) {
      builder.and(qFloor.floorCategoryId.equalsIgnoreCase(floorSearchDto.getFloorCategoryId()));
    }*/

    return builder;
  }
}
