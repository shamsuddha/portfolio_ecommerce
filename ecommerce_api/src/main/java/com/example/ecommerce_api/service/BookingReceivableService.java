package com.example.ecommerce_api.service;


import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import io.hms.api.controller.request_dto.BookingReceivableSearchDto;
import io.hms.api.entity.BookingReceivable;
import io.hms.api.entity.QBookingReceivable;
import io.hms.api.repository.BookingReceivableRepository;
import io.hms.api.service.common.EntityValidationService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static io.hms.api.service.predicate.BookingReceivablePredicate.makePredicate;

@Service
@AllArgsConstructor
public class BookingReceivableService {

  private final EntityManager entityManager;
  private final EntityValidationService entityValidationService;
  private final BookingReceivableRepository bookingReceivableRepository;



  @org.springframework.transaction.annotation.Transactional
  public BookingReceivable saveBookingReceivable(BookingReceivable bookingReceivable) {
    return this.bookingReceivableRepository.save(bookingReceivable);
  }

  @org.springframework.transaction.annotation.Transactional
  public BookingReceivable updateBookingReceivable(BookingReceivable bookingReceivable) {
    var bookingReceivableDb = entityValidationService.validateBookingReceivable(bookingReceivable.getId());
    bookingReceivableDb.setBooking(bookingReceivable.getBooking());
    bookingReceivableDb.setAmount(bookingReceivable.getAmount());
    bookingReceivableDb.setPaymentTypeKey(bookingReceivable.getPaymentTypeKey());
    bookingReceivableDb.setPaymentTypeValue(bookingReceivable.getPaymentTypeValue());
    bookingReceivableDb = bookingReceivableRepository.save(bookingReceivableDb);
    return bookingReceivableDb;
  }

  @org.springframework.transaction.annotation.Transactional
  public String deleteBookingReceivable(BookingReceivable bookingReceivable) {
    var bookingReceivableDb = entityValidationService.validateBookingReceivable(bookingReceivable.getId());
    bookingReceivableDb.setEnabled(Boolean.FALSE);
    bookingReceivableRepository.save(bookingReceivableDb);
    return "BookingReceivable deleted successfully";
  }

  public Page<BookingReceivable> searchBookingReceivable(BookingReceivableSearchDto bookingReceivableSearchDto) {
    Predicate predicate = makePredicate(bookingReceivableSearchDto);
    Pageable pageable = PageRequest.of(bookingReceivableSearchDto.getPage(), bookingReceivableSearchDto.getSize());
    final QBookingReceivable qBookingReceivable = QBookingReceivable.bookingReceivable;
    var query = new JPAQuery<BookingReceivable>(entityManager)
            .from(qBookingReceivable)
            .where(predicate)
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .orderBy(qBookingReceivable.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }
}
