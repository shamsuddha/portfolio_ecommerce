package com.example.ecommerce_api.repository;

import com.example.ecommerce_api.entity.BookingReceivable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingReceivableRepository extends JpaRepository<BookingReceivable, String> , QuerydslPredicateExecutor<BookingReceivable> {


}