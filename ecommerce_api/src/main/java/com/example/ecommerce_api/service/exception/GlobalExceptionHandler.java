package com.example.ecommerce_api.exception;

import io.hms.api.util.api_util.ApiResponse;
import io.hms.api.util.api_util.ApiResponseType;
import io.hms.api.util.api_util.UserInformErrorType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<ApiResponse> handleUnauthorizedException(UnauthorizedException uae) {
    System.out.println("From handleUnauthorizedException : " + uae.getMessage());
    uae.printStackTrace();
    return new ResponseEntity<>(
      new ApiResponse<Object>(null, ApiResponseType.unauthorized_error,
        UserInformErrorType.default_error, Boolean.FALSE, "Unauthorized error happened"),
      new HttpHeaders(), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(UserInformException.class)
  public ResponseEntity<ApiResponse> handleUserInformException(UserInformException userInformException) {
    System.out.println("From UserInformException : " + userInformException.getMessage());
    userInformException.printStackTrace();
    return new ResponseEntity<>(
      new ApiResponse<Object>(null, ApiResponseType.user_inform_error,
        UserInformErrorType.default_error, Boolean.FALSE, userInformException.getMessage()),
      new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException runtimeException) {
    System.out.println("From RuntimeException : " + runtimeException.getMessage());
    runtimeException.printStackTrace();
    return new ResponseEntity<>(
      new ApiResponse<Object>(null, ApiResponseType.user_inform_error,
        UserInformErrorType.default_error, Boolean.FALSE, "Something went wrong"),
      new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleGlobalException(Exception ex) {
    ex.printStackTrace();
    return new ResponseEntity<>(
      new ApiResponse<Object>(null, ApiResponseType.user_inform_error,
        UserInformErrorType.default_error, Boolean.FALSE, "Something went wrong"),
      new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
