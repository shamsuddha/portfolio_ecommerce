package com.example.ecommerce_api.repository;

import com.example.ecommerce_api.entity.Bed;
import com.example.ecommerce_api.entity.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BedRepository extends JpaRepository<Bed, String>, QuerydslPredicateExecutor<Bed> {

}
