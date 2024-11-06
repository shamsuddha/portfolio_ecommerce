package com.example.ecommerce_api.service;

import com.querydsl.jpa.impl.JPAQuery;
import io.hms.api.controller.request_dto.LoginInfoDto;
import io.hms.api.entity.*;
import io.hms.api.exception.UserInformException;
import io.hms.api.service.validation.AuthValidationService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static io.hms.api.entity.QAdmin.admin;

@Service
@AllArgsConstructor
public class AuthService {

  private final EntityManager entityManager;
  private final AuthValidationService authValidationService;
  private final JwtTokenService tokenService;

  public String customerLogin(LoginInfoDto loginInfoDto) {
    this.authValidationService.validateLoginInfoDto(loginInfoDto);
    final QGuestInfo qGuestInfo = QGuestInfo.guestInfo;
    GuestInfo guestInfo = new JPAQuery<GuestInfo>(entityManager)
      .from(qGuestInfo)
      .where(
        qGuestInfo.password.eq(loginInfoDto.getPassword()).and(
          qGuestInfo.email.eq(loginInfoDto.getUsername())
            .or(qGuestInfo.mobile.eq(loginInfoDto.getUsername()))
        )
      ).fetchFirst();
    if (Objects.nonNull(guestInfo)) {
      List<String> roleList = List.of("customer");
      if (Objects.nonNull(guestInfo.getMobile())) {
        guestInfo.setUsername(guestInfo.getMobile());
      } else if (Objects.nonNull(guestInfo.getEmail())) {
        guestInfo.setUsername(guestInfo.getEmail());
      } else {
        throw new UserInformException("Mobile number and email both can not be null");
      }
      return tokenService.generateCustomerToken(guestInfo, roleList);
    } else {
      return null;
    }
  }

  public String adminLogin(LoginInfoDto loginInfoDto) {
    this.authValidationService.validateLoginInfoDto(loginInfoDto);
    final QAdmin qAdmin = admin;
    Admin admin = new JPAQuery<Admin>(entityManager)
      .from(qAdmin)
      .where(
        qAdmin.password.eq(loginInfoDto.getPassword()).and(
          qAdmin.username.eq(loginInfoDto.getUsername())
        )
      ).fetchFirst();
    if (Objects.nonNull(admin)) {
      List<String> roleList = List.of();
      return tokenService.generateAdminToken(admin, roleList);
    } else {
      return null;
    }
  }

}
