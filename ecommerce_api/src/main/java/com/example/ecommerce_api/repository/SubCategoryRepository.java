package com.example.ecommerce_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.example.ecommerce_api.entity.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, String>, QuerydslPredicateExecutor<SubCategory> {

}