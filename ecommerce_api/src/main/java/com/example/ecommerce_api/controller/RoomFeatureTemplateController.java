package com.example.ecommerce_api.controller;

import io.hms.api.controller.request_dto.RoomFeatureTemplateSearchDto;
import io.hms.api.entity.RoomFeatureTemplate;
import io.hms.api.service.RoomFeatureTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room-feature-template")
@AllArgsConstructor
public class RoomFeatureTemplateController {

  private final RoomFeatureTemplateService roomFeatureTemplateService;

  @PostMapping("/save")
  public ResponseEntity<RoomFeatureTemplate> save(@RequestBody RoomFeatureTemplate roomFeatureTemplate) {
    return new ResponseEntity(roomFeatureTemplateService.saveRoomFeatureTemplate(roomFeatureTemplate), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/update")
  public ResponseEntity<RoomFeatureTemplate> update(@RequestBody RoomFeatureTemplate roomFeatureTemplate) {
    return new ResponseEntity(roomFeatureTemplateService.updateRoomFeatureTemplate(roomFeatureTemplate), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/delete")
  public ResponseEntity<String> delete(@RequestBody RoomFeatureTemplate roomFeatureTemplate) {
    return new ResponseEntity(roomFeatureTemplateService.deleteRoomFeatureTemplate(roomFeatureTemplate), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search")
  public ResponseEntity<Page<RoomFeatureTemplate>> search(@RequestBody RoomFeatureTemplateSearchDto roomFeatureTemplateSearchDto) {
    return new ResponseEntity(roomFeatureTemplateService.searchRoomFeatureTemplate(roomFeatureTemplateSearchDto), HttpStatusCode.valueOf(200));
  }

}
