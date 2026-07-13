package dev.arpit.pm1.logger.levels;

import com.google.gson.JsonElement;
import dev.arpit.pm1.logger.models.ILoggerEnabledException;
import dev.arpit.pm1.logger.models.IMessageConstant;
import java.util.List;
import java.util.Map;
import lombok.NonNull;

public class NoopLogger implements IJsonLogger {
  @Override
  public @NonNull IJsonLogger message(@NonNull String message) {
    return this;
  }

  @Override
  public @NonNull IJsonLogger message(@NonNull IMessageConstant messageConstant) {
    return this;
  }

  @Override
  public @NonNull IJsonLogger args(@NonNull String message, Object @NonNull ... args) {
    return this;
  }

  @Override
  public @NonNull IJsonLogger args(
      @NonNull IMessageConstant messageConstant, Object @NonNull ... args) {
    return this;
  }

  @Override
  public @NonNull IJsonLogger map(@NonNull String message, @NonNull Map<String, ?> map) {
    return this;
  }

  @Override
  public @NonNull IJsonLogger map(
      @NonNull IMessageConstant messageConstant, @NonNull Map<String, ?> map) {
    return this;
  }

  @Override
  public @NonNull IJsonLogger list(@NonNull String message, @NonNull List<?> list) {
    return this;
  }

  @Override
  public @NonNull IJsonLogger list(
      @NonNull IMessageConstant messageConstant, @NonNull List<?> list) {
    return this;
  }

  @Override
  public @NonNull IJsonLogger json(@NonNull String message, @NonNull JsonElement jsonElement) {
    return this;
  }

  @Override
  public @NonNull IJsonLogger json(
      @NonNull IMessageConstant messageConstant, @NonNull JsonElement jsonElement) {
    return this;
  }

  @Override
  public @NonNull IJsonLogger json(@NonNull String message, @NonNull String jsonString) {
    return this;
  }

  @Override
  public @NonNull IJsonLogger json(
      @NonNull IMessageConstant messageConstant, @NonNull String jsonString) {
    return this;
  }

  @Override
  public @NonNull IJsonLogger throwable(@NonNull Throwable throwable) {
    return this;
  }

  @Override
  public @NonNull IJsonLogger throwable(@NonNull String message, @NonNull Throwable throwable) {
    return this;
  }

  @Override
  public @NonNull IJsonLogger throwable(
      @NonNull IMessageConstant messageConstant, @NonNull Throwable throwable) {
    return this;
  }

  @Override
  public @NonNull IJsonLogger throwable(@NonNull ILoggerEnabledException throwable) {
    return this;
  }

  @Override
  public @NonNull IJsonLogger throwable(
      @NonNull String message, @NonNull ILoggerEnabledException throwable) {
    return this;
  }

  @Override
  public @NonNull IJsonLogger throwable(
      @NonNull IMessageConstant messageConstant, @NonNull ILoggerEnabledException throwable) {
    return this;
  }

  @Override
  public @NonNull IJsonLogger stack() {
    return this;
  }

  @Override
  public void log() {}
}
