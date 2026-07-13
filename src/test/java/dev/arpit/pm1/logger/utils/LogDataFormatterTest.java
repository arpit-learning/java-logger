package dev.arpit.pm1.logger.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LogDataFormatterTest {
  @Test
  public void test_objectCreation() {
    // act
    LogDataFormatter logDataFormatter = new LogDataFormatter();

    // assert
    assertNotNull(logDataFormatter);
  }

  @Test
  public void test_formatArgs_withValidArgs_shouldReturnFormattedString() {
    // arrange
    Object[] args = new Object[] {"Hello", "World", 123};

    // act
    String result = LogDataFormatter.formatArgs(args);

    // assert
    assertEquals("Hello, World, 123", result);
  }

  @Test
  public void test_formatArgs_withNullArgs_shouldThrowNPE() {
    // act & assert
    assertThrows(NullPointerException.class, () -> LogDataFormatter.formatArgs((Object[]) null));
  }

  @Test
  public void test_formatArgsWithEmptyArgs_shouldReturnEmptyString() {
    // act
    String result = LogDataFormatter.formatArgs(new Object[] {});

    // assert
    assertEquals("", result);
  }
}
