package com.example.ecommerce_api.service;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.example.ecommerce_api.controller.request_dto.CategorySearchDto;
import com.example.ecommerce_api.controller.request_dto.SubCategorySearchDto;
import com.example.ecommerce_api.entity.QCategory;
import com.example.ecommerce_api.entity.QSubCategory;
import com.example.ecommerce_api.entity.SubCategory;
import com.example.ecommerce_api.repository.SubCategoryRepository;
import com.example.ecommerce_api.service.common.EntityValidationService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.example.ecommerce_api.service.predicate.SubCategoryPredicate.makePredicate;

@Service
@AllArgsConstructor
public class SubCategoryService {

  private final EntityManager entityManager;
  private final EntityValidationService entityValidationService;
  private final SubCategoryRepository subCategoryRepository;

  @org.springframework.transaction.annotation.Transactional
  public SubCategory saveSubCategory(SubCategory subCategory) {
    return this.subCategoryRepository.save(subCategory);
  }

  @org.springframework.transaction.annotation.Transactional
  public SubCategory updateSubCategory(SubCategory subCategory) {
    var subCategoryDb = entityValidationService.validateSubCategory(subCategory.getId());
    subCategoryDb.setName(subCategory.getName());
    subCategoryDb = subCategoryRepository.save(subCategoryDb);
    return subCategoryDb;
  }

  @org.springframework.transaction.annotation.Transactional
  public String deleteSubCategory(SubCategory subCategory) {
    var subCategoryDb = entityValidationService.validateSubCategory(subCategory.getId());
    subCategoryDb.setEnabled(Boolean.FALSE);
    subCategoryRepository.save(subCategoryDb);
    return "Sub category deleted successfully";
  }

  public Page<SubCategory> searchSubCategory(SubCategorySearchDto subCategorySearchDto) {
    Predicate predicate = makePredicate(subCategorySearchDto);
    Pageable pageable = PageRequest.of(subCategorySearchDto.getPage(), subCategorySearchDto.getSize());
    final QSubCategory qSubCategory = QSubCategory.subCategory;
    var query = new JPAQuery<SubCategory>(entityManager)
        .from(qSubCategory)
        .where(predicate)
        .limit(pageable.getPageSize())
        .offset(pageable.getOffset())
        .orderBy(qSubCategory.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }

  public Page<SubCategory> searchWithAdultChildNumber(SubCategorySearchDto subCategorySearchDto) {
    Predicate predicate = makePredicate(subCategorySearchDto);
    Pageable pageable = PageRequest.of(subCategorySearchDto.getPage(), subCategorySearchDto.getSize());
    final QCategory qCategory = QCategory.category;
    var query = new JPAQuery<SubCategory>(entityManager)
        .from(qCategory)
        .where(predicate)
        .limit(pageable.getPageSize())
        .offset(pageable.getOffset())
        .orderBy(qCategory.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }

}
