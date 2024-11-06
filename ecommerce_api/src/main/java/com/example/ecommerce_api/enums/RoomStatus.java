package com.example.ecommerce_api.enums;

import com.example.ecommerce_api.exception.InvalidEnumValueException;

public enum RoomStatus {

  Booked("Booked","Booked"),
  Occupied("Occupied","Occupied"),
  Unoccupied("Unoccupied","Unoccupied"),
  Ready("Ready","Ready");

  private final String key;
  private final String value;

  RoomStatus(String key,String value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }
  public String getValue() {
    return value;
  }

  public static RoomStatus getEnumByValue(String value) {
    for (RoomStatus item : RoomStatus.values()) {
      if (item.getValue().equals(value)) {
        return item;
      }
    }
    throw new InvalidEnumValueException();
  }
}
