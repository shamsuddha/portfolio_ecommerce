package com.example.ecommerce_api.controller;

import com.example.ecommerce_api.controller.request_dto.BookingCartSearchDto;
import com.example.ecommerce_api.entity.BookingCart;
import com.example.ecommerce_api.service.BookingCartService;
import com.example.ecommerce_api.service.BookingCartService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking-cart")
@AllArgsConstructor
public class BookingCartController {

  private final BookingCartService bookingCartService;

  @PostMapping("/save")
  public ResponseEntity<BookingCart> save(@RequestBody BookingCart bookingCart) {
    return new ResponseEntity(bookingCartService.saveBookingCart(bookingCart), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/update")
  public ResponseEntity<BookingCart> update(@RequestBody BookingCart bookingCart) {
    return new ResponseEntity(bookingCartService.updateBookingCart(bookingCart), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/delete")
  public ResponseEntity<String> delete(@RequestBody BookingCart bookingCart) {
    return new ResponseEntity(bookingCartService.deleteBookingCart(bookingCart), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search")
  public ResponseEntity<Page<BookingCart>> search(@RequestBody BookingCartSearchDto bookingCartSearchDto) {
    return new ResponseEntity(bookingCartService.searchBookingCart(bookingCartSearchDto), HttpStatusCode.valueOf(200));
  }


}
