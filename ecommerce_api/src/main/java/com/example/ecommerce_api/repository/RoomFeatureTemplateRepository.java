package com.example.ecommerce_api.repository;

import io.hms.api.entity.RoomFeatureTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomFeatureTemplateRepository extends JpaRepository<RoomFeatureTemplate, String> , QuerydslPredicateExecutor<RoomFeatureTemplate> {


}
