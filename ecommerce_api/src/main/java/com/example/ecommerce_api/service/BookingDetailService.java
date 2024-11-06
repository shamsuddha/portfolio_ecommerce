package com.example.ecommerce_api.service;


import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.example.ecommerce_api.controller.request_dto.BookingDetailSearchDto;
import com.example.ecommerce_api.entity.BookingDetail;
import com.example.ecommerce_api.repository.BookingDetailRepository;
import com.example.ecommerce_api.service.common.EntityValidationService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.example.ecommerce_api.service.predicate.BookingDetailPredicate.makePredicate;

@Service
@AllArgsConstructor
public class BookingDetailService {

  private final EntityManager entityManager;
  private final EntityValidationService entityValidationService;
  private final BookingDetailRepository bookingDetailRepository;


  @org.springframework.transaction.annotation.Transactional
  public BookingDetail saveBookingDetail(BookingDetail bookingDetail) {
    return this.bookingDetailRepository.save(bookingDetail);
  }

  @org.springframework.transaction.annotation.Transactional
  public BookingDetail updateBookingDetail(BookingDetail bookingDetail) {
    var bookingDetailDb = entityValidationService.validateBookingDetail(bookingDetail.getId());
    bookingDetailDb.setBooking(bookingDetail.getBooking());
    bookingDetailDb.setNumberOfDay(bookingDetail.getNumberOfDay());
    bookingDetailDb.setTimeSlotStart(bookingDetail.getTimeSlotStart());
    bookingDetailDb.setTimeSlotEnd(bookingDetail.getTimeSlotEnd());
    bookingDetailDb.setRoom(bookingDetail.getRoom());
    bookingDetailDb.setRateAmount(bookingDetail.getRateAmount());
    bookingDetailDb.setOverriddenRateAmount(bookingDetail.getOverriddenRateAmount());
    bookingDetailDb.setNumberOfAdultGuest(bookingDetail.getNumberOfAdultGuest());
    bookingDetailDb.setNumberOfChildGuest(bookingDetail.getNumberOfChildGuest());
    bookingDetailDb.setCoupon(bookingDetail.getCoupon());
    bookingDetailDb.setDiscountPercentage(bookingDetail.getDiscountPercentage());
    bookingDetailDb.setDiscountAmount(bookingDetail.getDiscountAmount());
    bookingDetailDb.setFinalAmount(bookingDetail.getFinalAmount());
    bookingDetailDb.setOverriddenFinalAmount(bookingDetail.getOverriddenFinalAmount());
    bookingDetailDb.setRoomStatusKey(bookingDetail.getRoomStatusKey());
    bookingDetailDb.setRoomStatusValue(bookingDetail.getRoomStatusValue());
    bookingDetailDb = bookingDetailRepository.save(bookingDetailDb);
    return bookingDetailDb;
  }

  @org.springframework.transaction.annotation.Transactional
  public String deleteBookingDetail(BookingDetail bookingDetail) {
    var bookingDetailDb = entityValidationService.validateBookingDetail(bookingDetail.getId());
    bookingDetailDb.setEnabled(Boolean.FALSE);
    bookingDetailRepository.save(bookingDetailDb);
    return "BookingDetail deleted successfully";
  }

  public Page<BookingDetail> searchBookingDetail(BookingDetailSearchDto bookingDetailSearchDto) {
    Predicate predicate = makePredicate(bookingDetailSearchDto);
    Pageable pageable = PageRequest.of(bookingDetailSearchDto.getPage(), bookingDetailSearchDto.getSize());
    final QBookingDetail qBookingDetail = QBookingDetail.bookingDetail;
    var query = new JPAQuery<BookingDetail>(entityManager)
            .from(qBookingDetail)
            .where(predicate)
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .orderBy(qBookingDetail.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }
}
