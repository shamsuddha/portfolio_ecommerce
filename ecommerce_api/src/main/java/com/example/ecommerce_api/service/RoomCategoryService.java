package com.example.ecommerce_api.service;


import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.example.ecommerce_api.controller.request_dto.RoomCategorySearchDto;
import com.example.ecommerce_api.entity.QRoomCategory;
import com.example.ecommerce_api.entity.RoomCategory;
import com.example.ecommerce_api.repository.RoomCategoryRepository;
import com.example.ecommerce_api.service.common.EntityValidationService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.example.ecommerce_api.service.predicate.RoomCategoryPredicate.makePredicate;

@Service
@AllArgsConstructor
public class RoomCategoryService {

  private final EntityManager entityManager;
  private final EntityValidationService entityValidationService;
  private final RoomCategoryRepository roomCategoryRepository;

  @org.springframework.transaction.annotation.Transactional
  public RoomCategory saveRoomCategory(RoomCategory roomCategory) {
    return this.roomCategoryRepository.save(roomCategory);
  }

  @org.springframework.transaction.annotation.Transactional
  public RoomCategory updateRoomCategory(RoomCategory roomCategory) {
    var roomCategoryDb = entityValidationService.validateRoomCategory(roomCategory.getId());
    roomCategoryDb.setCode(roomCategory.getCode());
    roomCategoryDb.setName(roomCategory.getName());
    roomCategoryDb = roomCategoryRepository.save(roomCategoryDb);
    return roomCategoryDb;
  }

  @org.springframework.transaction.annotation.Transactional
  public String deleteRoomCategory(RoomCategory roomCategory) {
    var roomCategoryDb = entityValidationService.validateRoomCategory(roomCategory.getId());
    roomCategoryDb.setEnabled(Boolean.FALSE);
    roomCategoryRepository.save(roomCategoryDb);
    return "RoomCategory deleted successfully";
  }

  public Page<RoomCategory> searchRoomCategory(RoomCategorySearchDto roomSearchDto) {
    Predicate predicate = makePredicate(roomSearchDto);
    Pageable pageable = PageRequest.of(roomSearchDto.getPage(), roomSearchDto.getSize());
    final QRoomCategory qRoomCategory = QRoomCategory.roomCategory;
    var query = new JPAQuery<RoomCategory>(entityManager)
            .from(qRoomCategory)
            .where(predicate)
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .orderBy(qRoomCategory.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }
}
