package com.example.ecommerce_api.exception;

public class InvalidEnumValueException extends RuntimeException{
  private static final long serialVersionUID = 1L;

  public InvalidEnumValueException() {
    // TODO Auto-generated constructor
  }

  public InvalidEnumValueException(String message) {
    super(message);
  }

  public InvalidEnumValueException(String message, Throwable cause) {
    super(message, cause);
  }
}
