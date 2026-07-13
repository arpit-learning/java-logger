package dev.arpit.pm1.logger.levels;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

public class ErrorLoggerTest {
  private ErrorLogger errorLogger;
  private Logger logger;

  @Test
  public void test_objectCreation_withValidFields_shouldCreateObject() {
    // arrange
    logger = mock(Logger.class);

    // act
    errorLogger = new ErrorLogger(logger, new Gson());

    // assert
    assertNotNull(errorLogger);
  }

  @BeforeEach
  public void setUp() {
    // arrange
    logger = mock(Logger.class);
  }

  @Test
  public void test_objectCreation_withNullLoggerAndValidGson_shouldThrowNPE() {
    // act & assert
    assertThrows(NullPointerException.class, () -> errorLogger = new ErrorLogger(null, new Gson()));
  }

  @Test
  public void test_objectCreation_withValidLoggerAndNullGson_shouldThrowNPE() {
    // act
    assertThrows(NullPointerException.class, () -> errorLogger = new ErrorLogger(logger, null));
  }

  @Test
  public void test_log() {
    // arrange
    errorLogger = new ErrorLogger(logger, new Gson());

    // act
    errorLogger.log();
  }

  @Test
  public void test_toString() {
    // arrange
    errorLogger = new ErrorLogger(logger, new Gson());

    // act
    String result = errorLogger.toString();

    // assert
    assertNotNull(result);
  }
}
