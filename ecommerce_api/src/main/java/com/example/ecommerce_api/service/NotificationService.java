package com.example.ecommerce_api.service;


import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import io.hms.api.controller.request_dto.NotificationSearchDto;
import io.hms.api.entity.QNotification;
import io.hms.api.entity.Notification;
import io.hms.api.repository.NotificationRepository;
import io.hms.api.service.common.EntityValidationService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static io.hms.api.service.predicate.NotificationPredicate.makePredicate;

@Service
@AllArgsConstructor
public class NotificationService {

  private final EntityManager entityManager;
  private final EntityValidationService entityValidationService;
  private final NotificationRepository notificationRepository;

  @org.springframework.transaction.annotation.Transactional
  public Notification saveNotification(Notification notification) {
    return this.notificationRepository.save(notification);
  }

  @org.springframework.transaction.annotation.Transactional
  public Notification updateNotification(Notification notification) {
    var notificationDb = entityValidationService.validateNotification(notification.getId());
    notificationDb.setBody(notification.getBody());
    notificationDb.setTitle(notification.getTitle());
    notificationDb.setNotificationForId(notification.getNotificationForId());
    notificationDb.setNotificationForName(notification.getNotificationForName());
    notificationDb = notificationRepository.save(notificationDb);
    return notificationDb;
  }

  @org.springframework.transaction.annotation.Transactional
  public String deleteNotification(Notification notification) {
    var notificationDb = entityValidationService.validateNotification(notification.getId());
    notificationDb.setEnabled(Boolean.FALSE);
    notificationRepository.save(notificationDb);
    return "Notification deleted successfully";
  }

  public Page<Notification> searchNotification(NotificationSearchDto notificationSearchDto) {
    Predicate predicate = makePredicate(notificationSearchDto);
    Pageable pageable = PageRequest.of(notificationSearchDto.getPage(), notificationSearchDto.getSize());
    final QNotification qNotification = QNotification.notification;
    var query = new JPAQuery<Notification>(entityManager)
      .from(qNotification)
      .where(predicate)
      .limit(pageable.getPageSize())
      .offset(pageable.getOffset())
      .orderBy(qNotification.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }

  public Page<Notification> searchWithAdultChildNumber(NotificationSearchDto notificationSearchDto) {
    Predicate predicate = makePredicate(notificationSearchDto);
    Pageable pageable = PageRequest.of(notificationSearchDto.getPage(), notificationSearchDto.getSize());
    final QNotification qNotification = QNotification.notification;
    var query = new JPAQuery<Notification>(entityManager)
      .from(qNotification)
      .where(predicate)
      .limit(pageable.getPageSize())
      .offset(pageable.getOffset())
      .orderBy(qNotification.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }
}
