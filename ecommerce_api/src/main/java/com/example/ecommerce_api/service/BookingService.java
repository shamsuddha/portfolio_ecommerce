package com.example.ecommerce_api.service;


import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.example.ecommerce_api.controller.request_dto.BookingSearchDto;
import com.example.ecommerce_api.entity.Booking;
import com.example.ecommerce_api.entity.QBooking;
import com.example.ecommerce_api.repository.BookingRepository;
import com.example.ecommerce_api.service.common.EntityValidationService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.example.ecommerce_api.service.predicate.BookingPredicate.makePredicate;

@Service
@AllArgsConstructor
public class BookingService {

  private final EntityManager entityManager;
  private final EntityValidationService entityValidationService;
  private final BookingRepository bookingRepository;


  @org.springframework.transaction.annotation.Transactional
  public Booking saveBooking(Booking booking) {
    return this.bookingRepository.save(booking);
  }

  @org.springframework.transaction.annotation.Transactional
  public Booking updateBooking(Booking booking) {
    var bookingDb = entityValidationService.validateBooking(booking.getId());
    bookingDb.setGuestInfo(booking.getGuestInfo());
    bookingDb.setRoom(booking.getRoom());
    bookingDb.setCoupon(booking.getCoupon());
    bookingDb.setFinalDiscountPercentage(booking.getFinalDiscountPercentage());
    bookingDb.setFinalDiscountAmount(booking.getFinalDiscountAmount());
    bookingDb.setFinalAmount(booking.getFinalAmount());
    bookingDb.setOverriddenFinalAmount(booking.getOverriddenFinalAmount());
    bookingDb.setCheckIn(booking.getCheckIn());
    bookingDb.setCheckOut(booking.getCheckOut());
    bookingDb.setBookingDetailList(booking.getBookingDetailList());
    bookingDb.setBookingPayableList(booking.getBookingPayableList());
    bookingDb.setBookingReceivableList(booking.getBookingReceivableList());
    bookingDb = bookingRepository.save(bookingDb);
    return bookingDb;
  }

  @org.springframework.transaction.annotation.Transactional
  public String deleteBooking(Booking booking) {
    var bookingDb = entityValidationService.validateBooking(booking.getId());
    bookingDb.setEnabled(Boolean.FALSE);
    bookingRepository.save(bookingDb);
    return "Booking deleted successfully";
  }

  public Page<Booking> searchBooking(BookingSearchDto bookingSearchDto) {
    Predicate predicate = makePredicate(bookingSearchDto);
    Pageable pageable = PageRequest.of(bookingSearchDto.getPage(), bookingSearchDto.getSize());
    final QBooking qBooking = QBooking.booking;
    var query = new JPAQuery<Booking>(entityManager)
            .from(qBooking)
            .where(predicate)
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .orderBy(qBooking.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }
}
