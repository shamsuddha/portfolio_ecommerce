package com.example.ecommerce_api.service.validation;

import com.example.ecommerce_api.controller.request_dto.LoginInfoDto;
import com.example.ecommerce_api.exception.UserInformException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthValidationService {

  public Boolean validateLoginInfoDto(LoginInfoDto loginInfoDto) {
    if (StringUtils.isBlank(loginInfoDto.getUsername())) {
      throw new UserInformException("Username can not be null");
    }
    if (StringUtils.isBlank(loginInfoDto.getPassword())) {
      throw new UserInformException("Password can not be null");
    }
    return true;
  }

}
