package com.example.ecommerce_api.controller;

import io.hms.api.controller.request_dto.ComplainSearchDto;
import io.hms.api.entity.Complain;
import io.hms.api.service.ComplainService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/complain")
@AllArgsConstructor
public class ComplainController {

  private final ComplainService complainService;

  @PostMapping("/save")
  public ResponseEntity<Complain> save(@RequestBody Complain complain) {
    return new ResponseEntity(complainService.saveComplain(complain), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/update")
  public ResponseEntity<Complain> update(@RequestBody Complain complain) {
    return new ResponseEntity(complainService.updateComplain(complain), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/delete")
  public ResponseEntity<String> delete(@RequestBody Complain complain) {
    return new ResponseEntity(complainService.deleteComplain(complain), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search")
  public ResponseEntity<Page<Complain>> search(@RequestBody ComplainSearchDto complainSearchDto) {
    return new ResponseEntity(complainService.searchComplain(complainSearchDto), HttpStatusCode.valueOf(200));
  }
}
