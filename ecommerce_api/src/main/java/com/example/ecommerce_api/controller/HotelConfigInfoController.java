package com.example.ecommerce_api.controller;

import io.hms.api.controller.request_dto.HotelConfigInfoSearchDto;
import io.hms.api.entity.HotelConfigInfo;
import io.hms.api.service.HotelConfigInfoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel-config-info")
@AllArgsConstructor
public class HotelConfigInfoController {

  private final HotelConfigInfoService hotelConfigInfoService;

  @PostMapping("/save")
  public ResponseEntity<HotelConfigInfo> save(@RequestBody HotelConfigInfo hotelInfoConfig) {
    return new ResponseEntity(hotelConfigInfoService.saveHotelConfigInfo(hotelInfoConfig), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/update")
  public ResponseEntity<HotelConfigInfo> update(@RequestBody HotelConfigInfo hotelInfoConfig) {
    return new ResponseEntity(hotelConfigInfoService.updateHotelConfigInfo(hotelInfoConfig), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/delete")
  public ResponseEntity<String> delete(@RequestBody HotelConfigInfo hotelInfoConfig) {
    return new ResponseEntity(hotelConfigInfoService.deleteHotelConfigInfo(hotelInfoConfig), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search")
  public ResponseEntity<Page<HotelConfigInfo>> search(@RequestBody HotelConfigInfoSearchDto hotelInfoConfigSearchDto) {
    return new ResponseEntity(hotelConfigInfoService.searchHotelConfigInfo(hotelInfoConfigSearchDto), HttpStatusCode.valueOf(200));
  }
}
