package dev.arpit.learning.logger.levels;

import com.google.gson.Gson;
import dev.arpit.learning.logger.models.LogLevel;
import org.slf4j.Logger;

public class WtfLogger extends AbstractJsonLogger {
  public WtfLogger(Logger logger, Gson gson) {
    super(LogLevel.WTF, logger, gson);
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
