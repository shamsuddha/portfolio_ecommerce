package com.example.ecommerce_api.service;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.example.ecommerce_api.controller.request_dto.CategorySearchDto;
import com.example.ecommerce_api.entity.Category;
import com.example.ecommerce_api.entity.QCategory;
import com.example.ecommerce_api.repository.CategoryRepository;
import com.example.ecommerce_api.service.common.EntityValidationService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.example.ecommerce_api.service.predicate.CategoryPredicate.makePredicate;

@Service
@AllArgsConstructor
public class CategoryService {

    private final EntityManager entityManager;
    private final EntityValidationService entityValidationService;
    private final CategoryRepository categoryRepository;

    @org.springframework.transaction.annotation.Transactional
    public Category saveCategory(Category category) {
      return this.categoryRepository.save(category);
    }

    @org.springframework.transaction.annotation.Transactional
    public Category updateCategory(Category category) {
      var categoryDb = entityValidationService.validateCategory(category.getId());
      categoryDb.setName(category.getName());
      categoryDb = categoryRepository.save(categoryDb);
      return categoryDb;
    }

    @org.springframework.transaction.annotation.Transactional
    public String deleteCategory(Category category) {
      var categoryDb = entityValidationService.validateCategory(category.getId());
      categoryDb.setEnabled(Boolean.FALSE);
      categoryRepository.save(categoryDb);
      return "Category deleted successfully";
    }

    public Page<Category> searchCategory(CategorySearchDto categorySearchDto) {
      Predicate predicate = makePredicate(categorySearchDto);
      Pageable pageable = PageRequest.of(categorySearchDto.getPage(), categorySearchDto.getSize());
      final QCategory qCategory = QCategory.category;
      var query = new JPAQuery<Category>(entityManager)
              .from(qCategory)
              .where(predicate)
              .limit(pageable.getPageSize())
              .offset(pageable.getOffset())
              .orderBy(qCategory.createdDate.desc());
      return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }

    public Page<Category> searchWithAdultChildNumber(CategorySearchDto categorySearchDto) {
      Predicate predicate = makePredicate(categorySearchDto);
      Pageable pageable = PageRequest.of(categorySearchDto.getPage(), categorySearchDto.getSize());
      final QCategory qCategory = QCategory.category;
      var query = new JPAQuery<Category>(entityManager)
              .from(qCategory)
              .where(predicate)
              .limit(pageable.getPageSize())
              .offset(pageable.getOffset())
              .orderBy(qCategory.createdDate.desc());
      return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }

  }
