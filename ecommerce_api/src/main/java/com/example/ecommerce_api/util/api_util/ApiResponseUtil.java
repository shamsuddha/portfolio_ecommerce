package com.example.ecommerce_api.util.api_util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponseUtil {

  public static <T> ResponseEntity<ApiResponse<T>> save(T t) {
    return new ResponseEntity<>(new ApiResponse<T>(t, ApiResponseType.save_success,
      UserInformErrorType.no_error,
      Boolean.TRUE, "Saved successfully"), HttpStatus.CREATED);
  }

  public static <T> ResponseEntity<ApiResponse<T>> save(T t, String message) {
    return new ResponseEntity<>(new ApiResponse<T>(t, ApiResponseType.save_success,
      UserInformErrorType.no_error,
      Boolean.TRUE, message), HttpStatus.CREATED);
  }


  public static <T> ResponseEntity<ApiResponse<T>> update(T t) {
    return new ResponseEntity<>(new ApiResponse<T>(t, ApiResponseType.update_success,
      UserInformErrorType.no_error,
      Boolean.TRUE, "Updated successfully"), HttpStatus.OK);
  }

  public static <T> ResponseEntity<ApiResponse<T>> update(T t, String message) {
    return new ResponseEntity<>(new ApiResponse<T>(t, ApiResponseType.update_success,UserInformErrorType.no_error,
      Boolean.TRUE, message), HttpStatus.OK);
  }

  public static <T> ResponseEntity<ApiResponse<T>> delete(T t) {
    return new ResponseEntity<>(new ApiResponse<T>(t, ApiResponseType.delete_success,UserInformErrorType.no_error,
      Boolean.TRUE, "Deleted successfully"), HttpStatus.OK);
  }

  public static <T> ResponseEntity<ApiResponse<T>> fetch(T t) {
    return new ResponseEntity<>(new ApiResponse<T>(t, ApiResponseType.fetch_success,UserInformErrorType.no_error,
      Boolean.TRUE, "Data Fetched successfully"), HttpStatus.OK);
  }

  public static <T> ResponseEntity<ApiResponse<T>> fetch(T t, String message) {
    return new ResponseEntity<>(new ApiResponse<T>(t, ApiResponseType.fetch_success,UserInformErrorType.no_error,
      Boolean.TRUE, message), HttpStatus.OK);
  }


  public static <T> ResponseEntity<ApiResponse<T>> error() {
    return new ResponseEntity<>(new ApiResponse<T>(null, ApiResponseType.default_error,
      UserInformErrorType.default_error,
      Boolean.FALSE, "Something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public static <T> ResponseEntity<ApiResponse<T>> error(ApiResponseType apiResponseType, String message) {
    return new ResponseEntity<>(new ApiResponse<T>(null, apiResponseType,
      UserInformErrorType.default_error,
      Boolean.FALSE, message), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public static <T> ResponseEntity<ApiResponse<T>> userInformError(String message) {
    return new ResponseEntity<>(new ApiResponse<T>(null, ApiResponseType.user_inform_error,
      UserInformErrorType.default_error,
      Boolean.FALSE, message), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public static <T> ResponseEntity<ApiResponse<T>> userInformSpecificError(
    String message, UserInformErrorType userInformErrorType) {
    return new ResponseEntity<>(new ApiResponse<T>(null, ApiResponseType.user_inform_error, userInformErrorType,
      Boolean.FALSE, message), HttpStatus.INTERNAL_SERVER_ERROR);
  }


  public <T> ResponseEntity<ApiResponse<T>> badRequest(String message) {
    return new ResponseEntity<>(new ApiResponse<T>(null, ApiResponseType.bad_request_error,
      UserInformErrorType.default_error,
      Boolean.FALSE, "Bad request"), HttpStatus.INTERNAL_SERVER_ERROR);
  }


}
