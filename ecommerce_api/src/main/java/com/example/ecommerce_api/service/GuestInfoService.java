package com.example.ecommerce_api.service;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import io.hms.api.controller.request_dto.GuestInfoLoginDto;
import io.hms.api.controller.request_dto.GuestInfoSearchDto;
import io.hms.api.entity.GuestInfo;
import io.hms.api.entity.QGuestInfo;
import io.hms.api.repository.GuestInfoRepository;
import io.hms.api.service.common.EntityValidationService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static io.hms.api.service.predicate.GuestInfoPredicate.makePredicate;

@Service
@AllArgsConstructor
public class GuestInfoService {

  private final EntityManager entityManager;
  private final EntityValidationService entityValidationService;
  private final GuestInfoRepository guestInfoRepository;

  @org.springframework.transaction.annotation.Transactional
  public GuestInfo saveGuestInfo(GuestInfo guestInfo) {
    return this.guestInfoRepository.save(guestInfo);
  }

  @org.springframework.transaction.annotation.Transactional
  public GuestInfo updateGuestInfo(GuestInfo guestInfo) {
    var guestInfoDb = entityValidationService.validateGuestInfo(guestInfo.getId());
    guestInfoDb.setFirstName(guestInfo.getFirstName());
    guestInfoDb.setMiddleName(guestInfo.getMiddleName());
    guestInfoDb.setLastName(guestInfo.getLastName());
    guestInfoDb.setEmail(guestInfo.getEmail());
    guestInfoDb.setCountryCode(guestInfo.getCountryCode());
    guestInfoDb.setMobile(guestInfo.getMobile());
    guestInfoDb.setGender(guestInfo.getGender());
    guestInfoDb.setBirthDate(guestInfo.getBirthDate());
    guestInfoDb.setAddress(guestInfo.getAddress());
    guestInfoDb = guestInfoRepository.save(guestInfoDb);
    return guestInfoDb;
  }

  @org.springframework.transaction.annotation.Transactional
  public String deleteGuestInfo(GuestInfo guestInfo) {
    var guestInfoDb = entityValidationService.validateGuestInfo(guestInfo.getId());
    guestInfoDb.setEnabled(Boolean.FALSE);
    guestInfoRepository.save(guestInfoDb);
    return "GuestInfo deleted successfully";
  }

  public Page<GuestInfo> searchGuestInfo(GuestInfoSearchDto guestInfoSearchDto) {
    Predicate predicate = makePredicate(guestInfoSearchDto);
    Pageable pageable = PageRequest.of(guestInfoSearchDto.getPage(), guestInfoSearchDto.getSize());
    final QGuestInfo qGuestInfo = QGuestInfo.guestInfo;
    var query = new JPAQuery<GuestInfo>(entityManager)
            .from(qGuestInfo)
            .where(predicate)
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .orderBy(qGuestInfo.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }

  public Object login(GuestInfoLoginDto guestInfoLoginDto) {
    return null;
  }

}
