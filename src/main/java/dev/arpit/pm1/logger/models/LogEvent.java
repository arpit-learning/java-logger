package dev.arpit.pm1.logger.models;

import com.google.gson.JsonElement;
import dev.arpit.pm1.logger.utils.LogDataFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.NonNull;

@Getter
public final class LogEvent {
  private final @NonNull LogLevel level;
  private final @NonNull String component;
  private final @NonNull Map<ILoggerConstant, Object> attributes;
  private final Throwable throwable;
  private final String stackTrace;

  private LogEvent(
      @NonNull LogLevel level,
      @NonNull String component,
      Map<ILoggerConstant, Object> attributes,
      Throwable throwable,
      String stackTrace) {
    this.level = level;
    this.component = component;
    this.attributes = new HashMap<>(attributes);
    this.throwable = throwable;
    this.stackTrace = stackTrace;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private LogLevel level;
    private String component;
    private Map<ILoggerConstant, Object> attributes = new HashMap<>();
    private Throwable throwable;
    private String stackTrace;

    public Builder setLevel(@NonNull LogLevel level) {
      this.level = level;
      return this;
    }

    public Builder setComponent(@NonNull String component) {
      this.component = component;
      return this;
    }

    public Builder setAttributes(@NonNull Map<ILoggerConstant, Object> attributes) {
      this.attributes = new HashMap<>(attributes);
      return this;
    }

    private void addAttribute(@NonNull ILoggerConstant key, @NonNull Object value) {
      this.attributes.put(key, value);
    }

    public Builder setMessage(@NonNull String message) {
      addAttribute(LoggerConstant.ATTRIBUTE_MESSAGE, message);
      return this;
    }

    public Builder setMessage(@NonNull IMessageConstant messageConstant) {
      addAttribute(LoggerConstant.ATTRIBUTE_MESSAGE, messageConstant.getMessage());
      return this;
    }

    public Builder setArgs(Object @NonNull ... args) {
      if (args.length > 0) {
        String message = LogDataFormatter.formatArgs(args);
        addAttribute(LoggerConstant.ATTRIBUTE_ARGS, message);
      }
      return this;
    }

    public Builder setMap(@NonNull Map<String, ?> map) {
      addAttribute(LoggerConstant.ATTRIBUTE_DATA, map);
      return this;
    }

    public Builder setList(@NonNull List<?> list) {
      addAttribute(LoggerConstant.ATTRIBUTE_DATA, list);
      return this;
    }

    public Builder setJson(@NonNull JsonElement jsonElement) {
      addAttribute(LoggerConstant.ATTRIBUTE_DATA, jsonElement);
      return this;
    }

    public Builder setJsonString(@NonNull String jsonString) {
      addAttribute(LoggerConstant.ATTRIBUTE_DATA, jsonString);
      return this;
    }

    public Builder setThrowable(@NonNull Throwable throwable) {
      this.throwable = throwable;
      return this;
    }

    public Builder setStackTrace(@NonNull String stackTrace) {
      this.stackTrace = stackTrace;
      return this;
    }

    private void validate() {}

    public LogEvent build() {
      validate();
      return new LogEvent(level, component, attributes, throwable, stackTrace);
    }
  }
}
