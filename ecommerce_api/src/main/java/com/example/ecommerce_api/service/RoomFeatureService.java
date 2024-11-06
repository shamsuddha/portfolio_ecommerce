package com.example.ecommerce_api.service;


import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import io.hms.api.controller.request_dto.RoomFeatureSearchDto;
import io.hms.api.entity.RoomFeature;
import io.hms.api.entity.QRoomFeature;
import io.hms.api.repository.RoomFeatureRepository;
import io.hms.api.service.common.EntityValidationService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static io.hms.api.service.predicate.RoomFeaturePredicate.makePredicate;

@Service
@AllArgsConstructor
public class RoomFeatureService {

  private final EntityManager entityManager;
  private final EntityValidationService entityValidationService;
  private final RoomFeatureRepository roomFeatureRepository;

  @org.springframework.transaction.annotation.Transactional
  public RoomFeature saveRoomFeature(RoomFeature roomFeature) {
    return this.roomFeatureRepository.save(roomFeature);
  }

  @org.springframework.transaction.annotation.Transactional
  public RoomFeature updateRoomFeature(RoomFeature roomFeature) {
    var roomFeatureDb = entityValidationService.validateRoomFeature(roomFeature.getId());
    roomFeatureDb.setQuantity(roomFeature.getQuantity());
    roomFeatureDb.setRate(roomFeature.getRate());
    roomFeatureDb.setTotalAmount(roomFeature.getTotalAmount());
    roomFeatureDb.setOverriddenTotalAmount(roomFeature.getOverriddenTotalAmount());
    roomFeatureDb = roomFeatureRepository.save(roomFeatureDb);
    return roomFeatureDb;
  }

  @org.springframework.transaction.annotation.Transactional
  public String deleteRoomFeature(RoomFeature roomFeature) {
    var roomFeatureDb = entityValidationService.validateRoomFeature(roomFeature.getId());
    roomFeatureDb.setEnabled(Boolean.FALSE);
    roomFeatureRepository.save(roomFeatureDb);
    return "RoomFeature deleted successfully";
  }

  public Page<RoomFeature> searchRoomFeature(RoomFeatureSearchDto roomFeatureSearchDto) {
    Predicate predicate = makePredicate(roomFeatureSearchDto);
    Pageable pageable = PageRequest.of(roomFeatureSearchDto.getPage(), roomFeatureSearchDto.getSize());
    final QRoomFeature qRoomFeature = QRoomFeature.roomFeature;
    var query = new JPAQuery<RoomFeature>(entityManager)
            .from(qRoomFeature)
            .where(predicate)
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .orderBy(qRoomFeature.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }
}
