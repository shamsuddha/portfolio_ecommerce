package com.example.ecommerce_api.controller;

import io.hms.api.controller.request_dto.BookingSearchDto;
import io.hms.api.entity.Booking;
import io.hms.api.service.BookingService;
import io.hms.api.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@AllArgsConstructor
public class BookingController {

  private final BookingService bookingService;

  @PostMapping("/save")
  public ResponseEntity<Booking> save(@RequestBody Booking booking) {
    return new ResponseEntity(bookingService.saveBooking(booking), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/update")
  public ResponseEntity<Booking> update(@RequestBody Booking booking) {
    return new ResponseEntity(bookingService.updateBooking(booking), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/delete")
  public ResponseEntity<String> delete(@RequestBody Booking booking) {
    return new ResponseEntity(bookingService.deleteBooking(booking), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search")
  public ResponseEntity<Page<Booking>> search(@RequestBody BookingSearchDto bookingSearchDto) {
    return new ResponseEntity(bookingService.searchBooking(bookingSearchDto), HttpStatusCode.valueOf(200));
  }


}
