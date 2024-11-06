package com.example.ecommerce_api.controller;

import com.example.ecommerce_api.controller.request_dto.BookingDetailSearchDto;
import com.example.ecommerce_api.entity.BookingDetail;
import com.example.ecommerce_api.service.BookingDetailService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking-detail")
@AllArgsConstructor
public class BookingDetailController {

  private final BookingDetailService bookingDetailService;

  @PostMapping("/save")
  public ResponseEntity<BookingDetail> save(@RequestBody BookingDetail bookingDetail) {
    return new ResponseEntity(bookingDetailService.saveBookingDetail(bookingDetail), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/update")
  public ResponseEntity<BookingDetail> update(@RequestBody BookingDetail bookingDetail) {
    return new ResponseEntity(bookingDetailService.updateBookingDetail(bookingDetail), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/delete")
  public ResponseEntity<String> delete(@RequestBody BookingDetail bookingDetail) {
    return new ResponseEntity(bookingDetailService.deleteBookingDetail(bookingDetail), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search")
  public ResponseEntity<Page<BookingDetail>> search(@RequestBody BookingDetailSearchDto bookingDetailSearchDto) {
    return new ResponseEntity(bookingDetailService.searchBookingDetail(bookingDetailSearchDto), HttpStatusCode.valueOf(200));
  }


}
