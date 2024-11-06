package com.example.ecommerce_api.service;


import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import io.hms.api.controller.request_dto.CountrySearchDto;
import io.hms.api.entity.Country;
import io.hms.api.entity.QCountry;
import io.hms.api.repository.CountryRepository;
import io.hms.api.service.common.EntityValidationService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static io.hms.api.service.predicate.CountryPredicate.makePredicate;

@Service
@AllArgsConstructor
public class CountryService {

  private final EntityManager entityManager;
  private final EntityValidationService entityValidationService;
  private final CountryRepository countryRepository;

  @org.springframework.transaction.annotation.Transactional
  public Country saveCountry(Country country) {
    return this.countryRepository.save(country);
  }

  @org.springframework.transaction.annotation.Transactional
  public Country updateCountry(Country country) {
    var countryDb = entityValidationService.validateCountry(country.getId());
    countryDb.setName(country.getName());
    countryDb.setShortName(country.getShortName());
    countryDb.setCountryCode(country.getCountryCode());
    countryDb.setRegion(country.getRegion());
    countryDb = countryRepository.save(countryDb);
    return countryDb;
  }

  @org.springframework.transaction.annotation.Transactional
  public String deleteCountry(Country country) {
    var countryDb = entityValidationService.validateCountry(country.getId());
    countryDb.setEnabled(Boolean.FALSE);
    countryRepository.save(countryDb);
    return "Country deleted successfully";
  }

  public Page<Country> searchCountry(CountrySearchDto countrySearchDto) {
    Predicate predicate = makePredicate(countrySearchDto);
    Pageable pageable = PageRequest.of(countrySearchDto.getPage(), countrySearchDto.getSize());
    final QCountry qCountry = QCountry.country;
    var query = new JPAQuery<Country>(entityManager)
            .from(qCountry)
            .where(predicate)
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .orderBy(qCountry.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }

  public Page<Country> searchCountryByName(CountrySearchDto countrySearchDto) {
    Predicate predicate = makePredicate(countrySearchDto);
    Pageable pageable = PageRequest.of(countrySearchDto.getPage(), countrySearchDto.getSize());
    final QCountry qCountry = QCountry.country;
    var query = new JPAQuery<Country>(entityManager)
            .from(qCountry)
            .where(predicate)
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .orderBy(qCountry.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }


}
