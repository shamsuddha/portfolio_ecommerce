package com.example.ecommerce_api.service;


import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.example.ecommerce_api.controller.request_dto.HotelConfigInfoSearchDto;
import com.example.ecommerce_api.entity.HotelConfigInfo;
import com.example.ecommerce_api.entity.QHotelConfigInfo;
import com.example.ecommerce_api.repository.HotelConfigInfoRepository;
import com.example.ecommerce_api.service.common.EntityValidationService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.example.ecommerce_api.service.predicate.HotelConfigInfoPredicate.makePredicate;

@Service
@AllArgsConstructor
public class HotelConfigInfoService {

  private final EntityManager entityManager;
  private final EntityValidationService entityValidationService;
  private final HotelConfigInfoRepository hotelConfigInfoRepository;

  @org.springframework.transaction.annotation.Transactional
  public HotelConfigInfo saveHotelConfigInfo(HotelConfigInfo hotelConfigInfo) {
    return this.hotelConfigInfoRepository.save(hotelConfigInfo);
  }

  @org.springframework.transaction.annotation.Transactional
  public HotelConfigInfo updateHotelConfigInfo(HotelConfigInfo hotelConfigInfo) {
    var hotelConfigInfoDb = entityValidationService.validateHotelConfigInfo(hotelConfigInfo.getId());
    hotelConfigInfoDb.setName(hotelConfigInfo.getName());
    hotelConfigInfoDb.setAddress(hotelConfigInfo.getAddress());
    hotelConfigInfoDb.setEmail(hotelConfigInfo.getEmail());
    hotelConfigInfoDb.setLatitude(hotelConfigInfo.getLatitude());
    hotelConfigInfoDb.setLongitude(hotelConfigInfo.getLongitude());
    hotelConfigInfoDb.setPhone(hotelConfigInfo.getPhone());
    hotelConfigInfoDb.setCountry(hotelConfigInfo.getCountry());
    hotelConfigInfoDb.setCity(hotelConfigInfo.getCity());
    hotelConfigInfoDb.setState(hotelConfigInfo.getState());
    hotelConfigInfoDb.setZipCode(hotelConfigInfo.getZipCode());
    hotelConfigInfoDb = hotelConfigInfoRepository.save(hotelConfigInfoDb);
    return hotelConfigInfoDb;
  }

  @org.springframework.transaction.annotation.Transactional
  public String deleteHotelConfigInfo(HotelConfigInfo hotelConfigInfo) {
    var hotelConfigInfoDb = entityValidationService.validateHotelConfigInfo(hotelConfigInfo.getId());
    hotelConfigInfoDb.setEnabled(Boolean.FALSE);
    hotelConfigInfoRepository.save(hotelConfigInfoDb);
    return "HotelConfigInfo deleted successfully";
  }

  public Page<HotelConfigInfo> searchHotelConfigInfo(HotelConfigInfoSearchDto hotelConfigInfoSearchDto) {
    Predicate predicate = makePredicate(hotelConfigInfoSearchDto);
    Pageable pageable = PageRequest.of(hotelConfigInfoSearchDto.getPage(), hotelConfigInfoSearchDto.getSize());
    final QHotelConfigInfo qHotelConfigInfo = QHotelConfigInfo.hotelConfigInfo;
    var query = new JPAQuery<HotelConfigInfo>(entityManager)
            .from(qHotelConfigInfo)
            .where(predicate)
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .orderBy(qHotelConfigInfo.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }
}
