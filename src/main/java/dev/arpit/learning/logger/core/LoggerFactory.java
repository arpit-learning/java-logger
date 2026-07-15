package dev.arpit.learning.logger.core;

import lombok.NonNull;

public class LoggerFactory {
  public static @NonNull ILogger getLogger(@NonNull String name) {
    org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(name);
    return new Logger(logger);
  }

  public static @NonNull ILogger getLogger(@NonNull Class<?> classType) {
    org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(classType);
    return new Logger(logger);
  }
}
