package dev.arpit.pm1.logger.enrichers;

import dev.arpit.pm1.logger.models.ILoggerConstant;
import dev.arpit.pm1.logger.models.LoggerConstant;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;

public class ThreadNameEnricher implements ILogEnricher {
  @Override
  public @NonNull Map<ILoggerConstant, Object> enrich() {
    Map<ILoggerConstant, Object> attributes = new HashMap<>();

    attributes.put(LoggerConstant.ATTRIBUTE_THREAD_NAME, Thread.currentThread().getName());

    return attributes;
  }
}
