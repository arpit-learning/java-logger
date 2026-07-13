package dev.arpit.pm1.logger.levels;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import dev.arpit.pm1.logger.models.ILoggerEnabledException;
import dev.arpit.pm1.logger.models.IMessageConstant;
import dev.arpit.pm1.logger.models.LogLevel;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

public class AbstractJsonLoggerTest {
  private static class TestJsonLogger extends AbstractJsonLogger {
    public TestJsonLogger(LogLevel logLevel, Logger logger, Gson gson) {
      super(logLevel, logger, gson);
    }

    @Override
    public void log() {
      executePipeline();
    }
  }

  private TestJsonLogger testLogger;
  private Logger logger;

  @BeforeEach
  public void setUp() {
    logger = mock(Logger.class);
  }

  @Test
  public void
      test_objectCreation_withValidLogLevelAndValidLoggerAndValidGson_shouldCreateValidObject() {
    // arrange

    // act
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // assert
    assertNotNull(testLogger);
  }

  @Test
  public void test_objectCreation_withNullLogLevelAndValidLoggerAndValidGson_shouldThrowNPE() {
    // arrange

    // act & assert
    assertThrows(NullPointerException.class, () -> new TestJsonLogger(null, logger, new Gson()));
  }

  @Test
  public void test_objectCreation_withValidLogLevelAndNullLoggerAndValidGson_shouldThrowNPE() {
    // arrange

    // act & assert
    assertThrows(
        NullPointerException.class, () -> new TestJsonLogger(LogLevel.INFO, null, new Gson()));
  }

  @Test
  public void test_objectCreation_withValidLogLevelAndValidLoggerAndNullGson_shouldThrowNPE() {
    // arrange

    // act & assert
    assertThrows(NullPointerException.class, () -> new TestJsonLogger(LogLevel.INFO, logger, null));
  }

  @Test
  public void test_message_withInputAsString_shouldAddMessageToTheLogEventBuilder() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act
    testLogger.message("String message");

