package dev.arpit.learning.logger.enrichers;

import dev.arpit.learning.logger.core.LoggerConfiguration;
import dev.arpit.learning.logger.models.ILoggerConstant;
import dev.arpit.learning.logger.models.LoggerConstant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;

public class TimestampEnricher implements ILogEnricher {
  @Override
  public @NonNull Map<ILoggerConstant, Object> enrich() {
    Map<ILoggerConstant, Object> attributes = new HashMap<>();

    LocalDateTime localDateTime = LocalDateTime.now();
    attributes.put(
        LoggerConstant.ATTRIBUTE_EPOCH, localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli());
    attributes.put(
        LoggerConstant.ATTRIBUTE_TIMESTAMP,
        LoggerConfiguration.getFormatter().format(localDateTime));

    return attributes;
  }
}
