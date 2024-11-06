package com.example.ecommerce_api.service.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import io.hms.api.controller.request_dto.BedSearchDto;
import io.hms.api.entity.QBed;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class BedPredicate {


  private static final QBed qBed = QBed.bed;

  public static Predicate makePredicate(BedSearchDto bedSearchDto) {

    BooleanBuilder builder = new BooleanBuilder();

    if (!CollectionUtils.isEmpty(bedSearchDto.getIdList())) {
      builder.and(qBed.id.in(bedSearchDto.getIdList()));
    }
    if (!CollectionUtils.isEmpty(bedSearchDto.getIdNotEqualList())) {
      builder.and(qBed.id.notIn(bedSearchDto.getIdNotEqualList()));
    }
    if (StringUtils.isNotBlank(bedSearchDto.getName())) {
      builder.and(qBed.name.equalsIgnoreCase(bedSearchDto.getName()));
    }

    /*if (StringUtils.isNotBlank(bedSearchDto.getBedCategoryId())) {
      builder.and(qBed.bedCategoryId.equalsIgnoreCase(bedSearchDto.getBedCategoryId()));
    }*/

    return builder;
  }
}


