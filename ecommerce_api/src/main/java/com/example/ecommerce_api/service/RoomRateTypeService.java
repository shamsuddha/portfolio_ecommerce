package com.example.ecommerce_api.service;


import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.example.ecommerce_api.controller.request_dto.RoomRateTypeSearchDto;
import com.example.ecommerce_api.entity.RoomRateType;
import com.example.ecommerce_api.entity.QRoomRateType;
import com.example.ecommerce_api.repository.RoomRateTypeRepository;
import com.example.ecommerce_api.service.common.EntityValidationService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.example.ecommerce_api.service.predicate.RoomRateTypePredicate.makePredicate;

@Service
@AllArgsConstructor
public class RoomRateTypeService {

  private final EntityManager entityManager;
  private final EntityValidationService entityValidationService;
  private final RoomRateTypeRepository roomRateTypeRepository;

  @org.springframework.transaction.annotation.Transactional
  public RoomRateType saveRoomRateType(RoomRateType roomRateType) {
    return this.roomRateTypeRepository.save(roomRateType);
  }

  @org.springframework.transaction.annotation.Transactional
  public RoomRateType updateRoomRateType(RoomRateType roomRateType) {
    var roomRateTypeDb = entityValidationService.validateRoomRateType(roomRateType.getId());
    roomRateTypeDb.setName(roomRateType.getName());
    roomRateTypeDb.setCode(roomRateType.getCode());
    roomRateTypeDb = roomRateTypeRepository.save(roomRateTypeDb);
    return roomRateTypeDb;
  }

  @org.springframework.transaction.annotation.Transactional
  public String deleteRoomRateType(RoomRateType roomRateType) {
    var roomRateTypeDb = entityValidationService.validateRoomRateType(roomRateType.getId());
    roomRateTypeDb.setEnabled(Boolean.FALSE);
    roomRateTypeRepository.save(roomRateTypeDb);
    return "RoomRateType deleted successfully";
  }

  public Page<RoomRateType> searchRoomRateType(RoomRateTypeSearchDto roomRateTypeSearchDto) {
    Predicate predicate = makePredicate(roomRateTypeSearchDto);
    Pageable pageable = PageRequest.of(roomRateTypeSearchDto.getPage(), roomRateTypeSearchDto.getSize());
    final QRoomRateType qRoomRateType = QRoomRateType.roomRateType;
    var query = new JPAQuery<RoomRateType>(entityManager)
            .from(qRoomRateType)
            .where(predicate)
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .orderBy(qRoomRateType.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }
}
