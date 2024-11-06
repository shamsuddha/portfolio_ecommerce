package com.example.ecommerce_api.service;


import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import io.hms.api.controller.request_dto.RoomCategoryRateTypeSearchDto;
import io.hms.api.entity.RoomCategoryRateType;
import io.hms.api.entity.QRoomCategoryRateType;
import io.hms.api.repository.RoomCategoryRateTypeRepository;
import io.hms.api.service.common.EntityValidationService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static io.hms.api.service.predicate.RoomCategoryRateTypePredicate.makePredicate;

@Service
@AllArgsConstructor
public class RoomCategoryRateTypeService {

  private final EntityManager entityManager;
  private final EntityValidationService entityValidationService;
  private final RoomCategoryRateTypeRepository roomCategoryRateTypeRepository;

  @org.springframework.transaction.annotation.Transactional
  public RoomCategoryRateType saveRoomCategoryRateType(RoomCategoryRateType roomCategoryRateType) {
    return this.roomCategoryRateTypeRepository.save(roomCategoryRateType);
  }

  @org.springframework.transaction.annotation.Transactional
  public RoomCategoryRateType updateRoomCategoryRateType(RoomCategoryRateType roomCategoryRateType) {
    var roomCategoryRateTypeDb = entityValidationService.validateRoomCategoryRateType(roomCategoryRateType.getId());
    roomCategoryRateTypeDb.setRate(roomCategoryRateType.getRate());
    roomCategoryRateTypeDb = roomCategoryRateTypeRepository.save(roomCategoryRateTypeDb);
    return roomCategoryRateTypeDb;
  }

  @org.springframework.transaction.annotation.Transactional
  public String deleteRoomCategoryRateType(RoomCategoryRateType roomCategoryRateType) {
    var roomCategoryRateTypeDb = entityValidationService.validateRoomCategoryRateType(roomCategoryRateType.getId());
    roomCategoryRateTypeDb.setEnabled(Boolean.FALSE);
    roomCategoryRateTypeRepository.save(roomCategoryRateTypeDb);
    return "RoomCategoryRateType deleted successfully";
  }

  public Page<RoomCategoryRateType> searchRoomCategoryRateType(RoomCategoryRateTypeSearchDto roomCategoryRateTypeSearchDto) {
    Predicate predicate = makePredicate(roomCategoryRateTypeSearchDto);
    Pageable pageable = PageRequest.of(roomCategoryRateTypeSearchDto.getPage(), roomCategoryRateTypeSearchDto.getSize());
    final QRoomCategoryRateType qRoomCategoryRateType = QRoomCategoryRateType.roomCategoryRateType;
    var query = new JPAQuery<RoomCategoryRateType>(entityManager)
            .from(qRoomCategoryRateType)
            .where(predicate)
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .orderBy(qRoomCategoryRateType.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }
}
