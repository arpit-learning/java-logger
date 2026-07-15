package dev.arpit.learning.logger.writers;

import dev.arpit.learning.logger.models.LogLevel;
import lombok.NonNull;

public interface ILogWriter {
  void write(@NonNull LogLevel level, @NonNull String message);
}
