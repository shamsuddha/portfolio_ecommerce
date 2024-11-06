package com.example.ecommerce_api.controller;

import io.hms.api.controller.request_dto.FloorSearchDto;
import io.hms.api.entity.Floor;
import io.hms.api.service.FloorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/floor")
@AllArgsConstructor
public class FloorController {


  private final FloorService roomService;

  @PostMapping("/save")
  public ResponseEntity<Floor> save(@RequestBody Floor room) {
    return new ResponseEntity(roomService.saveFloor(room), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/update")
  public ResponseEntity<Floor> update(@RequestBody Floor room) {
    return new ResponseEntity(roomService.updateFloor(room), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/delete")
  public ResponseEntity<String> deleteFloor(@RequestBody Floor room) {
    return new ResponseEntity(roomService.deleteFloor(room), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search")
  public ResponseEntity<Page<Floor>> search(@RequestBody FloorSearchDto roomSearchDto) {
    return new ResponseEntity(roomService.searchFloor(roomSearchDto), HttpStatusCode.valueOf(200));
  }

}
