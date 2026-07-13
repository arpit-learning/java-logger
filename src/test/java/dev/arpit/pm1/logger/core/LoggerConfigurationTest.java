package dev.arpit.pm1.logger.core;

import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.arpit.pm1.logger.enrichers.ILogEnricher;
import dev.arpit.pm1.logger.enrichers.ThreadNameEnricher;
import dev.arpit.pm1.logger.levels.*;
import dev.arpit.pm1.logger.models.LogLevel;
import dev.arpit.pm1.logger.serializers.GsonLogSerializer;
import dev.arpit.pm1.logger.serializers.ILogSerializer;
import dev.arpit.pm1.logger.serializers.PrettyLogSerializer;
import dev.arpit.pm1.logger.writers.CompositeLogWriter;
import dev.arpit.pm1.logger.writers.ILogWriter;
import java.util.List;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerConfigurationTest {

  @Test
  public void test_singletonInstance() {
    // act
    LoggerConfiguration config1 = LoggerConfiguration.getInstance();
    LoggerConfiguration config2 = LoggerConfiguration.getInstance();

    // assert
    assertSame(config1, config2);
  }

  @Test
  public void testProperties() {
    LoggerConfiguration.setComponentName("MyTestComponent");
    assertEquals("MyTestComponent", LoggerConfiguration.getComponentName());

    LoggerConfiguration.setPrettyPrintEnabled(true);
    assertTrue(LoggerConfiguration.isPrettyPrintEnabled());

    LoggerConfiguration.setSystemOutLoggingEnabled(true);
    assertTrue(LoggerConfiguration.isSystemOutLoggingEnabled());

    LoggerConfiguration.setFileLoggingEnabled(true);
    assertTrue(LoggerConfiguration.isFileLoggingEnabled());

    LoggerConfiguration.setFileLoggingPath("/tmp/test.log");
    assertEquals("/tmp/test.log", LoggerConfiguration.getFileLoggingPath());
  }

  @Test
  public void test_addExcludedClasses_withValidClassName() {
    // arrange
    LoggerConfiguration instance = LoggerConfiguration.getInstance();

    // act
    instance.addExcludedClass("dev.arpit.CustomExcludedClass");

    // assert
    assertTrue(instance.getExcludedClasses().contains("dev.arpit.CustomExcludedClass"));
  }

  @Test
  public void test_addExcludedClasses_withNullClassName() {
    // arrange
    LoggerConfiguration instance = LoggerConfiguration.getInstance();

    // act & assert
    assertThrows(NullPointerException.class, () -> instance.addExcludedClass(null));
  }

  @Test
  public void test_addEnricher_withAddingNewValidEnricher_shouldAddItToTheList() {
    // arrange
    LoggerConfiguration config = LoggerConfiguration.getInstance();
    List<ILogEnricher> enrichers = config.getEnrichers();
    int initialSize = enrichers.size();

    // act
    ILogEnricher dummyEnricher = new ThreadNameEnricher();
    config.addEnricher(dummyEnricher);

    // assert
    assertEquals(initialSize + 1, config.getEnrichers().size());
    assertTrue(config.getEnrichers().contains(dummyEnricher));
  }

  @Test
  public void test_addEnricher_withAddingNewInvalidEnricher_shouldThrowNPE() {
    // arrange
    LoggerConfiguration config = LoggerConfiguration.getInstance();
    List<ILogEnricher> enrichers = config.getEnrichers();
    int initialSize = enrichers.size();

    // act & assert
    assertThrows(NullPointerException.class, () -> config.addEnricher(null));
    assertEquals(initialSize, config.getEnrichers().size());
  }

  @Test
  public void test_clearEnrichers_shouldClearEnrichersList() {
    // arrange
    LoggerConfiguration config = LoggerConfiguration.getInstance();
    List<ILogEnricher> enrichers = config.getEnrichers();
    int initialSize = enrichers.size();

    // assert
    assertTrue(initialSize > 0);

    // act
    config.clearEnrichers();

    // assert
    assertEquals(0, enrichers.size());
  }

  @Test
  public void
      test_createSerializer_withValidGsonAndPrettyPrintDisabled_shouldCreateGsonLogSerializer() {
    // arrange
    LoggerConfiguration.setPrettyPrintEnabled(false);
    LoggerConfiguration instance = LoggerConfiguration.getInstance();
    Gson gson = (new GsonBuilder()).create();

    // act
    ILogSerializer logSerializer = instance.createSerializer(gson);

    // assert
    assertNotNull(logSerializer);
    assertEquals(GsonLogSerializer.class, logSerializer.getClass());
  }

  @Test
  public void
      test_createSerializer_withValidGsonAndPrettyPrintEnabled_shouldCreatePrettyLogSerializer() {
    // arrange
    LoggerConfiguration.setPrettyPrintEnabled(true);
    LoggerConfiguration instance = LoggerConfiguration.getInstance();
    Gson gson = (new GsonBuilder()).create();

    // act
    ILogSerializer logSerializer = instance.createSerializer(gson);

    // assert
    assertNotNull(logSerializer);
    assertEquals(PrettyLogSerializer.class, logSerializer.getClass());
  }

  @Test
  public void test_createSerializer_withNullGsonAndPrettyPrintDisabled_shouldThrowNPE() {
    // arrange
    LoggerConfiguration.setPrettyPrintEnabled(false);
    LoggerConfiguration instance = LoggerConfiguration.getInstance();

    // act & assert
    assertThrows(NullPointerException.class, () -> instance.createSerializer(null));
  }

  @Test
  public void test_createSerializer_withNullGsonAndPrettyPrintEnabled_shouldThrowNPE() {
    // arrange
    LoggerConfiguration.setPrettyPrintEnabled(true);
    LoggerConfiguration instance = LoggerConfiguration.getInstance();

    // act & assert
    assertThrows(NullPointerException.class, () -> instance.createSerializer(null));
  }

  @Test
  public void test_createWriter_withNullLogger_shouldThrowNPE() {
    // arrange
    LoggerConfiguration.setSystemOutLoggingEnabled(false);
    LoggerConfiguration.setFileLoggingEnabled(false);
    LoggerConfiguration instance = LoggerConfiguration.getInstance();

    // act & assert
    assertThrows(NullPointerException.class, () -> instance.createWriter(null));
  }

  @Test
  public void
      test_createWriter_withValidLoggerAndSystemOutLogWriterDisabledAndFileLoggingDisabled_returnCompositeWriter() {
    // arrange
    LoggerConfiguration.setSystemOutLoggingEnabled(false);
    LoggerConfiguration.setFileLoggingEnabled(false);
    LoggerConfiguration instance = LoggerConfiguration.getInstance();
    Logger logger = LoggerFactory.getLogger("test");

    // act
    ILogWriter logWriter = instance.createWriter(logger);

    // assert
    assertNotNull(logWriter);
    assertEquals(CompositeLogWriter.class, logWriter.getClass());
  }

  @Test
  public void
      test_createWriter_withValidLoggerAndSystemOutLogWriterEnabledAndFileLoggingDisabled_returnCompositeWriter() {
    // arrange
    LoggerConfiguration.setSystemOutLoggingEnabled(true);
    LoggerConfiguration.setFileLoggingEnabled(false);
    LoggerConfiguration instance = LoggerConfiguration.getInstance();
    Logger logger = LoggerFactory.getLogger("test");

    // act
    ILogWriter logWriter = instance.createWriter(logger);

    // assert
    assertNotNull(logWriter);
    assertEquals(CompositeLogWriter.class, logWriter.getClass());
  }

  @Test
  public void
      test_createWriter_withValidLoggerAndSystemOutLogWriterDisabledAndFileLoggingEnabledAndInvalidFileLoggingPath_returnCompositeWriter() {
    // arrange
    LoggerConfiguration.setSystemOutLoggingEnabled(false);
    LoggerConfiguration.setFileLoggingEnabled(true);
    LoggerConfiguration.setFileLoggingPath("");
    LoggerConfiguration instance = LoggerConfiguration.getInstance();
    Logger logger = LoggerFactory.getLogger("test");

    // act
    ILogWriter logWriter = instance.createWriter(logger);

    // assert
    assertNotNull(logWriter);
    assertEquals(CompositeLogWriter.class, logWriter.getClass());
  }

  @Test
  public void
      test_createWriter_withValidLoggerAndSystemOutLogWriterDisabledAndFileLoggingEnabledAndValidFileLoggingPath_returnCompositeWriter() {
    // arrange
    LoggerConfiguration.setSystemOutLoggingEnabled(false);
    LoggerConfiguration.setFileLoggingEnabled(true);
    LoggerConfiguration.setFileLoggingPath("temp.log");
    LoggerConfiguration instance = LoggerConfiguration.getInstance();
    Logger logger = LoggerFactory.getLogger("test");

    // act
    ILogWriter logWriter = instance.createWriter(logger);

    // assert
    assertNotNull(logWriter);
    assertEquals(CompositeLogWriter.class, logWriter.getClass());
  }

  @Test
  public void
      test_createWriter_withValidLoggerAndSystemOutLogWriterEnabledAndFileLoggingEnabledAndInvalidFileLoggingPath_returnCompositeWriter() {
    // arrange
    LoggerConfiguration.setSystemOutLoggingEnabled(true);
    LoggerConfiguration.setFileLoggingEnabled(true);
    LoggerConfiguration.setFileLoggingPath("");
    LoggerConfiguration instance = LoggerConfiguration.getInstance();
    Logger logger = LoggerFactory.getLogger("test");

    // act
    ILogWriter logWriter = instance.createWriter(logger);

    // assert
    assertNotNull(logWriter);
    assertEquals(CompositeLogWriter.class, logWriter.getClass());
  }

  @Test
  public void
      test_createWriter_withValidLoggerAndSystemOutLogWriterEnabledAndFileLoggingEnabledAndValidFileLoggingPath_returnCompositeWriter() {
    // arrange
    LoggerConfiguration.setSystemOutLoggingEnabled(true);
    LoggerConfiguration.setFileLoggingEnabled(true);
    LoggerConfiguration.setFileLoggingPath("temp.log");
    LoggerConfiguration instance = LoggerConfiguration.getInstance();
    Logger logger = LoggerFactory.getLogger("test");

    // act
    ILogWriter logWriter = instance.createWriter(logger);

    // assert
    assertNotNull(logWriter);
    assertEquals(CompositeLogWriter.class, logWriter.getClass());
  }

  @Test
  public void test_createLogger_withNullLevel_shouldThrowNPE() {
    // arrange
    LoggerConfiguration instance = LoggerConfiguration.getInstance();
    Logger logger = LoggerFactory.getLogger("info");
    Gson gson = (new GsonBuilder()).create();

    // act & assert
    assertThrows(NullPointerException.class, () -> instance.createLogger(null, logger, gson));
  }

  @Test
  public void test_createLogger_withNullLogger_shouldThrowNPE() {
    // arrange
    LoggerConfiguration instance = LoggerConfiguration.getInstance();
    Gson gson = (new GsonBuilder()).create();

    // act & assert
    assertThrows(
        NullPointerException.class, () -> instance.createLogger(LogLevel.INFO, null, gson));
  }

  @Test
  public void test_createLogger_withGson_shouldThrowNPE() {
    // arrange
    LoggerConfiguration instance = LoggerConfiguration.getInstance();
    Logger logger = LoggerFactory.getLogger("info");

    // act & assert
    assertThrows(
        NullPointerException.class, () -> instance.createLogger(LogLevel.INFO, logger, null));
  }

  @Test
  public void test_createLogger_withLogLevelAsINFO_shouldReturnInfoLogger() {
    // arrange
    LoggerConfiguration instance = LoggerConfiguration.getInstance();
    Logger logger = LoggerFactory.getLogger("info");
    Gson gson = (new GsonBuilder()).create();

    // act
    IJsonLogger jsonLogger = instance.createLogger(LogLevel.INFO, logger, gson);

    // assert
    assertNotNull(jsonLogger);
    assertEquals(InfoLogger.class, jsonLogger.getClass());
  }

  @Test
  public void test_createLogger_withLogLevelAsDEBUG_shouldReturnDebugLogger() {
    // arrange
    LoggerConfiguration instance = LoggerConfiguration.getInstance();
    Logger logger = LoggerFactory.getLogger("debug");
    Gson gson = (new GsonBuilder()).create();

    // act
    IJsonLogger jsonLogger = instance.createLogger(LogLevel.DEBUG, logger, gson);

    // assert
    assertNotNull(jsonLogger);
    assertEquals(DebugLogger.class, jsonLogger.getClass());
  }

  @Test
  public void test_createLogger_withLogLevelAsTRACE_shouldReturnTraceLogger() {
    // arrange
    LoggerConfiguration instance = LoggerConfiguration.getInstance();
    Logger logger = LoggerFactory.getLogger("trace");
    Gson gson = (new GsonBuilder()).create();

    // act
    IJsonLogger jsonLogger = instance.createLogger(LogLevel.TRACE, logger, gson);

    // assert
    assertNotNull(jsonLogger);
    assertEquals(TraceLogger.class, jsonLogger.getClass());
  }

  @Test
  public void test_createLogger_withLogLevelAsWARN_shouldReturnWarnLogger() {
    // arrange
    LoggerConfiguration instance = LoggerConfiguration.getInstance();
    Logger logger = LoggerFactory.getLogger("warn");
    Gson gson = (new GsonBuilder()).create();

    // act
    IJsonLogger jsonLogger = instance.createLogger(LogLevel.WARN, logger, gson);

    // assert
    assertNotNull(jsonLogger);
    assertEquals(WarnLogger.class, jsonLogger.getClass());
  }

  @Test
  public void test_createLogger_withLogLevelAsERROR_shouldReturnErrorLogger() {
    // arrange
    LoggerConfiguration instance = LoggerConfiguration.getInstance();
    Logger logger = LoggerFactory.getLogger("error");
    Gson gson = (new GsonBuilder()).create();

    // act
    IJsonLogger jsonLogger = instance.createLogger(LogLevel.ERROR, logger, gson);

    // assert
    assertNotNull(jsonLogger);
    assertEquals(ErrorLogger.class, jsonLogger.getClass());
  }

  @Test
  public void test_createLogger_withLogLevelAsWTF_shouldReturnWtfLogger() {
    // arrange
    LoggerConfiguration instance = LoggerConfiguration.getInstance();
    Logger logger = LoggerFactory.getLogger("wtf");
    Gson gson = (new GsonBuilder()).create();

    // act
    IJsonLogger jsonLogger = instance.createLogger(LogLevel.WTF, logger, gson);

    // assert
    assertNotNull(jsonLogger);
    assertEquals(WtfLogger.class, jsonLogger.getClass());
  }

  @Test
  public void test_setDateFormat_withValidDateFormat_shouldUpdateDateFormatAndFormatter() {
    // arrange
    FastDateFormat dateFormat = FastDateFormat.getInstance("");

    // act
    LoggerConfiguration.setDateFormat("");

    // assert
    assertEquals("", LoggerConfiguration.getDateFormat());
    FastDateFormat formatter = LoggerConfiguration.getFormatter();
    System.out.println(formatter);
    assertEquals(dateFormat.getLocale(), formatter.getLocale());
    assertEquals(dateFormat.getPattern(), formatter.getPattern());
    assertEquals(dateFormat.getTimeZone(), formatter.getTimeZone());
  }

  @Test
  public void test_setDateFormat_withNullDateFormat_shouldThrowNPE() {
    // act & assert
    assertThrows(NullPointerException.class, () -> LoggerConfiguration.setDateFormat(null));
  }
}
