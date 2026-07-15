package dev.arpit.learning.logger.writers;

import dev.arpit.learning.logger.models.LogLevel;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;

public class CompositeLogWriter implements ILogWriter {
  private final List<ILogWriter> writers = new ArrayList<>();

  public void addWriter(@NonNull ILogWriter writer) {
    writers.add(writer);
  }

  @Override
  public void write(@NonNull LogLevel level, @NonNull String message) {
    for (ILogWriter writer : writers) {
      writer.write(level, message);
    }
  }
}
