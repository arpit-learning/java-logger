package dev.arpit.learning.logger.levels;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

public class TraceLoggerTest {
  private TraceLogger traceLogger;
  private Logger logger;

  @Test
  public void test_objectCreation_withValidFields_shouldCreateObject() {
    // arrange
    logger = mock(Logger.class);

    // act
    traceLogger = new TraceLogger(logger, new Gson());

    // assert
    assertNotNull(traceLogger);
  }

  @BeforeEach
  public void setUp() {
    // arrange
    logger = mock(Logger.class);
  }

  @Test
  public void test_objectCreation_withNullLoggerAndValidGson_shouldThrowNPE() {
    // act & assert
    assertThrows(NullPointerException.class, () -> traceLogger = new TraceLogger(null, new Gson()));
  }

  @Test
  public void test_objectCreation_withValidLoggerAndNullGson_shouldThrowNPE() {
    // act
    assertThrows(NullPointerException.class, () -> traceLogger = new TraceLogger(logger, null));
  }

  @Test
  public void test_log() {
    // arrange
    traceLogger = new TraceLogger(logger, new Gson());

    // act
    traceLogger.log();
  }

  @Test
  public void test_toString() {
    // arrange
    traceLogger = new TraceLogger(logger, new Gson());

    // act
    String result = traceLogger.toString();

    // assert
    assertNotNull(result);
  }
}
