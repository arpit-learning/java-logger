package dev.arpit.learning.logger.levels;

import com.google.gson.Gson;
import dev.arpit.learning.logger.models.LogLevel;
import org.slf4j.Logger;

public class ErrorLogger extends AbstractJsonLogger {
  public ErrorLogger(Logger logger, Gson gson) {
    super(LogLevel.ERROR, logger, gson);
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
