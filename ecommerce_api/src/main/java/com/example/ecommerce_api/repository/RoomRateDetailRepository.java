package com.example.ecommerce_api.repository;

import io.hms.api.entity.RoomRateDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRateDetailRepository extends JpaRepository<RoomRateDetail, String> , QuerydslPredicateExecutor<RoomRateDetail> {

}
