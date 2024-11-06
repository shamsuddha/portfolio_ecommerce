package com.example.ecommerce_api.service;


import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.example.ecommerce_api.controller.request_dto.RoomRateDetailSearchDto;
import com.example.ecommerce_api.entity.QRoom;
import com.example.ecommerce_api.entity.RoomRateDetail;
import com.example.ecommerce_api.entity.QRoomRateDetail;
import com.example.ecommerce_api.repository.RoomRateDetailRepository;
import com.example.ecommerce_api.service.common.EntityValidationService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.example.ecommerce_api.service.predicate.RoomRateDetailPredicate.makePredicate;

@Service
@AllArgsConstructor
public class RoomRateDetailService {

  private final EntityManager entityManager;
  private final EntityValidationService entityValidationService;
  private final RoomRateDetailRepository roomRateDetailRepository;

  @org.springframework.transaction.annotation.Transactional
  public RoomRateDetail saveRoomRateDetail(RoomRateDetail roomRateDetail) {
    return this.roomRateDetailRepository.save(roomRateDetail);
  }

  @org.springframework.transaction.annotation.Transactional
  public RoomRateDetail updateRoomRateDetail(RoomRateDetail roomRateDetail) {
    var roomRateDetailDb = entityValidationService.validateRoomRateDetail(roomRateDetail.getId());
    roomRateDetailDb.setRate(roomRateDetail.getRate());
    roomRateDetailDb.setOverriddenRate(roomRateDetail.getOverriddenRate());
    roomRateDetailDb.setHour(roomRateDetail.getHour());
    roomRateDetailDb = roomRateDetailRepository.save(roomRateDetailDb);
    return roomRateDetailDb;
  }

  @org.springframework.transaction.annotation.Transactional
  public String deleteRoomRateDetail(RoomRateDetail roomRateDetail) {
    var roomRateDetailDb = entityValidationService.validateRoomRateDetail(roomRateDetail.getId());
    roomRateDetailDb.setEnabled(Boolean.FALSE);
    roomRateDetailRepository.save(roomRateDetailDb);
    return "RoomRateDetail deleted successfully";
  }

  public Page<RoomRateDetail> searchRoomRateDetail(RoomRateDetailSearchDto roomRateDetailSearchDto) {
    Predicate predicate = makePredicate(roomRateDetailSearchDto);
    Pageable pageable = PageRequest.of(roomRateDetailSearchDto.getPage(), roomRateDetailSearchDto.getSize());
    final QRoomRateDetail qRoomRateDetail = QRoomRateDetail.roomRateDetail;
    var query = new JPAQuery<RoomRateDetail>(entityManager)
      .from(qRoomRateDetail)
      .where(predicate)
      .limit(pageable.getPageSize())
      .offset(pageable.getOffset())
      .orderBy(qRoomRateDetail.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }

  public Page<RoomRateDetail> searchWithRoom(RoomRateDetailSearchDto roomRateDetailSearchDto) {
    Predicate predicate = makePredicate(roomRateDetailSearchDto);
    Pageable pageable = PageRequest.of(roomRateDetailSearchDto.getPage(), roomRateDetailSearchDto.getSize());
    final QRoomRateDetail qRoomRateDetail = QRoomRateDetail.roomRateDetail;
    final QRoom qRoom = QRoom.room;
    var query = new JPAQuery<RoomRateDetail>(entityManager)
      .from(qRoomRateDetail)
      .leftJoin(qRoomRateDetail.room, qRoom).fetchJoin()
      .where(predicate)
      .limit(pageable.getPageSize())
      .offset(pageable.getOffset())
      .orderBy(qRoomRateDetail.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }

}
