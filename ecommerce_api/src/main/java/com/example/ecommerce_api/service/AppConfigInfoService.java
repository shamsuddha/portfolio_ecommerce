package com.example.ecommerce_api.service;


import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppConfigInfoService {

  private final EntityManager entityManager;
  private final EntityValidationService entityValidationService;
  private final AppConfigInfoRepository appConfigInfoRepository;

  @org.springframework.transaction.annotation.Transactional
  public AppConfigInfo saveAppConfigInfo(AppConfigInfo appConfigInfo) {
    return this.appConfigInfoRepository.save(appConfigInfo);
  }

  @org.springframework.transaction.annotation.Transactional
  public AppConfigInfo updateAppConfigInfo(AppConfigInfo appConfigInfo) {
    var appConfigInfoDb = entityValidationService.validateAppConfigInfo(appConfigInfo.getId());
    appConfigInfoDb = appConfigInfoRepository.save(appConfigInfoDb);
    return appConfigInfoDb;
  }

  @org.springframework.transaction.annotation.Transactional
  public String deleteAppConfigInfo(AppConfigInfo appConfigInfo) {
    var appConfigInfoDb = entityValidationService.validateAppConfigInfo(appConfigInfo.getId());
    appConfigInfoDb.setEnabled(Boolean.FALSE);
    appConfigInfoRepository.save(appConfigInfoDb);
    return "AppConfigInfo deleted successfully";
  }

  public Page<AppConfigInfo> searchAppConfigInfo(AppConfigInfoSearchDto appConfigInfoSearchDto) {
    Predicate predicate = makePredicate(appConfigInfoSearchDto);
    Pageable pageable = PageRequest.of(appConfigInfoSearchDto.getPage(), appConfigInfoSearchDto.getSize());
    final QAppConfigInfo qAppConfigInfo = QAppConfigInfo.appConfigInfo;
    var query = new JPAQuery<AppConfigInfo>(entityManager)
            .from(qAppConfigInfo)
            .where(predicate)
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .orderBy(qAppConfigInfo.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }
}
