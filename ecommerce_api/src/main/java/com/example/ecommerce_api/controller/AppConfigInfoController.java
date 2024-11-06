package com.example.ecommerce_api.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ecommerce_api.controller.request_dto.AppConfigInfoSearchDto;
import com.example.ecommerce_api.entity.AppConfigInfo;
import com.example.ecommerce_api.service.AppConfigInfoService;

@RestController
@RequestMapping("/app-config-info")
@AllArgsConstructor
public class AppConfigInfoController {

  private final AppConfigInfoService appConfigInfoService;

  @PostMapping("/save")
  public ResponseEntity<AppConfigInfo> save(@RequestBody AppConfigInfo appConfigInfo) {
    return new ResponseEntity(appConfigInfoService.saveAppConfigInfo(appConfigInfo), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/update")
  public ResponseEntity<AppConfigInfo> update(@RequestBody AppConfigInfo appConfigInfo) {
    return new ResponseEntity(appConfigInfoService.updateAppConfigInfo(appConfigInfo), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/delete")
  public ResponseEntity<String> delete(@RequestBody AppConfigInfo appConfigInfo) {
    return new ResponseEntity(appConfigInfoService.deleteAppConfigInfo(appConfigInfo), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search")
  public ResponseEntity<Page<AppConfigInfo>> search(@RequestBody AppConfigInfoSearchDto appConfigInfoSearchDto) {
    return new ResponseEntity(appConfigInfoService.searchAppConfigInfo(appConfigInfoSearchDto), HttpStatusCode.valueOf(200));
  }
}
