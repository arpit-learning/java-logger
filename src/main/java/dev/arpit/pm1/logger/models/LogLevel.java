package dev.arpit.pm1.logger.models;

import lombok.Getter;

public enum LogLevel {
  DEBUG("DEBUG"),
  ERROR("ERROR"),
  INFO("INFO"),
  TRACE("TRACE"),
  WARN("WARN"),
  WTF("ASSERT");

  @Getter private final String value;

  LogLevel(String value) {
    this.value = value;
  }
}
