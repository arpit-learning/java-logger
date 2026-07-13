package dev.arpit.pm1.logger.enrichers;

import dev.arpit.pm1.logger.core.LoggerConfiguration;
import dev.arpit.pm1.logger.models.ILoggerConstant;
import dev.arpit.pm1.logger.models.LoggerConstant;
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
