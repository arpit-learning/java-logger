package dev.arpit.learning.logger.levels;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

public class DebugLoggerTest {
  private DebugLogger debugLogger;
  private Logger logger;

  @Test
  public void test_objectCreation_withValidFields_shouldCreateObject() {
    // arrange
    logger = mock(Logger.class);

    // act
    debugLogger = new DebugLogger(logger, new Gson());

    // assert
    assertNotNull(debugLogger);
  }

  @BeforeEach
  public void setUp() {
    // arrange
    logger = mock(Logger.class);
  }

  @Test
  public void test_objectCreation_withNullLoggerAndValidGson_shouldThrowNPE() {
    // act & assert
    assertThrows(NullPointerException.class, () -> debugLogger = new DebugLogger(null, new Gson()));
  }

  @Test
  public void test_objectCreation_withValidLoggerAndNullGson_shouldThrowNPE() {
    // act
    assertThrows(NullPointerException.class, () -> debugLogger = new DebugLogger(logger, null));
  }

  @Test
  public void test_log() {
    // arrange
    debugLogger = new DebugLogger(logger, new Gson());

    // act
    debugLogger.log();
  }

  @Test
  public void test_toString() {
    // arrange
    debugLogger = new DebugLogger(logger, new Gson());

    // act
    String result = debugLogger.toString();

    // assert
    assertNotNull(result);
  }
}
