package io.hms.api.service.validation;

import io.hms.api.controller.request_dto.LoginInfoDto;
import io.hms.api.exception.UserInformException;
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
