package com.example.ecommerce_api.controller;

import io.hms.api.controller.request_dto.RoomCategorySearchDto;
import io.hms.api.entity.RoomCategory;
import io.hms.api.service.RoomCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room-category")
@AllArgsConstructor
public class RoomCategoryController {

  private final RoomCategoryService roomCategoryService;

  @PostMapping("/save")
  public ResponseEntity<RoomCategory> save(@RequestBody RoomCategory roomCategory) {
    return new ResponseEntity(roomCategoryService.saveRoomCategory(roomCategory), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/update")
  public ResponseEntity<RoomCategory> update(@RequestBody RoomCategory roomCategory) {
    return new ResponseEntity(roomCategoryService.updateRoomCategory(roomCategory), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/delete")
  public ResponseEntity<String> delete(@RequestBody RoomCategory roomCategory) {
    return new ResponseEntity(roomCategoryService.deleteRoomCategory(roomCategory), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search")
  public ResponseEntity<Page<RoomCategory>> search(@RequestBody RoomCategorySearchDto roomCategorySearchDto) {
    return new ResponseEntity(roomCategoryService.searchRoomCategory(roomCategorySearchDto), HttpStatusCode.valueOf(200));
  }

}
