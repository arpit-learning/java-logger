package dev.arpit.learning.logger.enrichers;

import dev.arpit.learning.logger.core.LoggerConfiguration;
import dev.arpit.learning.logger.models.ILoggerConstant;
import dev.arpit.learning.logger.models.LoggerConstant;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;

public class TimestampEnricher implements ILogEnricher {
  @Override
  public @NonNull Map<ILoggerConstant, Object> enrich() {
    Map<ILoggerConstant, Object> attributes = new HashMap<>();

    long current = System.currentTimeMillis();
    attributes.put(LoggerConstant.ATTRIBUTE_EPOCH, current);
    attributes.put(
        LoggerConstant.ATTRIBUTE_TIMESTAMP, LoggerConfiguration.getFormatter().format(current));

    return attributes;
  }
}
