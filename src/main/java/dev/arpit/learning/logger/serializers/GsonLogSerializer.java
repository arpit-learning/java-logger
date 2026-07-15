package dev.arpit.learning.logger.serializers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.arpit.learning.logger.models.LogEvent;
import dev.arpit.learning.logger.models.LoggerConstant;
import dev.arpit.learning.logger.utils.ExceptionFormatter;
import lombok.NonNull;

public class GsonLogSerializer implements ILogSerializer {
  private final @NonNull Gson gson;

  public GsonLogSerializer(@NonNull Gson gson) {
    this.gson = gson;
  }

  @Override
  public String serialize(@NonNull LogEvent event) {
    JsonObject jsonObject = new JsonObject();

    // 1. Add all attributes collected in event
    event
        .getAttributes()
        .forEach(
            (key, value) -> {
              try {
                jsonObject.add(key.getValue(), gson.toJsonTree(value));
              } catch (Throwable t) {
                // Circular dependency or Gson serialization failure fallback:
                // If it fails, serialize it as a string
                jsonObject.addProperty(key.getValue(), String.valueOf(value));
              }
            });

    // 2. Set level and component
    jsonObject.add(LoggerConstant.ATTRIBUTE_LEVEL.getValue(), gson.toJsonTree(event.getLevel()));
    jsonObject.add(
        LoggerConstant.ATTRIBUTE_COMPONENT.getValue(), gson.toJsonTree(event.getComponent()));

    // 3. Handle throwable formatting if present
    if (event.getThrowable() != null) {
      try {
        JsonElement exceptionJsonElement = ExceptionFormatter.format(event.getThrowable(), gson);
        JsonObject exceptionJsonObject = exceptionJsonElement.getAsJsonObject();
        for (String key : exceptionJsonObject.keySet()) {
          jsonObject.add(key, exceptionJsonObject.get(key));
        }
      } catch (Throwable t) {
        jsonObject.add(
            LoggerConstant.ATTRIBUTE_EXCEPTION.getValue(), gson.toJsonTree(String.valueOf(t)));
      }
    }

    return gson.toJson(jsonObject);
  }
}
