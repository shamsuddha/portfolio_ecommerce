package com.example.ecommerce_api.controller;

import io.hms.api.controller.request_dto.BookingPayableSearchDto;
import io.hms.api.entity.BookingPayable;
import io.hms.api.service.BookingPayableService;
import io.hms.api.service.BookingPayableService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking-payable")
@AllArgsConstructor
public class BookingPayableController {

  private final BookingPayableService bookingPayableService;

  @PostMapping("/save")
  public ResponseEntity<BookingPayable> save(@RequestBody BookingPayable bookingPayable) {
    return new ResponseEntity(bookingPayableService.saveBookingPayable(bookingPayable), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/update")
  public ResponseEntity<BookingPayable> update(@RequestBody BookingPayable bookingPayable) {
    return new ResponseEntity(bookingPayableService.updateBookingPayable(bookingPayable), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/delete")
  public ResponseEntity<String> delete(@RequestBody BookingPayable bookingPayable) {
    return new ResponseEntity(bookingPayableService.deleteBookingPayable(bookingPayable), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search")
  public ResponseEntity<Page<BookingPayable>> search(@RequestBody BookingPayableSearchDto bookingPayableSearchDto) {
    return new ResponseEntity(bookingPayableService.searchBookingPayable(bookingPayableSearchDto), HttpStatusCode.valueOf(200));
  }


}
