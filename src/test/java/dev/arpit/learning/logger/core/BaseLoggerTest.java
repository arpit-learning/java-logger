package dev.arpit.learning.logger.core;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import dev.arpit.learning.logger.levels.*;
import dev.arpit.pm1.logger.levels.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

public class BaseLoggerTest {
  private Logger logger;
  private BaseLogger baseLogger;
  private NoopLogger noopLogger;

  @Test
  public void test_creationWithValidLogger() {
    // arrange
    logger = mock(Logger.class);

    // act
    baseLogger = new BaseLogger(logger);

    // assert
    assertNotNull(baseLogger);
  }

  @Test
  public void test_creationWithNullLogger() {
    // act & assert
    assertThrows(NullPointerException.class, () -> baseLogger = new BaseLogger(logger));
  }

  @Test
  public void test_infoMethod_whenInfoLoggerIsEnabled_expectsToReturnInfoLogger() {
    // arrange
    logger = mock(Logger.class);
    noopLogger = mock(NoopLogger.class);
    baseLogger = new BaseLogger(logger);
    when(logger.isInfoEnabled()).thenReturn(true);

    // act
    IJsonLogger jsonLogger = baseLogger.info();

    // assert
    assertNotNull(jsonLogger);
    assertEquals(InfoLogger.class, jsonLogger.getClass());
  }

  @Test
  public void test_infoMethod_whenInfoLoggerIsDisabled_expectsToReturnNoopLogger() {
    // arrange
    logger = mock(Logger.class);
    noopLogger = mock(NoopLogger.class);
    baseLogger = new BaseLogger(logger);
    when(logger.isInfoEnabled()).thenReturn(false);

    // act
    IJsonLogger jsonLogger = baseLogger.info();

    // assert
    assertNotNull(jsonLogger);
    assertEquals(NoopLogger.class, jsonLogger.getClass());
  }

  @Test
  public void test_debugMethod_whenDebugLoggerIsEnabled_expectsToReturnDebugLogger() {
    // arrange
    logger = mock(Logger.class);
    noopLogger = mock(NoopLogger.class);
    baseLogger = new BaseLogger(logger);
    when(logger.isDebugEnabled()).thenReturn(true);

    // act
    IJsonLogger jsonLogger = baseLogger.debug();

    // assert
    assertNotNull(jsonLogger);
    assertEquals(DebugLogger.class, jsonLogger.getClass());
  }

  @Test
  public void test_debugMethod_whenDebugLoggerIsDisabled_expectsToReturnNoopLogger() {
    // arrange
    logger = mock(Logger.class);
    noopLogger = mock(NoopLogger.class);
    baseLogger = new BaseLogger(logger);
    when(logger.isDebugEnabled()).thenReturn(false);

    // act
    IJsonLogger jsonLogger = baseLogger.debug();

    // assert
    assertNotNull(jsonLogger);
    assertEquals(NoopLogger.class, jsonLogger.getClass());
  }

  @Test
  public void test_errorMethod_whenErrorLoggerIsEnabled_expectsToReturnErrorLogger() {
    // arrange
    logger = mock(Logger.class);
    noopLogger = mock(NoopLogger.class);
    baseLogger = new BaseLogger(logger);
    when(logger.isErrorEnabled()).thenReturn(true);

    // act
    IJsonLogger jsonLogger = baseLogger.error();

    // assert
    assertNotNull(jsonLogger);
    assertEquals(ErrorLogger.class, jsonLogger.getClass());
  }

  @Test
  public void test_errorMethod_whenErrorLoggerIsDisabled_expectsToReturnNoopLogger() {
    // arrange
    logger = mock(Logger.class);
    noopLogger = mock(NoopLogger.class);
    baseLogger = new BaseLogger(logger);
    when(logger.isErrorEnabled()).thenReturn(false);

    // act
    IJsonLogger jsonLogger = baseLogger.error();

    // assert
    assertNotNull(jsonLogger);
    assertEquals(NoopLogger.class, jsonLogger.getClass());
  }

  @Test
  public void test_warnMethod_whenWarnLoggerIsEnabled_expectsToReturnWarnLogger() {
    // arrange
    logger = mock(Logger.class);
    noopLogger = mock(NoopLogger.class);
    baseLogger = new BaseLogger(logger);
    when(logger.isWarnEnabled()).thenReturn(true);

    // act
    IJsonLogger jsonLogger = baseLogger.warn();

    // assert
    assertNotNull(jsonLogger);
    assertEquals(WarnLogger.class, jsonLogger.getClass());
  }

  @Test
  public void test_warnMethod_whenWarnLoggerIsDisabled_expectsToReturnNoopLogger() {
    // arrange
    logger = mock(Logger.class);
    noopLogger = mock(NoopLogger.class);
    baseLogger = new BaseLogger(logger);
    when(logger.isWarnEnabled()).thenReturn(false);

    // act
    IJsonLogger jsonLogger = baseLogger.warn();

    // assert
    assertNotNull(jsonLogger);
    assertEquals(NoopLogger.class, jsonLogger.getClass());
  }

  @Test
  public void test_traceMethod_whenTraceLoggerIsEnabled_expectsToReturnTraceLogger() {
    // arrange
    logger = mock(Logger.class);
    noopLogger = mock(NoopLogger.class);
    baseLogger = new BaseLogger(logger);
    when(logger.isTraceEnabled()).thenReturn(true);

    // act
    IJsonLogger jsonLogger = baseLogger.trace();

    // assert
    assertNotNull(jsonLogger);
    assertEquals(TraceLogger.class, jsonLogger.getClass());
  }

  @Test
  public void test_traceMethod_whenTraceLoggerIsDisabled_expectsToReturnNoopLogger() {
    // arrange
    logger = mock(Logger.class);
    noopLogger = mock(NoopLogger.class);
    baseLogger = new BaseLogger(logger);
    when(logger.isTraceEnabled()).thenReturn(false);

    // act
    IJsonLogger jsonLogger = baseLogger.trace();

    // assert
    assertNotNull(jsonLogger);
    assertEquals(NoopLogger.class, jsonLogger.getClass());
  }

  @Test
  public void test_wtfMethod_whenErrorLoggerIsEnabled_expectsToReturnWtfLogger() {
    // arrange
    logger = mock(Logger.class);
    noopLogger = mock(NoopLogger.class);
    baseLogger = new BaseLogger(logger);
    when(logger.isErrorEnabled()).thenReturn(true);

    // act
    IJsonLogger jsonLogger = baseLogger.wtf();

    // arrange
    assertNotNull(jsonLogger);
    assertEquals(WtfLogger.class, jsonLogger.getClass());
  }

  @Test
  public void test_wtfMethod_whenErrorLoggerIsDisabled_expectsToReturnNoopLogger() {
    // arrange
    logger = mock(Logger.class);
    noopLogger = mock(NoopLogger.class);
    baseLogger = new BaseLogger(logger);
    when(logger.isErrorEnabled()).thenReturn(false);

    // act
    IJsonLogger jsonLogger = baseLogger.wtf();

    // assert
    assertNotNull(jsonLogger);
    assertEquals(NoopLogger.class, jsonLogger.getClass());
  }
}
