package com.example.ecommerce_api.enums;

import com.example.ecommerce_api.exception.InvalidEnumValueException;

public enum DiscountType {

  Percentage("Percentage","Percentage"),
  Consolidate("Consolidate","Consolidate");

  private final String key;
  private final String value;

  DiscountType(String key,String value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }
  public String getValue() {
    return value;
  }

  public static DiscountType getEnumByValue(String value) {
    for (DiscountType item : DiscountType.values()) {
      if (item.getValue().equals(value)) {
        return item;
      }
    }
    throw new InvalidEnumValueException();
  }
}
