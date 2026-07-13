package dev.arpit.pm1.logger.levels;

import com.google.gson.Gson;
import dev.arpit.pm1.logger.models.LogLevel;
import org.slf4j.Logger;

public class DebugLogger extends AbstractJsonLogger {
  public DebugLogger(Logger logger, Gson gson) {
    super(LogLevel.DEBUG, logger, gson);
  }

  @Override
  public void log() {
    this.executePipeline();
  }

  @Override
  public String toString() {
    return this.formatMessage();
  }
}
