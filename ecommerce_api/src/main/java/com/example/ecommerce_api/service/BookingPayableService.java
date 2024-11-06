package com.example.ecommerce_api.service;


import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.example.ecommerce_api.controller.request_dto.BookingPayableSearchDto;
import com.example.ecommerce_api.entity.BookingPayable;
import com.example.ecommerce_api.entity.QBookingPayable;
import com.example.ecommerce_api.repository.BookingPayableRepository;
import com.example.ecommerce_api.service.common.EntityValidationService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.example.ecommerce_api.service.predicate.BookingPayablePredicate.makePredicate;

@Service
@AllArgsConstructor
public class BookingPayableService {

  private final EntityManager entityManager;
  private final EntityValidationService entityValidationService;
  private final BookingPayableRepository bookingPayableRepository;


  @org.springframework.transaction.annotation.Transactional
  public BookingPayable saveBookingPayable(BookingPayable bookingPayable) {
    return this.bookingPayableRepository.save(bookingPayable);
  }

  @org.springframework.transaction.annotation.Transactional
  public BookingPayable updateBookingPayable(BookingPayable bookingPayable) {
    var bookingPayableDb = entityValidationService.validateBookingPayable(bookingPayable.getId());
    bookingPayableDb.setBooking(bookingPayable.getBooking());
    bookingPayableDb.setAmount(bookingPayable.getAmount());
    bookingPayableDb.setHead(bookingPayable.getHead());
    bookingPayableDb.setDiscountPercentage(bookingPayable.getDiscountPercentage());
    bookingPayableDb.setDiscountAmount(bookingPayable.getDiscountAmount());
    bookingPayableDb.setFinalAmount(bookingPayable.getFinalAmount());
    bookingPayableDb.setOverriddenFinalAmount(bookingPayable.getOverriddenFinalAmount());
    bookingPayableDb = bookingPayableRepository.save(bookingPayableDb);
    return bookingPayableDb;
  }

  @org.springframework.transaction.annotation.Transactional
  public String deleteBookingPayable(BookingPayable bookingPayable) {
    var bookingPayableDb = entityValidationService.validateBookingPayable(bookingPayable.getId());
    bookingPayableDb.setEnabled(Boolean.FALSE);
    bookingPayableRepository.save(bookingPayableDb);
    return "BookingPayable deleted successfully";
  }

  public Page<BookingPayable> searchBookingPayable(BookingPayableSearchDto bookingPayableSearchDto) {
    Predicate predicate = makePredicate(bookingPayableSearchDto);
    Pageable pageable = PageRequest.of(bookingPayableSearchDto.getPage(), bookingPayableSearchDto.getSize());
    final QBookingPayable qBookingPayable = QBookingPayable.bookingPayable;
    var query = new JPAQuery<BookingPayable>(entityManager)
            .from(qBookingPayable)
            .where(predicate)
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .orderBy(qBookingPayable.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }
}
