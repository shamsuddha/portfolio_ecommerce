package com.example.ecommerce_api.controller;

import io.hms.api.controller.request_dto.CountrySearchDto;
import io.hms.api.controller.request_dto.RoomSearchDto;
import io.hms.api.entity.Country;
import io.hms.api.entity.Room;
import io.hms.api.service.RoomService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/room")
@AllArgsConstructor
public class RoomController {

  private final RoomService roomService;

  @PostMapping("/save")
  public ResponseEntity<Room> save(@RequestBody Room room) {
    return new ResponseEntity(roomService.saveRoom(room), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/update")
  public ResponseEntity<Room> update(@RequestBody Room room) {
    return new ResponseEntity(roomService.updateRoom(room), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/delete")
  public ResponseEntity<String> deleteRoom(@RequestBody Room room) {
    return new ResponseEntity(roomService.deleteRoom(room), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search")
  public ResponseEntity<Page<Room>> search(@RequestBody RoomSearchDto roomSearchDto) {
    return new ResponseEntity(roomService.searchRoom(roomSearchDto), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search-adult-child-number")
  public ResponseEntity<Page<Room>> searchWithAdultChildNumber(@RequestBody RoomSearchDto roomSearchDto) {
    return new ResponseEntity(roomService.searchWithAdultChildNumber(roomSearchDto), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search-with-rate-detail")
  public ResponseEntity<Page<Room>> searchWithRateDetail(@RequestBody RoomSearchDto roomSearchDto) {
    return new ResponseEntity(roomService.searchWithRateDetail(roomSearchDto), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search-by-name")
  public ResponseEntity<Page<Country>> searchByName(@RequestBody RoomSearchDto roomSearchDto) {
    return new ResponseEntity(roomService.searchRoomByName(roomSearchDto), HttpStatusCode.valueOf(200));
  }

}
