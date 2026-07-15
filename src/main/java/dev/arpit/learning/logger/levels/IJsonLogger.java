package dev.arpit.learning.logger.levels;

import com.google.gson.JsonElement;
import dev.arpit.learning.logger.models.ILoggerEnabledException;
import dev.arpit.learning.logger.models.IMessageConstant;
import java.util.List;
import java.util.Map;
import lombok.NonNull;

public interface IJsonLogger {
  @NonNull
  IJsonLogger message(@NonNull String message);

  @NonNull
  IJsonLogger message(@NonNull IMessageConstant messageConstant);

  @NonNull
  IJsonLogger args(@NonNull String message, Object @NonNull ... args);

  @NonNull
  IJsonLogger args(@NonNull IMessageConstant messageConstant, Object @NonNull ... args);

  @NonNull
  IJsonLogger map(@NonNull String message, @NonNull Map<String, ?> map);

  @NonNull
  IJsonLogger map(@NonNull IMessageConstant messageConstant, @NonNull Map<String, ?> map);

  @NonNull
  IJsonLogger list(@NonNull String message, @NonNull List<?> list);

  @NonNull
  IJsonLogger list(@NonNull IMessageConstant messageConstant, @NonNull List<?> list);

  @NonNull
  IJsonLogger json(@NonNull String message, @NonNull JsonElement jsonElement);

  @NonNull
  IJsonLogger json(@NonNull IMessageConstant messageConstant, @NonNull JsonElement jsonElement);

  @NonNull
  IJsonLogger json(@NonNull String message, @NonNull String jsonString);

  @NonNull
  IJsonLogger json(@NonNull IMessageConstant messageConstant, @NonNull String jsonString);

  @NonNull
  IJsonLogger throwable(@NonNull Throwable throwable);

  @NonNull
  IJsonLogger throwable(@NonNull String message, @NonNull Throwable throwable);

  @NonNull
  IJsonLogger throwable(@NonNull IMessageConstant messageConstant, @NonNull Throwable throwable);

  @NonNull
  IJsonLogger throwable(@NonNull ILoggerEnabledException throwable);

  @NonNull
  IJsonLogger throwable(@NonNull String message, @NonNull ILoggerEnabledException throwable);

  @NonNull
  IJsonLogger throwable(
      @NonNull IMessageConstant messageConstant, @NonNull ILoggerEnabledException throwable);

  @NonNull
  IJsonLogger stack();

  void log();
}
