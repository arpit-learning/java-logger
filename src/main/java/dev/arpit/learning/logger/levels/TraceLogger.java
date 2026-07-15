package dev.arpit.learning.logger.levels;

import com.google.gson.Gson;
import dev.arpit.learning.logger.models.LogLevel;
import org.slf4j.Logger;

public class TraceLogger extends AbstractJsonLogger {
  public TraceLogger(Logger logger, Gson gson) {
    super(LogLevel.TRACE, logger, gson);
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
