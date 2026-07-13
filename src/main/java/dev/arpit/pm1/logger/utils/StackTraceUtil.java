package dev.arpit.pm1.logger.utils;

import lombok.NonNull;

public class StackTraceUtil {
  public static @NonNull String formatStack() {
    StringBuilder output = new StringBuilder();
    StackTraceElement[] stackTraceElements = (new Throwable()).getStackTrace();
    if (stackTraceElements.length > 2) {
      output.append(stackTraceElements[2]);
      for (int index = 3; index < stackTraceElements.length; ++index) {
        output.append("\n\tat ").append(stackTraceElements[index]);
      }
    }
    return output.toString();
  }
}
