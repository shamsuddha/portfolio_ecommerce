package com.example.ecommerce_api.service;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import io.hms.api.controller.request_dto.BedSearchDto;
import io.hms.api.entity.Bed;
import io.hms.api.entity.QBed;
import io.hms.api.repository.BedRepository;
import io.hms.api.service.common.EntityValidationService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static io.hms.api.service.predicate.BedPredicate.makePredicate;

@Service
@AllArgsConstructor
public class BedService {

    private final EntityManager entityManager;
    private final EntityValidationService entityValidationService;
    private final BedRepository bedRepository;

    @org.springframework.transaction.annotation.Transactional
    public Bed saveBed(Bed bed) {
      return this.bedRepository.save(bed);
    }

    @org.springframework.transaction.annotation.Transactional
    public Bed updateBed(Bed bed) {
      var bedDb = entityValidationService.validateBed(bed.getId());
      bedDb.setName(bed.getName());
      bedDb = bedRepository.save(bedDb);
      return bedDb;
    }

    @org.springframework.transaction.annotation.Transactional
    public String deleteBed(Bed bed) {
      var bedDb = entityValidationService.validateBed(bed.getId());
      bedDb.setEnabled(Boolean.FALSE);
      bedRepository.save(bedDb);
      return "Bed deleted successfully";
    }

    public Page<Bed> searchBed(BedSearchDto bedSearchDto) {
      Predicate predicate = makePredicate(bedSearchDto);
      Pageable pageable = PageRequest.of(bedSearchDto.getPage(), bedSearchDto.getSize());
      final QBed qBed = QBed.bed;
      var query = new JPAQuery<Bed>(entityManager)
              .from(qBed)
              .where(predicate)
              .limit(pageable.getPageSize())
              .offset(pageable.getOffset())
              .orderBy(qBed.createdDate.desc());
      return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }

    public Page<Bed> searchWithAdultChildNumber(BedSearchDto bedSearchDto) {
      Predicate predicate = makePredicate(bedSearchDto);
      Pageable pageable = PageRequest.of(bedSearchDto.getPage(), bedSearchDto.getSize());
      final QBed qBed = QBed.bed;
      var query = new JPAQuery<Bed>(entityManager)
              .from(qBed)
              .where(predicate)
              .limit(pageable.getPageSize())
              .offset(pageable.getOffset())
              .orderBy(qBed.createdDate.desc());
      return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }

  }
