package dev.arpit.pm1.logger.enrichers;

import dev.arpit.pm1.logger.models.ILoggerConstant;
import java.util.Map;
import lombok.NonNull;

public interface ILogEnricher {
  @NonNull
  Map<ILoggerConstant, Object> enrich();
}
