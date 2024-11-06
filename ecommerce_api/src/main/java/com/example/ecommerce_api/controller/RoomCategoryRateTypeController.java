package com.example.ecommerce_api.controller;

import io.hms.api.controller.request_dto.RoomCategoryRateTypeSearchDto;
import io.hms.api.entity.RoomCategoryRateType;
import io.hms.api.service.RoomCategoryRateTypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room-category-rate-type")
@AllArgsConstructor
public class RoomCategoryRateTypeController {

  private final RoomCategoryRateTypeService roomCategoryRateTypeService;

  @PostMapping("/save")
  public ResponseEntity<RoomCategoryRateType> save(@RequestBody RoomCategoryRateType roomCategoryRateType) {
    return new ResponseEntity(roomCategoryRateTypeService.saveRoomCategoryRateType(roomCategoryRateType), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/update")
  public ResponseEntity<RoomCategoryRateType> update(@RequestBody RoomCategoryRateType roomCategoryRateType) {
    return new ResponseEntity(roomCategoryRateTypeService.updateRoomCategoryRateType(roomCategoryRateType), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/delete")
  public ResponseEntity<String> delete(@RequestBody RoomCategoryRateType roomCategoryRateType) {
    return new ResponseEntity(roomCategoryRateTypeService.deleteRoomCategoryRateType(roomCategoryRateType), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search")
  public ResponseEntity<Page<RoomCategoryRateType>> search(@RequestBody RoomCategoryRateTypeSearchDto roomCategoryRateTypeSearchDto) {
    return new ResponseEntity(roomCategoryRateTypeService.searchRoomCategoryRateType(roomCategoryRateTypeSearchDto), HttpStatusCode.valueOf(200));
  }
}
