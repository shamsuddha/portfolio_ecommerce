package com.example.ecommerce_api.exception;


public class UserInformException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public UserInformException(String message) {
    super(message);
  }

  public UserInformException(String message, Throwable cause) {
    super(message, cause);
  }

}
