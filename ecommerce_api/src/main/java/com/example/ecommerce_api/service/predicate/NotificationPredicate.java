package com.example.ecommerce_api.service.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.example.ecommerce_api.controller.request_dto.NotificationSearchDto;
import com.example.ecommerce_api.entity.QNotification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Objects;

public class NotificationPredicate {

  private static final QNotification qNotification = QNotification.notification;

  public static Predicate makePredicate(NotificationSearchDto notificationSearchDto) {

    BooleanBuilder builder = new BooleanBuilder();

    if (!CollectionUtils.isEmpty(notificationSearchDto.getIdList())) {
      builder.and(qNotification.id.in(notificationSearchDto.getIdList()));
    }
    if (!CollectionUtils.isEmpty(notificationSearchDto.getIdNotEqualList())) {
      builder.and(qNotification.id.notIn(notificationSearchDto.getIdNotEqualList()));
    }

    /*if (StringUtils.isNotBlank(notificationSearchDto.getNotificationCategoryId())) {
      builder.and(qNotification.notificationCategoryId.equalsIgnoreCase(notificationSearchDto.getNotificationCategoryId()));
    }*/
    if (StringUtils.isNotBlank(notificationSearchDto.getMultiSearchProp())) {
      BooleanBuilder booleanBuilder = new BooleanBuilder();
      booleanBuilder.or(qNotification.title.containsIgnoreCase(notificationSearchDto.getMultiSearchProp()))
        .or(qNotification.body.containsIgnoreCase(notificationSearchDto.getMultiSearchProp()));
      builder.and(booleanBuilder);
    }
    return builder;
  }
}