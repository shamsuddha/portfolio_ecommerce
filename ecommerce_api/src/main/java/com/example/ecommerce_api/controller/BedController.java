package com.example.ecommerce_api.controller;

import io.hms.api.controller.request_dto.BedSearchDto;
import io.hms.api.entity.Bed;
import io.hms.api.service.BedService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bed")
@AllArgsConstructor
public class BedController {


  private final BedService roomService;

  @PostMapping("/save")
  public ResponseEntity<Bed> save(@RequestBody Bed room) {
    return new ResponseEntity(roomService.saveBed(room), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/update")
  public ResponseEntity<Bed> update(@RequestBody Bed room) {
    return new ResponseEntity(roomService.updateBed(room), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/delete")
  public ResponseEntity<String> deleteBed(@RequestBody Bed room) {
    return new ResponseEntity(roomService.deleteBed(room), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search")
  public ResponseEntity<Page<Bed>> search(@RequestBody BedSearchDto roomSearchDto) {
    return new ResponseEntity(roomService.searchBed(roomSearchDto), HttpStatusCode.valueOf(200));
  }
}
