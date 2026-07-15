package dev.arpit.learning.logger.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.arpit.learning.logger.models.ILoggerEnabledException;
import dev.arpit.learning.logger.models.LoggerConstant;
import java.util.Map;
import lombok.NonNull;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class ExceptionFormatter {
  public static @NonNull JsonElement format(@NonNull Throwable throwable, @NonNull Gson gson) {
    JsonObject jsonObject = new JsonObject();

    if (throwable instanceof ILoggerEnabledException exception) {
      Map<String, Object> exceptionDetailsMap = exception.getFormattedMap();

      for (Map.Entry<String, Object> entry : exceptionDetailsMap.entrySet()) {
        jsonObject.add(entry.getKey(), gson.toJsonTree(entry.getValue()));
      }
    } else {
      jsonObject.add(
          LoggerConstant.ATTRIBUTE_EXCEPTION.getValue(),
          gson.toJsonTree(ExceptionUtils.getStackTrace(throwable)));
    }

    return jsonObject;
  }
}
