package com.example.ecommerce_api.enums;

import io.hms.api.exception.InvalidEnumValueException;

public enum PaymentType {

  Advance("Advance","Advance"),
  Paid("Paid","Paid"),
  Return("Return","Return");

  private final String key;
  private final String value;

  PaymentType(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }
  public String getValue() {
    return value;
  }

  public static PaymentType getEnumByValue(String value) {
    for (PaymentType item : PaymentType.values()) {
      if (item.getValue().equals(value)) {
        return item;
      }
    }
    throw new InvalidEnumValueException();
  }
}
