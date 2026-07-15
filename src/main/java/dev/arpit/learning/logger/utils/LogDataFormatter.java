package dev.arpit.learning.logger.utils;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.NonNull;

public class LogDataFormatter {
  public static String formatArgs(Object @NonNull ... args) {
    if (args.length == 0) {
      return "";
    }
    return Stream.of(args).map(String::valueOf).collect(Collectors.joining(", "));
  }
}
