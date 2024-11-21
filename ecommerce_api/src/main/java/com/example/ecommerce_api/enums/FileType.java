package io.hms.api.enums;

import io.hms.api.exception.InvalidEnumValueException;

public enum FileType {

  RoomImage("RoomImage","Room Image"),
  HotelImage("HotelImage","Hotel Image");

  private final String key;
  private final String value;

  FileType(String key,String value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }

  public static FileType getEnumByValue(String value) {
    for (FileType item : FileType.values()) {
      if (item.getValue().equals(value)) {
        return item;
      }
    }
    throw new InvalidEnumValueException();
  }
}
