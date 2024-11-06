package com.example.ecommerce_api.controller;

import com.example.ecommerce_api.controller.request_dto.BookingReceivableSearchDto;
import com.example.ecommerce_api.entity.BookingReceivable;
import com.example.ecommerce_api.service.BookingReceivableService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking-receivable")
@AllArgsConstructor
public class BookingReceivableController {

  private final BookingReceivableService bookingReceivableService;

  @PostMapping("/save")
  public ResponseEntity<BookingReceivable> save(@RequestBody BookingReceivable bookingReceivable) {
    return new ResponseEntity(bookingReceivableService.saveBookingReceivable(bookingReceivable), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/update")
  public ResponseEntity<BookingReceivable> update(@RequestBody BookingReceivable bookingReceivable) {
    return new ResponseEntity(bookingReceivableService.updateBookingReceivable(bookingReceivable), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/delete")
  public ResponseEntity<String> delete(@RequestBody BookingReceivable bookingReceivable) {
    return new ResponseEntity(bookingReceivableService.deleteBookingReceivable(bookingReceivable), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search")
  public ResponseEntity<Page<BookingReceivable>> search(@RequestBody BookingReceivableSearchDto bookingReceivableSearchDto) {
    return new ResponseEntity(bookingReceivableService.searchBookingReceivable(bookingReceivableSearchDto), HttpStatusCode.valueOf(200));
  }
}
