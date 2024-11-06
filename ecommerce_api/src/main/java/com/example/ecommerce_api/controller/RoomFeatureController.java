package com.example.ecommerce_api.controller;

import io.hms.api.controller.request_dto.RoomFeatureSearchDto;
import io.hms.api.entity.RoomFeature;
import io.hms.api.service.RoomFeatureService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room-feature")
@AllArgsConstructor
public class RoomFeatureController {

  private final RoomFeatureService roomFeatureService;

  @PostMapping("/save")
  public ResponseEntity<RoomFeature> save(@RequestBody RoomFeature roomFeature) {
    return new ResponseEntity(roomFeatureService.saveRoomFeature(roomFeature), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/update")
  public ResponseEntity<RoomFeature> update(@RequestBody RoomFeature roomFeature) {
    return new ResponseEntity(roomFeatureService.updateRoomFeature(roomFeature), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/delete")
  public ResponseEntity<String> delete(@RequestBody RoomFeature roomFeature) {
    return new ResponseEntity(roomFeatureService.deleteRoomFeature(roomFeature), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search")
  public ResponseEntity<Page<RoomFeature>> search(@RequestBody RoomFeatureSearchDto roomFeatureSearchDto) {
    return new ResponseEntity(roomFeatureService.searchRoomFeature(roomFeatureSearchDto), HttpStatusCode.valueOf(200));
  }
}
