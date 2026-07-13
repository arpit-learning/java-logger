package dev.arpit.pm1.logger.enrichers;

import dev.arpit.pm1.logger.models.*;
import java.util.*;
import lombok.NonNull;

public class CallerClassEnricher implements ILogEnricher {
  private static final StackWalker WALKER = StackWalker.getInstance();
  private final Set<String> excludedClasses;

  public CallerClassEnricher(Set<String> excludedClasses) {
    this.excludedClasses = excludedClasses;
  }

  @Override
  public @NonNull Map<ILoggerConstant, Object> enrich() {
    Optional<StackWalker.StackFrame> callerFrameOpt =
        WALKER.walk(
            frames ->
                frames
                    .filter(
                        frame ->
                            !excludedClasses.contains(frame.getClassName())
                                && !frame.getClassName().startsWith("java.lang."))
                    .findFirst());

    Map<ILoggerConstant, Object> attributes = new HashMap<>();

    if (callerFrameOpt.isPresent()) {
      StackWalker.StackFrame callerFrame = callerFrameOpt.get();
      attributes.put(LoggerConstant.ATTRIBUTE_CLASS, callerFrame.getClassName());
      attributes.put(LoggerConstant.ATTRIBUTE_METHOD, callerFrame.getMethodName());
      attributes.put(LoggerConstant.ATTRIBUTE_LINE, callerFrame.getLineNumber());
    } else {
      attributes.put(LoggerConstant.ATTRIBUTE_CLASS, "Unknown");
    }

    return attributes;
  }
}
