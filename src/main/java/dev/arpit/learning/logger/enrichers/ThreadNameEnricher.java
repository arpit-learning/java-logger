package dev.arpit.learning.logger.enrichers;

import dev.arpit.learning.logger.models.ILoggerConstant;
import dev.arpit.learning.logger.models.LoggerConstant;
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
