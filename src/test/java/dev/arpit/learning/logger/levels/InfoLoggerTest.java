package dev.arpit.learning.logger.levels;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

public class InfoLoggerTest {
  private InfoLogger infoLogger;
  private Logger logger;

  @Test
  public void test_objectCreation_withValidFields_shouldCreateObject() {
    // arrange
    logger = mock(Logger.class);

    // act
    infoLogger = new InfoLogger(logger, new Gson());

    // assert
    assertNotNull(infoLogger);
  }

  @BeforeEach
  public void setUp() {
    // arrange
    logger = mock(Logger.class);
  }

  @Test
  public void test_objectCreation_withNullLoggerAndValidGson_shouldThrowNPE() {
    // act & assert
    assertThrows(NullPointerException.class, () -> infoLogger = new InfoLogger(null, new Gson()));
  }

  @Test
  public void test_objectCreation_withValidLoggerAndNullGson_shouldThrowNPE() {
    // act
    assertThrows(NullPointerException.class, () -> infoLogger = new InfoLogger(logger, null));
  }

  @Test
  public void test_log() {
    // arrange
    infoLogger = new InfoLogger(logger, new Gson());

    // act
    infoLogger.log();
  }

  @Test
  public void test_toString() {
    // arrange
    infoLogger = new InfoLogger(logger, new Gson());

    // act
    String result = infoLogger.toString();

    // assert
    assertNotNull(result);
  }
}
