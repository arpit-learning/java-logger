package dev.arpit.learning.logger.writers;

import static org.junit.jupiter.api.Assertions.*;

import dev.arpit.learning.logger.models.LogLevel;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SystemOutLogWriterTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private SystemOutLogWriter writer;

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outContent));
  }

  @AfterEach
  public void restore() {
    System.setOut(originalOut);
  }

  @Test
  public void test_objectCreation() {
    // act
    writer = new SystemOutLogWriter();

    // assert
    assertNotNull(writer);
  }

  @Test
  public void test_write_withValidLogLevelAndValidMessage_shouldWriteMessageToStandardOut() {
    // arrange
    writer = new SystemOutLogWriter();

    // act
    writer.write(LogLevel.INFO, "Test message to standard out");

    // assert
    assertTrue(outContent.toString().contains("Test message to standard out"));
  }

  @Test
  public void test_write_withNullLogLevelAndValidMessage_shouldThrowNPE() {
    // arrange
    SystemOutLogWriter writer = new SystemOutLogWriter();

    // act & assert
    assertThrows(
        NullPointerException.class, () -> writer.write(null, "Test message to standard out"));
  }

  @Test
  public void test_write_withValidLogLevelAndNullMessage_shouldThrowNPE() {
    // arrange
    SystemOutLogWriter writer = new SystemOutLogWriter();

    // act & assert
    assertThrows(NullPointerException.class, () -> writer.write(LogLevel.INFO, null));
  }
}
