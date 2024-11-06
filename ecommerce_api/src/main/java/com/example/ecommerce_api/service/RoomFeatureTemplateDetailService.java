package com.example.ecommerce_api.service;


import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import io.hms.api.controller.request_dto.RoomFeatureTemplateDetailSearchDto;
import io.hms.api.entity.RoomFeatureTemplateDetail;
import io.hms.api.entity.QRoomFeatureTemplateDetail;
import io.hms.api.repository.RoomFeatureTemplateDetailRepository;
import io.hms.api.service.common.EntityValidationService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static io.hms.api.service.predicate.RoomFeatureTemplateDetailPredicate.makePredicate;

@Service
@AllArgsConstructor
public class RoomFeatureTemplateDetailService {

  private final EntityManager entityManager;
  private final EntityValidationService entityValidationService;
  private final RoomFeatureTemplateDetailRepository roomFeatureTemplateDetailRepository;

  @org.springframework.transaction.annotation.Transactional
  public RoomFeatureTemplateDetail saveRoomFeatureTemplateDetail(RoomFeatureTemplateDetail roomFeatureTemplateDetail) {
    return this.roomFeatureTemplateDetailRepository.save(roomFeatureTemplateDetail);
  }

  @org.springframework.transaction.annotation.Transactional
  public RoomFeatureTemplateDetail updateRoomFeatureTemplateDetail(RoomFeatureTemplateDetail roomFeatureTemplateDetail) {
    var roomFeatureTemplateDetailDb = entityValidationService.validateRoomFeatureTemplateDetail(roomFeatureTemplateDetail.getId());
    roomFeatureTemplateDetailDb.setQuantity(roomFeatureTemplateDetail.getQuantity());
    roomFeatureTemplateDetailDb.setRate(roomFeatureTemplateDetail.getRate());
    roomFeatureTemplateDetailDb.setTotalAmount(roomFeatureTemplateDetail.getTotalAmount());
    roomFeatureTemplateDetailDb = roomFeatureTemplateDetailRepository.save(roomFeatureTemplateDetailDb);
    return roomFeatureTemplateDetailDb;
  }

  @org.springframework.transaction.annotation.Transactional
  public String deleteRoomFeatureTemplateDetail(RoomFeatureTemplateDetail roomFeatureTemplateDetail) {
    var roomFeatureTemplateDetailDb = entityValidationService.validateRoomFeatureTemplateDetail(roomFeatureTemplateDetail.getId());
    roomFeatureTemplateDetailDb.setEnabled(Boolean.FALSE);
    roomFeatureTemplateDetailRepository.save(roomFeatureTemplateDetailDb);
    return "RoomFeatureTemplateDetail deleted successfully";
  }

  public Page<RoomFeatureTemplateDetail> searchRoomFeatureTemplateDetail(RoomFeatureTemplateDetailSearchDto roomFeatureTemplateDetailSearchDto) {
    Predicate predicate = makePredicate(roomFeatureTemplateDetailSearchDto);
    Pageable pageable = PageRequest.of(roomFeatureTemplateDetailSearchDto.getPage(), roomFeatureTemplateDetailSearchDto.getSize());
    final QRoomFeatureTemplateDetail qRoomFeatureTemplateDetail = QRoomFeatureTemplateDetail.roomFeatureTemplateDetail;
    var query = new JPAQuery<RoomFeatureTemplateDetail>(entityManager)
            .from(qRoomFeatureTemplateDetail)
            .where(predicate)
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .orderBy(qRoomFeatureTemplateDetail.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }
}
