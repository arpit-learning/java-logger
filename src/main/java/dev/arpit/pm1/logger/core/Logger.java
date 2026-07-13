package dev.arpit.pm1.logger.core;

import dev.arpit.pm1.logger.models.ILoggerEnabledException;
import dev.arpit.pm1.logger.models.IMessageConstant;
import java.util.List;
import java.util.Map;
import lombok.NonNull;

public class Logger extends BaseLogger implements ILogger {

  public Logger(org.slf4j.Logger logger) {
    super(logger);
  }

  @Override
  public void info(@NonNull String message) {
    super.info().message(message).log();
  }

  @Override
  public void info(@NonNull IMessageConstant messageConstant) {
    super.info().message(messageConstant).log();
  }

  @Override
  public void info(@NonNull String message, Object @NonNull ... args) {
    super.info().args(message, args).log();
  }

  @Override
  public void info(@NonNull IMessageConstant messageConstant, Object @NonNull ... args) {
    super.info().args(messageConstant, args).log();
  }

  @Override
  public void error(@NonNull String message) {
    super.error().message(message).log();
  }

  @Override
  public void error(@NonNull IMessageConstant messageConstant) {
    super.error().message(messageConstant).log();
  }

  @Override
  public void error(@NonNull String message, Object @NonNull ... args) {
    super.error().args(message, args).log();
  }

  @Override
  public void error(@NonNull IMessageConstant messageConstant, Object @NonNull ... args) {
    super.error().args(messageConstant, args).log();
  }

  @Override
  public void error(@NonNull Throwable e) {
    super.error().throwable(e).log();
  }

  @Override
  public void error(@NonNull String message, @NonNull Throwable e) {
    super.error().throwable(message, e).log();
  }

  @Override
  public void error(@NonNull IMessageConstant messageConstant, @NonNull Throwable e) {
    super.error().throwable(messageConstant, e).log();
  }

  @Override
  public void error(@NonNull ILoggerEnabledException e) {
    super.error().throwable(e).log();
  }

  @Override
  public void error(@NonNull String message, @NonNull ILoggerEnabledException e) {
    super.error().throwable(message, e).log();
  }

  @Override
  public void error(@NonNull IMessageConstant messageConstant, @NonNull ILoggerEnabledException e) {
    super.error().throwable(messageConstant, e).log();
  }

  @Override
  public void debug(@NonNull String message) {
    super.debug().message(message).log();
  }

  @Override
  public void debug(@NonNull IMessageConstant messageConstant) {
    super.debug().message(messageConstant).log();
  }

  @Override
  public void debug(@NonNull String message, Object @NonNull ... args) {
    super.debug().args(message, args).log();
  }

  @Override
  public void debug(@NonNull IMessageConstant messageConstant, Object @NonNull ... args) {
    super.debug().args(messageConstant, args).log();
  }

  @Override
  public void debug(@NonNull String message, @NonNull Map<String, ?> map) {
    super.debug().map(message, map).log();
  }

  @Override
  public void debug(@NonNull IMessageConstant messageConstant, @NonNull Map<String, ?> map) {
    super.debug().map(messageConstant, map).log();
  }

  @Override
  public void debug(@NonNull String message, @NonNull List<?> list) {
    super.debug().list(message, list).log();
  }

  @Override
  public void debug(@NonNull IMessageConstant messageConstant, @NonNull List<?> list) {
    super.debug().list(messageConstant, list).log();
  }

  @Override
  public void debug(@NonNull Throwable e) {
    super.debug().throwable(e).log();
  }

  @Override
  public void debug(@NonNull String message, @NonNull Throwable e) {
    super.debug().throwable(message, e).log();
  }

  @Override
  public void debug(@NonNull IMessageConstant messageConstant, @NonNull Throwable e) {
    super.debug().throwable(messageConstant, e).log();
  }

  @Override
  public void debug(@NonNull ILoggerEnabledException e) {
    super.debug().throwable(e).log();
  }

  @Override
  public void debug(@NonNull String message, @NonNull ILoggerEnabledException e) {
    super.debug().throwable(message, e).log();
  }

  @Override
  public void debug(@NonNull IMessageConstant messageConstant, @NonNull ILoggerEnabledException e) {
    super.debug().throwable(messageConstant, e).log();
  }

  @Override
  public void warn(@NonNull String message) {
    super.warn().message(message).log();
  }

  @Override
  public void warn(@NonNull IMessageConstant messageConstant) {
    super.warn().message(messageConstant).log();
  }

  @Override
  public void warn(@NonNull String message, Object @NonNull ... args) {
    super.warn().args(message, args).log();
  }

  @Override
  public void warn(@NonNull IMessageConstant messageConstant, Object @NonNull ... args) {
    super.warn().args(messageConstant, args).log();
  }

  @Override
  public void warn(@NonNull Throwable e) {
    super.warn().throwable(e).log();
  }

  @Override
  public void warn(@NonNull String message, @NonNull Throwable e) {
    super.warn().throwable(message, e).log();
  }

  @Override
  public void warn(@NonNull IMessageConstant messageConstant, @NonNull Throwable e) {
    super.warn().throwable(messageConstant, e).log();
  }

  @Override
  public void warn(@NonNull ILoggerEnabledException e) {
    super.warn().throwable(e).log();
  }

  @Override
  public void warn(@NonNull String message, @NonNull ILoggerEnabledException e) {
    super.warn().throwable(message, e).log();
  }

  @Override
  public void warn(@NonNull IMessageConstant messageConstant, @NonNull ILoggerEnabledException e) {
    super.warn().throwable(messageConstant, e).log();
  }

  @Override
  public void trace(@NonNull String message) {
    super.trace().message(message).log();
  }

  @Override
  public void trace(@NonNull IMessageConstant messageConstant) {
    super.trace().message(messageConstant).log();
  }

  @Override
  public void trace(@NonNull String message, Object @NonNull ... args) {
    super.trace().args(message, args).log();
  }

  @Override
  public void trace(@NonNull IMessageConstant messageConstant, Object @NonNull ... args) {
    super.trace().args(messageConstant, args).log();
  }

  @Override
  public void trace(@NonNull Throwable e) {
    super.trace().throwable(e).log();
  }

  @Override
  public void trace(@NonNull String message, @NonNull Throwable e) {
    super.trace().throwable(message, e).log();
  }

  @Override
  public void trace(@NonNull IMessageConstant messageConstant, @NonNull Throwable e) {
    super.trace().throwable(messageConstant, e).log();
  }

  @Override
  public void trace(@NonNull ILoggerEnabledException e) {
    super.trace().throwable(e).log();
  }

  @Override
  public void trace(@NonNull String message, @NonNull ILoggerEnabledException e) {
    super.trace().throwable(message, e).log();
  }

  @Override
  public void trace(@NonNull IMessageConstant messageConstant, @NonNull ILoggerEnabledException e) {
    super.trace().throwable(messageConstant, e).log();
  }

  @Override
  public void wtf(@NonNull String message) {
    super.wtf().message(message).log();
  }

  @Override
  public void wtf(@NonNull IMessageConstant messageConstant) {
    super.wtf().message(messageConstant).log();
  }

  @Override
  public void wtf(@NonNull String message, Object @NonNull ... args) {
    super.wtf().args(message, args).log();
  }

  @Override
  public void wtf(@NonNull IMessageConstant messageConstant, Object @NonNull ... args) {
    super.wtf().args(messageConstant, args).log();
  }
}
