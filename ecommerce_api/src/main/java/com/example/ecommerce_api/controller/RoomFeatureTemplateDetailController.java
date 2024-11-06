package com.example.ecommerce_api.controller;

import com.example.ecommerce_api.controller.request_dto.RoomFeatureTemplateDetailSearchDto;
import com.example.ecommerce_api.entity.RoomFeatureTemplateDetail;
import com.example.ecommerce_api.service.RoomFeatureTemplateDetailService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room-feature-template-detail")
@AllArgsConstructor
public class RoomFeatureTemplateDetailController {

  private final RoomFeatureTemplateDetailService roomFeatureTemplateDetailService;

  @PostMapping("/save")
  public ResponseEntity<RoomFeatureTemplateDetail> save(@RequestBody RoomFeatureTemplateDetail roomFeatureTemplateDetail) {
    return new ResponseEntity(roomFeatureTemplateDetailService.saveRoomFeatureTemplateDetail(roomFeatureTemplateDetail), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/update")
  public ResponseEntity<RoomFeatureTemplateDetail> update(@RequestBody RoomFeatureTemplateDetail roomFeatureTemplateDetail) {
    return new ResponseEntity(roomFeatureTemplateDetailService.updateRoomFeatureTemplateDetail(roomFeatureTemplateDetail), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/delete")
  public ResponseEntity<String> delete(@RequestBody RoomFeatureTemplateDetail roomFeatureTemplateDetail) {
    return new ResponseEntity(roomFeatureTemplateDetailService.deleteRoomFeatureTemplateDetail(roomFeatureTemplateDetail), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search")
  public ResponseEntity<Page<RoomFeatureTemplateDetail>> search(@RequestBody RoomFeatureTemplateDetailSearchDto roomFeatureTemplateDetailSearchDto) {
    return new ResponseEntity(roomFeatureTemplateDetailService.searchRoomFeatureTemplateDetail(roomFeatureTemplateDetailSearchDto), HttpStatusCode.valueOf(200));
  }

}
