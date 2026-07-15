package dev.arpit.learning.logger.levels;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import dev.arpit.learning.logger.core.LogPipeline;
import dev.arpit.learning.logger.core.LoggerConfiguration;
import dev.arpit.learning.logger.models.ILoggerEnabledException;
import dev.arpit.learning.logger.models.IMessageConstant;
import dev.arpit.learning.logger.models.LogEvent;
import dev.arpit.learning.logger.models.LogLevel;
import dev.arpit.learning.logger.utils.StackTraceUtil;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.slf4j.Logger;

@Getter
@Setter
public abstract class AbstractJsonLogger implements IJsonLogger {
  private @NonNull LogLevel logLevel;
  private LogEvent logEvent;
  private @NonNull LogEvent.Builder logEventBuilder;
  private @NonNull LogPipeline logPipeline;

  protected AbstractJsonLogger(
      @NonNull LogLevel logLevel, @NonNull Logger logger, @NonNull Gson gson) {
    this.logLevel = logLevel;
    this.logEventBuilder =
        LogEvent.builder().setComponent(LoggerConfiguration.getComponentName()).setLevel(logLevel);
    this.logPipeline =
        new LogPipeline(
            LoggerConfiguration.getInstance().getEnrichers(),
            LoggerConfiguration.getInstance().createSerializer(gson),
            LoggerConfiguration.getInstance().createWriter(logger));
  }

  @Override
  public @NonNull IJsonLogger message(@NonNull String message) {
    this.logEventBuilder.setMessage(message);
    return this;
  }

  @Override
  public @NonNull IJsonLogger message(@NonNull IMessageConstant messageConstant) {
    this.logEventBuilder.setMessage(messageConstant);
    return this;
  }

  @Override
  public @NonNull IJsonLogger args(@NonNull String message, Object @NonNull ... args) {
    this.logEventBuilder.setMessage(message).setArgs(args);
    return this;
  }

  @Override
  public @NonNull IJsonLogger args(
      @NonNull IMessageConstant messageConstant, Object @NonNull ... args) {
    this.logEventBuilder.setMessage(messageConstant).setArgs(args);
    return this;
  }

  @Override
  public @NonNull IJsonLogger map(@NonNull String message, @NonNull Map<String, ?> map) {
    this.logEventBuilder.setMessage(message).setMap(map);
    return this;
  }

  @Override
  public @NonNull IJsonLogger map(
      @NonNull IMessageConstant messageConstant, @NonNull Map<String, ?> map) {
    this.logEventBuilder.setMessage(messageConstant).setMap(map);
    return this;
  }

  @Override
  public @NonNull IJsonLogger list(@NonNull String message, @NonNull List<?> list) {
    this.logEventBuilder.setMessage(message).setList(list);
    return this;
  }

  @Override
  public @NonNull IJsonLogger list(
      @NonNull IMessageConstant messageConstant, @NonNull List<?> list) {
    this.logEventBuilder.setMessage(messageConstant).setList(list);
    return this;
  }

  @Override
  public @NonNull IJsonLogger json(@NonNull String message, @NonNull JsonElement jsonElement) {
    this.logEventBuilder.setMessage(message).setJson(jsonElement);
    return this;
  }

  @Override
  public @NonNull IJsonLogger json(
      @NonNull IMessageConstant messageConstant, @NonNull JsonElement jsonElement) {
    this.logEventBuilder.setMessage(messageConstant).setJson(jsonElement);
    return this;
  }

  @Override
  public @NonNull IJsonLogger json(@NonNull String message, @NonNull String jsonString) {
    this.logEventBuilder.setMessage(message).setJsonString(jsonString);
    return this;
  }

  @Override
  public @NonNull IJsonLogger json(
      @NonNull IMessageConstant messageConstant, @NonNull String jsonString) {
    this.logEventBuilder.setMessage(messageConstant).setJsonString(jsonString);
    return this;
  }

  @Override
  public @NonNull IJsonLogger throwable(@NonNull Throwable throwable) {
    this.logEventBuilder.setThrowable(throwable);
    return this;
  }

  @Override
  public @NonNull IJsonLogger throwable(@NonNull String message, @NonNull Throwable throwable) {
    this.logEventBuilder.setMessage(message).setThrowable(throwable);
    return this;
  }

  @Override
  public @NonNull IJsonLogger throwable(
      @NonNull IMessageConstant messageConstant, @NonNull Throwable throwable) {
    this.logEventBuilder.setMessage(messageConstant).setThrowable(throwable);
    return this;
  }

  @Override
  public @NonNull IJsonLogger throwable(@NonNull ILoggerEnabledException throwable) {
    this.logEventBuilder.setThrowable(throwable);
    return this;
  }

  @Override
  public @NonNull IJsonLogger throwable(
      @NonNull String message, @NonNull ILoggerEnabledException throwable) {
    this.logEventBuilder.setMessage(message).setThrowable(throwable);
    return this;
  }

  @Override
  public @NonNull IJsonLogger throwable(
      @NonNull IMessageConstant messageConstant, @NonNull ILoggerEnabledException throwable) {
    this.logEventBuilder.setMessage(messageConstant).setThrowable(throwable);
    return this;
  }

  @Override
  public @NonNull IJsonLogger stack() {
    this.logEventBuilder.setStackTrace(StackTraceUtil.formatStack());
    return this;
  }

  protected @NonNull String formatMessage() {
    this.setLogEvent(this.getLogEventBuilder().build());
    return this.logPipeline.formatMessage(this.logEvent);
  }

  protected void executePipeline() {
    this.setLogEvent(this.getLogEventBuilder().build());
    this.logPipeline.execute(this.logEvent);
  }
}
