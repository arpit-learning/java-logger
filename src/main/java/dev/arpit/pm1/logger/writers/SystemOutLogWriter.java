package dev.arpit.pm1.logger.writers;

import dev.arpit.pm1.logger.models.LogLevel;
import lombok.NonNull;

public class SystemOutLogWriter implements ILogWriter {
  @Override
  public void write(@NonNull LogLevel level, @NonNull String message) {
    System.out.println(message);
  }
}
