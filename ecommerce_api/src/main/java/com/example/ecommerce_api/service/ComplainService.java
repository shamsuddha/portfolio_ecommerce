package com.example.ecommerce_api.service;


import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import io.hms.api.controller.request_dto.ComplainSearchDto;
import io.hms.api.entity.Complain;
import io.hms.api.entity.QComplain;
import io.hms.api.repository.ComplainRepository;
import io.hms.api.service.common.EntityValidationService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static io.hms.api.service.predicate.ComplainPredicate.makePredicate;

@Service
@AllArgsConstructor
public class ComplainService {

  private final EntityManager entityManager;
  private final EntityValidationService entityValidationService;
  private final ComplainRepository complainRepository;

  @org.springframework.transaction.annotation.Transactional
  public Complain saveComplain(Complain complain) {
    return this.complainRepository.save(complain);
  }

  @org.springframework.transaction.annotation.Transactional
  public Complain updateComplain(Complain complain) {
    var complainDb = entityValidationService.validateComplain(complain.getId());
    complainDb.setTitle(complain.getTitle());
    complainDb.setDescription(complain.getDescription());
    complainDb.setStatus(complain.getStatus());
    complainDb = complainRepository.save(complainDb);
    return complainDb;
  }

  @org.springframework.transaction.annotation.Transactional
  public String deleteComplain(Complain complain) {
    var complainDb = entityValidationService.validateComplain(complain.getId());
    complainDb.setEnabled(Boolean.FALSE);
    complainRepository.save(complainDb);
    return "Complain deleted successfully";
  }

  public Page<Complain> searchComplain(ComplainSearchDto complainSearchDto) {
    Predicate predicate = makePredicate(complainSearchDto);
    Pageable pageable = PageRequest.of(complainSearchDto.getPage(), complainSearchDto.getSize());
    final QComplain qComplain = QComplain.complain;
    var query = new JPAQuery<Complain>(entityManager)
            .from(qComplain)
            .where(predicate)
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .orderBy(qComplain.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }
}
