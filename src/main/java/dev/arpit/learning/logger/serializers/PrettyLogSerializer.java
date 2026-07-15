package dev.arpit.learning.logger.serializers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.arpit.learning.logger.models.ILoggerConstant;
import dev.arpit.learning.logger.models.LogEvent;
import dev.arpit.learning.logger.models.LoggerConstant;
import java.util.Map;
import lombok.NonNull;

public class PrettyLogSerializer implements ILogSerializer {
  private static final char TOP_LEFT_CORNER = '┌';
  private static final char BOTTOM_LEFT_CORNER = '└';
  private static final char MIDDLE_CORNER = '├';
  private static final char HORIZONTAL_LINE = '│';
  private static final String DOUBLE_DIVIDER =
      "────────────────────────────────────────────────────────";
  private static final String SINGLE_DIVIDER =
      "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";
  private static final String TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
  private static final String BOTTOM_BORDER = BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
  private static final String MIDDLE_BORDER = MIDDLE_CORNER + SINGLE_DIVIDER + SINGLE_DIVIDER;

  private final Gson prettyGson;

  public PrettyLogSerializer() {
    this.prettyGson =
        (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().serializeNulls().create();
  }

  @Override
  public String serialize(@NonNull LogEvent event) {
    StringBuilder builder = new StringBuilder();
    builder.append("\n");
    builder.append(TOP_BORDER).append("\n");

    // Thread Info
    Map<ILoggerConstant, Object> attrs = event.getAttributes();
    Object thread = attrs.get(LoggerConstant.ATTRIBUTE_THREAD_NAME);
    if (thread != null) {
      builder.append(HORIZONTAL_LINE).append(" Thread: ").append(thread).append("\n");
      builder.append(MIDDLE_BORDER).append("\n");
    }

    // Method Info
    Object className = attrs.get(LoggerConstant.ATTRIBUTE_CLASS);
    Object methodName = attrs.get(LoggerConstant.ATTRIBUTE_METHOD);
    Object line = attrs.get(LoggerConstant.ATTRIBUTE_LINE);
    if (className != null) {
      builder
          .append(HORIZONTAL_LINE)
          .append(" ")
          .append(className)
          .append(methodName != null ? "." + methodName : "")
          .append(line != null ? " (line " + line + ")" : "")
          .append("\n");
      builder.append(MIDDLE_BORDER).append("\n");
    }

    // Message
    Object message = attrs.get(LoggerConstant.ATTRIBUTE_MESSAGE);
    if (message != null) {
      String[] lines = String.valueOf(message).split(System.lineSeparator());
      for (String l : lines) {
        builder.append(HORIZONTAL_LINE).append(" ").append(l).append("\n");
      }
    }

    // Data
    Object data = attrs.get(LoggerConstant.ATTRIBUTE_DATA);
    if (data != null) {
      appendData(builder, data);
    }

    // Exception
    Throwable t = event.getThrowable();
    if (t != null) {
      builder.append(MIDDLE_BORDER).append("\n");
      builder.append(HORIZONTAL_LINE).append(" ").append(t).append("\n");
      for (StackTraceElement ste : t.getStackTrace()) {
        builder.append(HORIZONTAL_LINE).append("    at ").append(ste.toString()).append("\n");
      }
    }

    builder.append(BOTTOM_BORDER);
    return builder.toString();
  }

  private void appendData(StringBuilder builder, Object data) {
    builder.append(MIDDLE_BORDER).append("\n");
    if (data instanceof String) {
      String strData = (String) data;
      String trimmed = strData.trim();
      if (trimmed.startsWith("{") || trimmed.startsWith("[")) {
        try {
          Object parsed = this.prettyGson.fromJson(strData, Object.class);
          String pretty = this.prettyGson.toJson(parsed);
          for (String l : pretty.split(System.lineSeparator())) {
            builder.append(HORIZONTAL_LINE).append(" ").append(l).append("\n");
          }
          return;
        } catch (Exception e) {
          // Fallback if not valid JSON
        }
      }
      for (String l : strData.split(System.lineSeparator())) {
        builder.append(HORIZONTAL_LINE).append(" ").append(l).append("\n");
      }
    } else {
      String prettyData = this.prettyGson.toJson(data);
      for (String l : prettyData.split(System.lineSeparator())) {
        builder.append(HORIZONTAL_LINE).append(" ").append(l).append("\n");
      }
    }
  }
}
