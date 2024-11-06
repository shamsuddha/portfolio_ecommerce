package com.example.ecommerce_api.repository;

import io.hms.api.entity.Floor;
import io.hms.api.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorRepository extends JpaRepository<Floor, String> , QuerydslPredicateExecutor<Floor> {

}
