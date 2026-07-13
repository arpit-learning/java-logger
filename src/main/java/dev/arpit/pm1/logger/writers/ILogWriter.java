package dev.arpit.pm1.logger.writers;

import dev.arpit.pm1.logger.models.LogLevel;
import lombok.NonNull;

public interface ILogWriter {
  void write(@NonNull LogLevel level, @NonNull String message);
}
