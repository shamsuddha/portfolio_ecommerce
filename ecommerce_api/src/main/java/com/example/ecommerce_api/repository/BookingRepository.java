package com.example.ecommerce_api.repository;

import com.example.ecommerce_api.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> , QuerydslPredicateExecutor<Booking> {


}
