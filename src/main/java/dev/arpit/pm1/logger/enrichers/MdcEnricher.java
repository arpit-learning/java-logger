package dev.arpit.pm1.logger.enrichers;

import dev.arpit.pm1.logger.core.LoggerConfiguration;
import dev.arpit.pm1.logger.models.ILoggerConstant;
import dev.arpit.pm1.logger.models.LoggerConstant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

public class MdcEnricher implements ILogEnricher {
  @Override
  public @NonNull Map<ILoggerConstant, Object> enrich() {
    Map<String, String> mdc = MDC.getCopyOfContextMap();
    Map<ILoggerConstant, Object> attributes = new HashMap<>();

    if (mdc != null && !mdc.isEmpty()) {
      attributes.put(LoggerConstant.ATTRIBUTE_MDC, mdc);
    }

    @NonNull List<ILoggerConstant> keys = LoggerConfiguration.getMdcKeys();
    for (ILoggerConstant attr : keys) {
      String value = MDC.get(attr.getValue());
      if (StringUtils.isNotBlank(value)) {
        attributes.put(attr, value);
      }
    }

    return attributes;
  }
}
