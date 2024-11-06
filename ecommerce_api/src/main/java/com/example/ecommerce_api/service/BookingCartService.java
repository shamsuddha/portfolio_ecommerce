package com.example.ecommerce_api.service;


import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import io.hms.api.controller.request_dto.BookingCartSearchDto;
import io.hms.api.entity.BookingCart;
import io.hms.api.entity.GuestInfo;
import io.hms.api.entity.QBookingCart;
import io.hms.api.repository.BookingCartRepository;
import io.hms.api.service.common.EntityValidationService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static io.hms.api.service.predicate.BookingCartPredicate.makePredicate;

@Service
@AllArgsConstructor
public class BookingCartService {

  private final EntityManager entityManager;
  private final EntityValidationService entityValidationService;
  private final BookingCartRepository bookingCartRepository;


  @org.springframework.transaction.annotation.Transactional
  public BookingCart saveBookingCart(BookingCart bookingCart) {
   // bookingCart.setGuestInfo(new GuestInfo(bookingCart.getGuestInfoId()));
    return this.bookingCartRepository.save(bookingCart);
  }

  @org.springframework.transaction.annotation.Transactional
  public BookingCart updateBookingCart(BookingCart bookingCart) {
    var bookingCartDb = entityValidationService.validateBookingCart(bookingCart.getId());
    bookingCartDb.setGuestInfo(bookingCart.getGuestInfo());
    bookingCartDb = bookingCartRepository.save(bookingCartDb);
    return bookingCartDb;
  }

  @org.springframework.transaction.annotation.Transactional
  public String deleteBookingCart(BookingCart bookingCart) {
    var bookingCartDb = entityValidationService.validateBookingCart(bookingCart.getId());
    bookingCartDb.setEnabled(Boolean.FALSE);
    bookingCartRepository.save(bookingCartDb);
    return "BookingCart deleted successfully";
  }

  public Page<BookingCart> searchBookingCart(BookingCartSearchDto bookingCartSearchDto) {
    Predicate predicate = makePredicate(bookingCartSearchDto);
    Pageable pageable = PageRequest.of(bookingCartSearchDto.getPage(), bookingCartSearchDto.getSize());
    final QBookingCart qBookingCart = QBookingCart.bookingCart;
    var query = new JPAQuery<BookingCart>(entityManager)
            .from(qBookingCart)
            .where(predicate)
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .orderBy(qBookingCart.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }
}
