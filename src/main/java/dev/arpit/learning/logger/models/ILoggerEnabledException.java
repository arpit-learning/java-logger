package dev.arpit.learning.logger.models;

import java.util.Map;
import lombok.NonNull;

public abstract class ILoggerEnabledException extends Exception {
  public ILoggerEnabledException() {}

  public ILoggerEnabledException(@NonNull String message) {
    super(message);
  }

  public abstract Map<String, Object> getFormattedMap();
}
