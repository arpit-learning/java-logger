package dev.arpit.pm1.logger.levels;

import com.google.gson.Gson;
import dev.arpit.pm1.logger.models.LogLevel;
import org.slf4j.Logger;

public class InfoLogger extends AbstractJsonLogger {
  public InfoLogger(Logger logger, Gson gson) {
    super(LogLevel.INFO, logger, gson);
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