    // assert
    assertNotNull(testLogger.getLogEventBuilder());
  }

  @Test
  public void test_message_withNullInputAsString_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act & assert
    assertThrows(NullPointerException.class, () -> testLogger.message((String) null));
  }

  @Test
  public void test_message_withInputOfTypeIMessageConstant_shouldAddMessageToTheLogEventBuilder() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act
    testLogger.message(() -> "Test Message");

    // assert
    assertNotNull(testLogger.getLogEventBuilder());
  }

  @Test
  public void test_message_withNullInputOfTypeIMessageConstant_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act & assert
    assertThrows(NullPointerException.class, () -> testLogger.message((IMessageConstant) null));
  }

  @Test
  public void
      test_args_withValidStringMessageAndArgs_shouldAddMessageAndArgsToTheLogEventBuilder() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act
    testLogger.args("Message", "arg1", 2);

    // assert
    assertNotNull(testLogger.getLogEventBuilder());
  }

  @Test
  public void test_args_withNullStringMessageAndArgs_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act & assert
    assertThrows(NullPointerException.class, () -> testLogger.args((String) null, "arg1", 2));
  }

  @Test
  public void test_args_withValidStringMessageAndNullArgs_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act & assert
    assertThrows(NullPointerException.class, () -> testLogger.args("Message", (Object[]) null));
  }

  @Test
  public void
      test_args_withValidIMessageConstantMessageAndArgs_shouldAddMessageAndArgsToTheLogEventBuilder() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act
    testLogger.args(() -> "Message", "arg1", 2);

    // assert
    assertNotNull(testLogger.getLogEventBuilder());
  }

  @Test
  public void test_args_withNullIMessageConstantMessageAndArgs_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act & assert
    assertThrows(
        NullPointerException.class, () -> testLogger.args((IMessageConstant) null, "arg1", 2));
  }

  @Test
  public void test_args_withValidIMessageConstantMessageAndNullArgs_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act & assert
    assertThrows(NullPointerException.class, () -> testLogger.args(() -> "Message", (Object[]) null));
  }

  @Test
  public void test_map_withValidStringMessageAndMap_shouldAddMessageAndMapToTheLogEventBuilder() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());
    Map<String, Object> map = Map.of("key", "value");

    // act
    testLogger.map("Message", map);

    // assert
    assertNotNull(testLogger.getLogEventBuilder());
  }

  @Test
  public void test_map_withNullStringMessageAndValidMap_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());
    Map<String, Object> map = Map.of("key", "value");

    // act & assert
    assertThrows(NullPointerException.class, () -> testLogger.map((String) null, map));
  }

  @Test
  public void test_map_withValidStringMessageAndNullMap_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act & assert
    assertThrows(NullPointerException.class, () -> testLogger.map("Message", null));
  }

  @Test
  public void
      test_map_withValidIMessageConstantMessageAndMap_shouldAddMessageAndMapToTheLogEventBuilder() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());
    Map<String, Object> map = Map.of("key", "value");

    // act
    testLogger.map(() -> "Message", map);

    // assert
    assertNotNull(testLogger.getLogEventBuilder());
  }

  @Test
  public void test_map_withNullIMessageConstantMessageAndValidMap_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());
    Map<String, Object> map = Map.of("key", "value");

    // act & assert
    assertThrows(NullPointerException.class, () -> testLogger.map((IMessageConstant) null, map));
  }

  @Test
  public void test_map_withValidIMessageConstantMessageAndNullMap_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act & assert
    assertThrows(NullPointerException.class, () -> testLogger.map(() -> "Message", null));
  }

  @Test
  public void
      test_list_withValidStringMessageAndList_shouldAddMessageAndListToTheLogEventBuilder() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());
    List<Object> list = List.of("key", 2);

    // act
    testLogger.list("Message", list);

    // assert
    assertNotNull(testLogger.getLogEventBuilder());
  }

  @Test
  public void test_list_withNullStringMessageAndValidList_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());
    List<Object> list = List.of("key", 2);

    // act & assert
    assertThrows(NullPointerException.class, () -> testLogger.list((String) null, list));
  }

  @Test
  public void test_list_withValidStringMessageAndNullList_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act & assert
    assertThrows(NullPointerException.class, () -> testLogger.list("Message", null));
  }

  @Test
  public void
      test_list_withValidIMessageConstantMessageAndList_shouldAddMessageAndListToTheLogEventBuilder() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());
    List<Object> list = List.of("key", 2);

    // act
    testLogger.list(() -> "Message", list);

    // assert
    assertNotNull(testLogger.getLogEventBuilder());
  }

  @Test
  public void test_list_withNullIMessageConstantMessageAndValidList_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());
    List<Object> list = List.of("key", 2);

    // act & assert
    assertThrows(NullPointerException.class, () -> testLogger.list((IMessageConstant) null, list));
  }

  @Test
  public void test_list_withValidIMessageConstantMessageAndNullList_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act & assert
    assertThrows(NullPointerException.class, () -> testLogger.list(() -> "Message", null));
  }

  @Test
  public void
      test_json_withValidStringMessageAndJsonElement_shouldAddMessageAndJsonElementToTheLogEventBuilder() {
    // arrange
    Gson gson = new Gson();
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, gson);
    JsonElement jsonElement = gson.toJsonTree(List.of("key", 2));

    // act
    testLogger.json("Message", jsonElement);

    // assert
    assertNotNull(testLogger.getLogEventBuilder());
  }

  @Test
  public void test_json_withNullStringMessageAndValidJsonElement_shouldThrowNPE() {
    // arrange
    Gson gson = new Gson();
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, gson);
    JsonElement jsonElement = gson.toJsonTree(List.of("key", 2));

    // act & assert
    assertThrows(NullPointerException.class, () -> testLogger.json((String) null, jsonElement));
  }

  @Test
  public void test_json_withValidStringMessageAndNullJsonElement_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act & assert
    assertThrows(NullPointerException.class, () -> testLogger.json("Message", (JsonElement) null));
  }

  @Test
  public void
      test_json_withValidIMessageConstantMessageAndJsonElement_shouldAddMessageAndJsonElementToTheLogEventBuilder() {
    // arrange
    Gson gson = new Gson();
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, gson);
    JsonElement jsonElement = gson.toJsonTree(List.of("key", 2));

    // act
    testLogger.json(() -> "Message", jsonElement);

    // assert
    assertNotNull(testLogger.getLogEventBuilder());
  }

  @Test
  public void test_json_withNullIMessageConstantMessageAndValidJsonElement_shouldThrowNPE() {
    // arrange
    Gson gson = new Gson();
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, gson);
    JsonElement jsonElement = gson.toJsonTree(List.of("key", 2));

    // act & assert
    assertThrows(
        NullPointerException.class, () -> testLogger.json((IMessageConstant) null, jsonElement));
  }

  @Test
  public void test_json_withValidIMessageConstantMessageAndNullJsonElement_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act & assert
    assertThrows(
        NullPointerException.class, () -> testLogger.json(() -> "Message", (JsonElement) null));
  }

  @Test
  public void
      test_json_withValidStringMessageAndJsonString_shouldAddMessageAndJsonStringToTheLogEventBuilder() {
    // arrange
    Gson gson = new Gson();
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, gson);
    String jsonString = gson.toJson(List.of("key", 2));

    // act
    testLogger.json("Message", jsonString);

    // assert
    assertNotNull(testLogger.getLogEventBuilder());
  }

  @Test
  public void test_json_withNullStringMessageAndValidJsonString_shouldThrowNPE() {
    // arrange
    Gson gson = new Gson();
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, gson);
    String jsonString = gson.toJson(List.of("key", 2));

    // act & assert
    assertThrows(NullPointerException.class, () -> testLogger.json((String) null, jsonString));
  }

  @Test
  public void test_json_withValidStringMessageAndNullJsonString_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act & assert
    assertThrows(NullPointerException.class, () -> testLogger.json("Message", (String) null));
  }

  @Test
  public void
      test_json_withValidIMessageConstantMessageAndJsonString_shouldAddMessageAndJsonElementToTheLogEventBuilder() {
    // arrange
    Gson gson = new Gson();
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, gson);
    String jsonString = gson.toJson(List.of("key", 2));

    // act
    testLogger.json(() -> "Message", jsonString);

    // assert
    assertNotNull(testLogger.getLogEventBuilder());
  }

  @Test
  public void test_json_withNullIMessageConstantMessageAndValidJsonString_shouldThrowNPE() {
    // arrange
    Gson gson = new Gson();
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, gson);
    String jsonString = gson.toJson(List.of("key", 2));

    // act & assert
    assertThrows(
        NullPointerException.class, () -> testLogger.json((IMessageConstant) null, jsonString));
  }

  @Test
  public void test_json_withValidIMessageConstantMessageAndNullJsonString_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act & assert
    assertThrows(NullPointerException.class, () -> testLogger.json(() -> "Message", (String) null));
  }

  @Test
  public void test_throwable_withValidThrowable_shouldAddThrowableToTheLogEventBuilder() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act
    testLogger.throwable(new Throwable("Test Throwable"));

    // assert
    assertNotNull(testLogger.getLogEventBuilder());
  }

  @Test
  public void test_throwable_withNullThrowable_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act & assert
    assertThrows(NullPointerException.class, () -> testLogger.throwable((Throwable) null));
  }

  @Test
  public void
      test_throwable_withValidStringMessageAndValidThrowable_shouldAddMessageAndThrowableToTheLogEventBuilder() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act
    testLogger.throwable("Testing message", new Throwable("Test Throwable"));

    // assert
    assertNotNull(testLogger.getLogEventBuilder());
  }

  @Test
  public void test_throwable_withNullStringMessageAndValidThrowable_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act & assert
    assertThrows(
        NullPointerException.class,
        () -> testLogger.throwable((String) null, new Throwable("Test Throwable")));
  }

  @Test
  public void test_throwable_withValidStringMessageAndNullThrowable_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act & assert
    assertThrows(
        NullPointerException.class,
        () -> testLogger.throwable("Testing message", (Throwable) null));
  }

  @Test
  public void
      test_throwable_withValidIMessageConstantMessageAndValidThrowable_shouldAddMessageAndThrowableToTheLogEventBuilder() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act
    testLogger.throwable(() -> "Testing message", new Throwable("Test Throwable"));

    // assert
    assertNotNull(testLogger.getLogEventBuilder());
  }

  @Test
  public void test_throwable_withNullIMessageConstantMessageAndValidThrowable_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act & assert
    assertThrows(
        NullPointerException.class,
        () -> testLogger.throwable((IMessageConstant) null, new Throwable("Test Throwable")));
  }

  @Test
  public void test_throwable_withValidIMessageConstantMessageAndNullThrowable_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act & assert
    assertThrows(
        NullPointerException.class,
        () -> testLogger.throwable(() -> "Testing message", (Throwable) null));
  }

  @Test
  public void
      test_throwable_withValidILogEnabledException_shouldAddILogEnabledExceptionToTheLogEventBuilder() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act
    testLogger.throwable(
        new ILoggerEnabledException("Test Throwable") {
          @Override
          public Map<String, Object> getFormattedMap() {
            return Map.of();
          }
        });

    // assert
    assertNotNull(testLogger.getLogEventBuilder());
  }

  @Test
  public void test_throwable_withNullILogEnabledException_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act & assert
    assertThrows(
        NullPointerException.class, () -> testLogger.throwable((ILoggerEnabledException) null));
  }

  @Test
  public void
      test_throwable_withValidStringMessageAndValidILogEnabledException_shouldAddMessageAndILogEnabledExceptionToTheLogEventBuilder() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act
    testLogger.throwable(
        "Testing message",
        new ILoggerEnabledException("Test Throwable") {
          @Override
          public Map<String, Object> getFormattedMap() {
            return Map.of();
          }
        });

    // assert
    assertNotNull(testLogger.getLogEventBuilder());
  }

  @Test
  public void test_throwable_withNullStringMessageAndValidILogEnabledException_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act & assert
    assertThrows(
        NullPointerException.class,
        () ->
            testLogger.throwable(
                (String) null,
                new ILoggerEnabledException("Test Throwable") {
                  @Override
                  public Map<String, Object> getFormattedMap() {
                    return Map.of();
                  }
                }));
  }

  @Test
  public void test_throwable_withValidStringMessageAndNullILogEnabledException_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act & assert
    assertThrows(
        NullPointerException.class,
        () -> testLogger.throwable("Testing message", (ILoggerEnabledException) null));
  }

  @Test
  public void
      test_throwable_withValidIMessageConstantMessageAndValidILogEnabledException_shouldAddMessageAndThrowableToTheLogEventBuilder() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act
    testLogger.throwable(
        () -> "Testing message",
        new ILoggerEnabledException("Test Throwable") {
          @Override
          public Map<String, Object> getFormattedMap() {
            return Map.of();
          }
        });

    // assert
    assertNotNull(testLogger.getLogEventBuilder());
  }

  @Test
  public void
      test_throwable_withNullIMessageConstantMessageAndValidILogEnabledException_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act & assert
    assertThrows(
        NullPointerException.class,
        () ->
            testLogger.throwable(
                (IMessageConstant) null,
                new ILoggerEnabledException("Test Throwable") {
                  @Override
                  public Map<String, Object> getFormattedMap() {
                    return Map.of();
                  }
                }));
  }

  @Test
  public void
      test_throwable_withValidIMessageConstantMessageAndNullILogEnabledException_shouldThrowNPE() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act & assert
    assertThrows(
        NullPointerException.class,
        () -> testLogger.throwable(() -> "Testing message", (ILoggerEnabledException) null));
  }

  @Test
  public void test_stack() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act
    testLogger.stack();

    // assert
    assertNotNull(testLogger.getLogEventBuilder());
  }

  @Test
  public void test_formatMessage() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act
    testLogger.formatMessage();

    // assert
    assertNotNull(testLogger.getLogEventBuilder());
  }

  @Test
  public void test_executePipeline() {
    // arrange
    testLogger = new TestJsonLogger(LogLevel.INFO, logger, new Gson());

    // act
    testLogger.executePipeline();

    // assert
    assertNotNull(testLogger.getLogEventBuilder());
  }
}
