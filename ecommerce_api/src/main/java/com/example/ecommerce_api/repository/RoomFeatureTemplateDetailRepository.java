package com.example.ecommerce_api.repository;

import io.hms.api.entity.RoomFeatureTemplateDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomFeatureTemplateDetailRepository extends JpaRepository<RoomFeatureTemplateDetail, String> , QuerydslPredicateExecutor<RoomFeatureTemplateDetail> {


}
