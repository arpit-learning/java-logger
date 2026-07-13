package dev.arpit.pm1.logger.core;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LoggerFactoryTest {

  @Test
  public void test_objectCreation() {
    // act
    LoggerFactory loggerFactory = new LoggerFactory();

    // assert
    assertNotNull(loggerFactory);
  }

  @Test
  public void test_getLoggerByName_withValidName_returnsLogger() {
    // act
    ILogger logger = LoggerFactory.getLogger("TestLogger");

    // assert
    assertNotNull(logger);
    assertInstanceOf(Logger.class, logger);
  }

  @Test
  public void test_getLoggerByName_withNullName_returnsLogger() {
    // act & assert
    assertThrows(NullPointerException.class, () -> LoggerFactory.getLogger((String) null));
  }

  @Test
  public void test_getLoggerByClass_withNullClass_returnsLogger() {
    // act
    ILogger logger = LoggerFactory.getLogger(LoggerFactoryTest.class);

    // assert
    assertNotNull(logger);
    assertInstanceOf(Logger.class, logger);
  }

  @Test
  public void test_getLoggerByClass_withValidClass_returnsLogger() {
    // act & assert
    assertThrows(NullPointerException.class, () -> LoggerFactory.getLogger((Class<?>) null));
  }
}
