package com.example.ecommerce_api.service;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import io.hms.api.controller.request_dto.FloorSearchDto;
import io.hms.api.entity.QFloor;
import io.hms.api.entity.Floor;
import io.hms.api.repository.FloorRepository;
import io.hms.api.service.common.EntityValidationService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static io.hms.api.service.predicate.FloorPredicate.makePredicate;

@Service
@AllArgsConstructor
public class FloorService
{
  private final EntityManager entityManager;
  private final EntityValidationService entityValidationService;
  private final FloorRepository floorRepository;

  @org.springframework.transaction.annotation.Transactional
  public Floor saveFloor(Floor floor) {
    return this.floorRepository.save(floor);
  }

  @org.springframework.transaction.annotation.Transactional
  public Floor updateFloor(Floor floor) {
    var floorDb = entityValidationService.validateFloor(floor.getId());
    floorDb.setName(floor.getName());
    floorDb = floorRepository.save(floorDb);
    return floorDb;
  }

  @org.springframework.transaction.annotation.Transactional
  public String deleteFloor(Floor floor) {
    var floorDb = entityValidationService.validateFloor(floor.getId());
    floorDb.setEnabled(Boolean.FALSE);
    floorRepository.save(floorDb);
    return "Floor deleted successfully";
  }

  public Page<Floor> searchFloor(FloorSearchDto floorSearchDto) {
    Predicate predicate = makePredicate(floorSearchDto);
    Pageable pageable = PageRequest.of(floorSearchDto.getPage(), floorSearchDto.getSize());
    final QFloor qFloor = QFloor.floor;
    var query = new JPAQuery<Floor>(entityManager)
            .from(qFloor)
            .where(predicate)
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .orderBy(qFloor.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }

  public Page<Floor> searchWithAdultChildNumber(FloorSearchDto floorSearchDto) {
    Predicate predicate = makePredicate(floorSearchDto);
    Pageable pageable = PageRequest.of(floorSearchDto.getPage(), floorSearchDto.getSize());
    final QFloor qFloor = QFloor.floor;
    var query = new JPAQuery<Floor>(entityManager)
            .from(qFloor)
            .where(predicate)
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .orderBy(qFloor.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }

}
