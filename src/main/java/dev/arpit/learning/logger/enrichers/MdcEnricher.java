package dev.arpit.learning.logger.enrichers;

import dev.arpit.learning.logger.core.LoggerConfiguration;
import dev.arpit.learning.logger.models.ILoggerConstant;
import dev.arpit.learning.logger.models.LoggerConstant;
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
