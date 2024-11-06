package com.example.ecommerce_api.util.api_util;

public enum ApiResponseType {

  save_success,
  save_failed,
  update_success,
  update_failed,
  delete_success,
  delete_failed,
  fetch_success,
  fetch_failed,

  default_error,
  user_inform_error,
  bad_request_error,
  unauthorized_error;

}
