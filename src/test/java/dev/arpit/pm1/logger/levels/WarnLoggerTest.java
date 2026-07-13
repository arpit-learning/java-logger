package dev.arpit.pm1.logger.levels;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

public class WarnLoggerTest {
  private WarnLogger warnLogger;
  private Logger logger;

  @Test
  public void test_objectCreation_withValidFields_shouldCreateObject() {
    // arrange
    logger = mock(Logger.class);

    // act
    warnLogger = new WarnLogger(logger, new Gson());

    // assert
    assertNotNull(warnLogger);
  }

  @BeforeEach
  public void setUp() {
    // arrange
    logger = mock(Logger.class);
  }

  @Test
  public void test_objectCreation_withNullLoggerAndValidGson_shouldThrowNPE() {
    // act & assert
    assertThrows(NullPointerException.class, () -> warnLogger = new WarnLogger(null, new Gson()));
  }

  @Test
  public void test_objectCreation_withValidLoggerAndNullGson_shouldThrowNPE() {
    // act
    assertThrows(NullPointerException.class, () -> warnLogger = new WarnLogger(logger, null));
  }

  @Test
  public void test_log() {
    // arrange
    warnLogger = new WarnLogger(logger, new Gson());

    // act
    warnLogger.log();
  }

  @Test
  public void test_toString() {
    // arrange
    warnLogger = new WarnLogger(logger, new Gson());

    // act
    String result = warnLogger.toString();

    // assert
    assertNotNull(result);
  }
}
