package com.example.ecommerce_api.service;


import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.example.ecommerce_api.controller.request_dto.RoomFeatureTemplateSearchDto;
import com.example.ecommerce_api.entity.RoomFeatureTemplate;
import com.example.ecommerce_api.entity.QRoomFeatureTemplate;
import com.example.ecommerce_api.repository.RoomFeatureTemplateRepository;
import com.example.ecommerce_api.service.common.EntityValidationService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.example.ecommerce_api.service.predicate.RoomFeatureTemplatePredicate.makePredicate;

@Service
@AllArgsConstructor
public class RoomFeatureTemplateService {

  private final EntityManager entityManager;
  private final EntityValidationService entityValidationService;
  private final RoomFeatureTemplateRepository roomFeatureTemplateRepository;

  @org.springframework.transaction.annotation.Transactional
  public RoomFeatureTemplate saveRoomFeatureTemplate(RoomFeatureTemplate roomFeatureTemplate) {
    return this.roomFeatureTemplateRepository.save(roomFeatureTemplate);
  }

  @org.springframework.transaction.annotation.Transactional
  public RoomFeatureTemplate updateRoomFeatureTemplate(RoomFeatureTemplate roomFeatureTemplate) {
    var roomFeatureTemplateDb = entityValidationService.validateRoomFeatureTemplate(roomFeatureTemplate.getId());
    roomFeatureTemplateDb.setName(roomFeatureTemplate.getName());
    roomFeatureTemplateDb = roomFeatureTemplateRepository.save(roomFeatureTemplateDb);
    return roomFeatureTemplateDb;
  }

  @org.springframework.transaction.annotation.Transactional
  public String deleteRoomFeatureTemplate(RoomFeatureTemplate roomFeatureTemplate) {
    var roomFeatureTemplateDb = entityValidationService.validateRoomFeatureTemplate(roomFeatureTemplate.getId());
    roomFeatureTemplateDb.setEnabled(Boolean.FALSE);
    roomFeatureTemplateRepository.save(roomFeatureTemplateDb);
    return "RoomFeatureTemplate deleted successfully";
  }

  public Page<RoomFeatureTemplate> searchRoomFeatureTemplate(RoomFeatureTemplateSearchDto roomFeatureTemplateSearchDto) {
    Predicate predicate = makePredicate(roomFeatureTemplateSearchDto);
    Pageable pageable = PageRequest.of(roomFeatureTemplateSearchDto.getPage(), roomFeatureTemplateSearchDto.getSize());
    final QRoomFeatureTemplate qRoomFeatureTemplate = QRoomFeatureTemplate.roomFeatureTemplate;
    var query = new JPAQuery<RoomFeatureTemplate>(entityManager)
            .from(qRoomFeatureTemplate)
            .where(predicate)
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .orderBy(qRoomFeatureTemplate.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }
}
