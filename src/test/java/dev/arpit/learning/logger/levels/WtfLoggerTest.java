package dev.arpit.learning.logger.levels;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

public class WtfLoggerTest {
  private WtfLogger wtfLogger;
  private Logger logger;

  @Test
  public void test_objectCreation_withValidFields_shouldCreateObject() {
    // arrange
    logger = mock(Logger.class);

    // act
    wtfLogger = new WtfLogger(logger, new Gson());

    // assert
    assertNotNull(wtfLogger);
  }

  @BeforeEach
  public void setUp() {
    // arrange
    logger = mock(Logger.class);
  }

  @Test
  public void test_objectCreation_withNullLoggerAndValidGson_shouldThrowNPE() {
    // act & assert
    assertThrows(NullPointerException.class, () -> wtfLogger = new WtfLogger(null, new Gson()));
  }

  @Test
  public void test_objectCreation_withValidLoggerAndNullGson_shouldThrowNPE() {
    // act
    assertThrows(NullPointerException.class, () -> wtfLogger = new WtfLogger(logger, null));
  }

  @Test
  public void test_log() {
    // arrange
    wtfLogger = new WtfLogger(logger, new Gson());

    // act
    wtfLogger.log();
  }

  @Test
  public void test_toString() {
    // arrange
    wtfLogger = new WtfLogger(logger, new Gson());

    // act
    String result = wtfLogger.toString();

    // assert
    assertNotNull(result);
  }
}
