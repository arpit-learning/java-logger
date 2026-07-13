package dev.arpit.pm1.logger.writers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import dev.arpit.pm1.logger.models.LogLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

public class Slf4jLogWriterTest {
  private Logger mockLogger;
  private Slf4jLogWriter writer;

  @BeforeEach
  public void setUp() {
    mockLogger = mock(Logger.class);
  }

  @Test
  public void test_objectCreation_withValidLogger_shouldCreateValidObject() {
    // act
    writer = new Slf4jLogWriter(mockLogger);

    // assert
    assertNotNull(writer);
  }

  @Test
  public void test_objectCreation_withNullLogger_shouldThrowNPE() {
    // act & assert
    assertThrows(NullPointerException.class, () -> new Slf4jLogWriter(null));
  }

  @Test
  public void test_write_withNullLogLevelAndValidMessage_shouldThrowNPE() {
    // arrange
    writer = new Slf4jLogWriter(mockLogger);

    // act & assert
    assertThrows(NullPointerException.class, () -> writer.write(null, "Debug message"));
  }

  @Test
  public void test_write_withDebugLogLevelAndNllMessage_shouldThrowNPE() {
    // arrange
    writer = new Slf4jLogWriter(mockLogger);

    // act & assert
    assertThrows(NullPointerException.class, () -> writer.write(LogLevel.DEBUG, null));
  }

  @Test
  public void test_write_withDebugLogLevelAndValidMessage_shouldLogDebug() {
    // arrange
    writer = new Slf4jLogWriter(mockLogger);

    // act
    writer.write(LogLevel.DEBUG, "Debug message");

    // assert
    verify(mockLogger).debug("Debug message");
  }

  @Test
  public void test_write_withWarnLogLevelAndValidMessage_shouldLogWarn() {
    // arrange
    writer = new Slf4jLogWriter(mockLogger);

    // act
    writer.write(LogLevel.WARN, "Warn message");

    // assert
    verify(mockLogger).warn("Warn message");
  }

  @Test
  public void test_write_withErrorLogLevelAndValidMessage_shouldLogError() {
    // arrange
    writer = new Slf4jLogWriter(mockLogger);

    // act
    writer.write(LogLevel.ERROR, "Error message");

    // assert
    verify(mockLogger).error("Error message");
  }

  @Test
  public void test_write_withTraceLogLevelAndValidMessage_shouldLogTrace() {
    // arrange
    writer = new Slf4jLogWriter(mockLogger);

    // act
    writer.write(LogLevel.TRACE, "Trace message");

    // assert
    verify(mockLogger).trace("Trace message");
  }

  @Test
  public void test_write_withWtfLogLevelAndValidMessage_shouldLogWtf() {
    // arrange
    writer = new Slf4jLogWriter(mockLogger);

    // act
    writer.write(LogLevel.WTF, "Wtf message");

    // assert
    verify(mockLogger).info("Wtf message"); // WTF maps to default -> info
  }

  @Test
  public void test_write_withInfoLogLevelAndValidMessage_shouldLogInfo() {
    // arrange
    writer = new Slf4jLogWriter(mockLogger);

    // act
    writer.write(LogLevel.INFO, "Info message");

    // assert
    verify(mockLogger).info("Info message");
  }
}
