package dev.arpit.pm1.logger.writers;

import dev.arpit.pm1.logger.core.LoggerConfiguration;
import dev.arpit.pm1.logger.models.LogLevel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import lombok.NonNull;

public class FileLogWriter implements ILogWriter {
  @Override
  public void write(@NonNull LogLevel level, @NonNull String message) {
    synchronized (this) {
      try (BufferedWriter writer =
          new BufferedWriter(new FileWriter(LoggerConfiguration.getFileLoggingPath(), true))) {
        writer.write(message);
        writer.newLine();
      } catch (IOException e) {
        // Fallback to System.err if file writing fails
        System.err.println("Failed to write log to file: " + e.getMessage());
        System.err.println(message);
      }
    }
  }
}
