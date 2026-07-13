package dev.arpit.pm1.logger.core;

import dev.arpit.pm1.logger.enrichers.ILogEnricher;
import dev.arpit.pm1.logger.models.ILoggerConstant;
import dev.arpit.pm1.logger.models.LogEvent;
import dev.arpit.pm1.logger.models.LoggerConstant;
import dev.arpit.pm1.logger.serializers.ILogSerializer;
import dev.arpit.pm1.logger.writers.ILogWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.NonNull;

public class LogPipeline {
  private final @NonNull List<ILogEnricher> enrichers;
  private final @NonNull ILogSerializer serializer;
  private final @NonNull ILogWriter logWriter;

  public LogPipeline(
      @NonNull List<ILogEnricher> enrichers,
      @NonNull ILogSerializer serializer,
      @NonNull ILogWriter logWriter) {
    this.enrichers = new ArrayList<>(enrichers);
    this.serializer = serializer;
    this.logWriter = logWriter;
  }

  public void execute(@NonNull LogEvent logEvent) {
    String formattedMessage = formatMessage(logEvent);
    this.logWriter.write(logEvent.getLevel(), formattedMessage);
  }

  public String formatMessage(@NonNull LogEvent logEvent) {
    for (ILogEnricher enricher : enrichers) {
      try {
        Map<ILoggerConstant, Object> attributes = enricher.enrich();
        for (Map.Entry<ILoggerConstant, Object> entry : attributes.entrySet()) {
          logEvent.getAttributes().put(entry.getKey(), entry.getValue());
        }
      } catch (Throwable t) {
        ILoggerConstant errorKey =
            () ->
                LoggerConstant.ENRICHMENT_ERROR.getValue()
                    + "_"
                    + enricher.getClass().getSimpleName();
        logEvent.getAttributes().put(errorKey, t.getMessage());
      }
    }
    return serializer.serialize(logEvent);
  }
}
