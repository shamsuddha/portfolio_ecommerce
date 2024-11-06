package com.example.ecommerce_api.controller;

import io.hms.api.controller.request_dto.NotificationSearchDto;
import io.hms.api.entity.Notification;
import io.hms.api.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
@AllArgsConstructor
public class NotificationController {

  private final NotificationService notificationService;

  @PostMapping("/save")
  public ResponseEntity<Notification> save(@RequestBody Notification notification) {
    return new ResponseEntity(notificationService.saveNotification(notification), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/update")
  public ResponseEntity<Notification> update(@RequestBody Notification notification) {
    return new ResponseEntity(notificationService.updateNotification(notification), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/delete")
  public ResponseEntity<String> deleteNotification(@RequestBody Notification notification) {
    return new ResponseEntity(notificationService.deleteNotification(notification), HttpStatusCode.valueOf(200));
  }

  @PostMapping("/search")
  public ResponseEntity<Page<Notification>> search(@RequestBody NotificationSearchDto notificationSearchDto) {
    return new ResponseEntity(notificationService.searchNotification(notificationSearchDto), HttpStatusCode.valueOf(200));
  }

}
