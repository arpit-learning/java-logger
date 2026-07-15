package dev.arpit.learning.logger.writers;

import static org.junit.jupiter.api.Assertions.*;

import dev.arpit.learning.logger.core.LoggerConfiguration;
import dev.arpit.learning.logger.models.LogLevel;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileLogWriterTest {
  private File tempFile;
  private String originalPath;

  @BeforeEach
  public void setUp() throws IOException {
    originalPath = LoggerConfiguration.getFileLoggingPath();
    tempFile = File.createTempFile("test-log", ".log");
    LoggerConfiguration.setFileLoggingPath(tempFile.getAbsolutePath());
  }

  @AfterEach
  public void tearDown() {
    LoggerConfiguration.setFileLoggingPath(originalPath);
    if (tempFile.exists()) {
      tempFile.delete();
    }
  }

  @Test
  public void test_objectCreation() {
    // act
    FileLogWriter writer = new FileLogWriter();

    // assert
    assertNotNull(writer);
  }

  @Test
  public void test_write_withNullLogLevelAndValidMessage_shouldThrowNPE() {
    // arrange
    FileLogWriter writer = new FileLogWriter();

    // act & assert
    assertThrows(NullPointerException.class, () -> writer.write(null, "Test message"));
  }

  @Test
  public void test_write_withValidLogLevelAndNullMessage_shouldThrowNPE() {
    // arrange
    FileLogWriter writer = new FileLogWriter();

    // act & assert
    assertThrows(NullPointerException.class, () -> writer.write(LogLevel.INFO, null));
  }

  @Test
  public void test_write_withValidFilePath_shouldWriteLogTOTheFile() throws IOException {
    // arrange
    FileLogWriter writer = new FileLogWriter();

    // act
    writer.write(LogLevel.INFO, "Test message to file");

    // assert
    List<String> lines = Files.readAllLines(Path.of(tempFile.getAbsolutePath()));
    assertEquals(1, lines.size());
    assertTrue(lines.get(0).contains("Test message to file"));
  }

  @Test
  public void test_write_withInvalidFilePath_shouldHandleIOException() throws IOException {
    // arrange
    FileLogWriter writer = new FileLogWriter();
    LoggerConfiguration.setFileLoggingPath("invalid/path/to/file.log");

    // act
    writer.write(LogLevel.INFO, "Test message to file");
  }
}
