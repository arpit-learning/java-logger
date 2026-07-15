package dev.arpit.learning.logger.writers;

import dev.arpit.learning.logger.models.LogLevel;
import lombok.NonNull;
import org.slf4j.Logger;

public class Slf4jLogWriter implements ILogWriter {
  private final Logger logger;

  public Slf4jLogWriter(@NonNull Logger logger) {
    this.logger = logger;
  }

  @Override
  public void write(@NonNull LogLevel level, @NonNull String message) {
    switch (level) {
      case DEBUG:
        logger.debug(message);
        break;
      case WARN:
        logger.warn(message);
        break;
      case ERROR:
        logger.error(message);
        break;
      case TRACE:
        logger.trace(message);
        break;
      case WTF:
        logger.error(message);
      case INFO:
      default:
        logger.info(message);
    }
  }
}
