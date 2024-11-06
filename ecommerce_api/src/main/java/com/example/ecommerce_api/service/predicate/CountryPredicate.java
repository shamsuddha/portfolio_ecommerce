package com.example.ecommerce_api.service.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import io.hms.api.controller.request_dto.CountrySearchDto;
import io.hms.api.controller.request_dto.RoomSearchDto;
import io.hms.api.entity.QCountry;
import io.hms.api.entity.QRoom;
import io.hms.api.entity.QRoomCategory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class CountryPredicate {


  private static final QCountry qCountry = QCountry.country;

  public static Predicate makePredicate(CountrySearchDto countrySearchDto) {

    BooleanBuilder bb = new BooleanBuilder();
    if (!CollectionUtils.isEmpty(countrySearchDto.getIdList())) {
      bb.and(qCountry.id.in(countrySearchDto.getIdList()));
    }
    if (!CollectionUtils.isEmpty(countrySearchDto.getIdNeList())) {
      bb.and(qCountry.id.notIn(countrySearchDto.getIdNeList()));
    }
    if (StringUtils.isNotBlank(countrySearchDto.getName())) {
      bb.or(qCountry.name.eq(countrySearchDto.getName()));
    }
    if (StringUtils.isNotBlank(countrySearchDto.getMultiSearchProp())) {
      BooleanBuilder bb2 = new BooleanBuilder();
      bb2.or(qCountry.name.containsIgnoreCase(countrySearchDto.getMultiSearchProp()))
        .or(qCountry.countryCode.containsIgnoreCase(countrySearchDto.getMultiSearchProp()));
      bb.and(bb2);
    }

    return bb;
  }
}
