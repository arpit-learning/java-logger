package dev.arpit.learning.logger.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.arpit.learning.logger.levels.IJsonLogger;
import dev.arpit.learning.logger.levels.NoopLogger;
import dev.arpit.learning.logger.models.LogLevel;
import lombok.NonNull;
import org.slf4j.Logger;

public class BaseLogger implements IBaseLogger {
  private final Logger logger;
  private final Gson gson = (new GsonBuilder()).disableHtmlEscaping().serializeNulls().create();
  private final NoopLogger noopLogger = new NoopLogger();

  public BaseLogger(@NonNull Logger logger) {
    this.logger = logger;
  }

  @Override
  public @NonNull IJsonLogger info() {
    if (this.logger.isInfoEnabled()) {
      return LoggerConfiguration.getInstance().createLogger(LogLevel.INFO, this.logger, this.gson);
    }
    return this.noopLogger;
  }

  @Override
  public @NonNull IJsonLogger debug() {
    if (this.logger.isDebugEnabled()) {
      return LoggerConfiguration.getInstance().createLogger(LogLevel.DEBUG, this.logger, this.gson);
    }
    return this.noopLogger;
  }

  @Override
  public @NonNull IJsonLogger trace() {
    if (this.logger.isTraceEnabled()) {
      return LoggerConfiguration.getInstance().createLogger(LogLevel.TRACE, this.logger, this.gson);
    }
    return this.noopLogger;
  }

  @Override
  public @NonNull IJsonLogger warn() {
    if (this.logger.isWarnEnabled()) {
      return LoggerConfiguration.getInstance().createLogger(LogLevel.WARN, this.logger, this.gson);
    }
    return this.noopLogger;
  }

  @Override
  public @NonNull IJsonLogger wtf() {
    if (this.logger.isErrorEnabled()) {
      return LoggerConfiguration.getInstance().createLogger(LogLevel.WTF, this.logger, this.gson);
    }
    return this.noopLogger;
  }

  @Override
  public @NonNull IJsonLogger error() {
    if (this.logger.isErrorEnabled()) {
      return LoggerConfiguration.getInstance().createLogger(LogLevel.ERROR, this.logger, this.gson);
    }
    return this.noopLogger;
  }
}
