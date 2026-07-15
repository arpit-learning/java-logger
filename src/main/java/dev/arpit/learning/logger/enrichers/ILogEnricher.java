package dev.arpit.learning.logger.enrichers;

import dev.arpit.learning.logger.models.ILoggerConstant;
import java.util.Map;
import lombok.NonNull;

public interface ILogEnricher {
  @NonNull
  Map<ILoggerConstant, Object> enrich();
}
