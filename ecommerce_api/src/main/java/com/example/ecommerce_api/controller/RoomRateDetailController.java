package com.example.ecommerce_api.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ecommerce_api.controller.request_dto.RoomRateDetailSearchDto;
import com.example.ecommerce_api.entity.RoomRateDetail;
import com.example.ecommerce_api.service.RoomRateDetailService;

@RestController
@RequestMapping("/room-rate-detail")
@AllArgsConstructor
public class RoomRateDetailController {

  private final RoomRateDetailService roomRateDetailService;

  @PostMapping("/save")
  public ResponseEntity<RoomRateDetail> save(@RequestBody RoomRateDetail roomRateDetail) {
    return new ResponseEntity(roomRateDetailService.saveRoomRateDetail(roomRateDetail), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/update")
  public ResponseEntity<RoomRateDetail> update(@RequestBody RoomRateDetail roomRateDetail) {
    return new ResponseEntity(roomRateDetailService.updateRoomRateDetail(roomRateDetail), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/delete")
  public ResponseEntity<String> delete(@RequestBody RoomRateDetail roomRateDetail) {
    return new ResponseEntity(roomRateDetailService.deleteRoomRateDetail(roomRateDetail), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search")
  public ResponseEntity<Page<RoomRateDetail>> search(@RequestBody RoomRateDetailSearchDto roomRateDetailSearchDto) {
    return new ResponseEntity(roomRateDetailService.searchRoomRateDetail(roomRateDetailSearchDto), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search-with-room")
  public ResponseEntity<Page<RoomRateDetail>> searchWithRoom(@RequestBody RoomRateDetailSearchDto roomRateDetailSearchDto) {
    return new ResponseEntity(roomRateDetailService.searchWithRoom(roomRateDetailSearchDto), HttpStatusCode.valueOf(200));
  }

}
