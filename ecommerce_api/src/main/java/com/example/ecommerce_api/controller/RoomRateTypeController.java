package com.example.ecommerce_api.controller;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ecommerce_api.controller.request_dto.RoomRateTypeSearchDto;
import com.example.ecommerce_api.entity.RoomRateType;
import com.example.ecommerce_api.service.RoomRateTypeService;

@RestController
@RequestMapping("/room-rate-type")
@AllArgsConstructor
public class RoomRateTypeController {

  private final RoomRateTypeService roomRateTypeService;

  @PostMapping("/save")
  public ResponseEntity<RoomRateType> save(@RequestBody RoomRateType roomRateType) {
    return new ResponseEntity(roomRateTypeService.saveRoomRateType(roomRateType), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/update")
  public ResponseEntity<RoomRateType> update(@RequestBody RoomRateType roomRateType) {
    return new ResponseEntity(roomRateTypeService.updateRoomRateType(roomRateType), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/delete")
  public ResponseEntity<String> delete(@RequestBody RoomRateType roomRateType) {
    return new ResponseEntity(roomRateTypeService.deleteRoomRateType(roomRateType), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search")
  public ResponseEntity<Page<RoomRateType>> search(@RequestBody RoomRateTypeSearchDto roomRateTypeSearchDto) {
    return new ResponseEntity(roomRateTypeService.searchRoomRateType(roomRateTypeSearchDto), HttpStatusCode.valueOf(200));
  }
}
