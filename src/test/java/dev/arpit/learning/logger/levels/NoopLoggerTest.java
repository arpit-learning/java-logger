package dev.arpit.learning.logger.levels;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.google.gson.Gson;
import dev.arpit.learning.logger.models.ILoggerEnabledException;
import dev.arpit.learning.logger.models.IMessageConstant;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NoopLoggerTest {
  private NoopLogger noopLogger;

  @BeforeEach
  public void setUp() {
    noopLogger = new NoopLogger();
  }

  @Test
  public void test_message_withValidStringMessage() {
    // act
    noopLogger.message("Test Message");
  }

  @Test
  public void test_message_withValidIMessageConstantMessage() {
    // act
    noopLogger.message(() -> "Test Message");
  }

  @Test
  public void test_args_withValidStringMessageAndValidArgs() {
    // act
    noopLogger.args("Test Message", "args1", 2);
  }

  @Test
  public void test_args_withValidIMessageConstantMessageAndValidArgs() {
    // act
    noopLogger.args(() -> "Test Message", "args1", 2);
  }

  @Test
  public void test_map_withValidStringMessageAndValidMap() {
    // act
    noopLogger.map("Test Message", Map.of("key", "value", "key2", 2));
  }

  @Test
  public void test_map_withValidIMessageConstantMessageAndValidMap() {
    // act
    noopLogger.map(() -> "Test Message", Map.of("key", "value", "key2", 2));
  }

  @Test
  public void test_list_withValidStringMessageAndValidList() {
    // act
    noopLogger.list("Test Message", List.of("key", "value", "key2", 2));
  }

  @Test
  public void test_list_withValidIMessageConstantMessageAndValidList() {
    // act
    noopLogger.list(() -> "Test Message", List.of("key", "value", "key2", 2));
  }

  @Test
  public void test_json_withValidStringMessageAndValidJsonElement() {
    // act
    noopLogger.json("Test Message", (new Gson()).toJsonTree(Map.of("key", "value", "key2", 2)));
  }

  @Test
  public void test_json_withValidIMessageConstantMessageAndValidJsonElement() {
    // act
    noopLogger.json(
        () -> "Test Message", (new Gson()).toJsonTree(Map.of("key", "value", "key2", 2)));
  }

  @Test
  public void test_json_withValidStringMessageAndValidJsonString() {
    // act
    noopLogger.json("Test Message", (new Gson()).toJson(Map.of("key", "value", "key2", 2)));
  }

  @Test
  public void test_json_withValidIMessageConstantMessageAndValidJsonString() {
    // act
    noopLogger.json(() -> "Test Message", (new Gson()).toJson(Map.of("key", "value", "key2", 2)));
  }

  @Test
  public void test_throwable_withValidThrowable() {
    // act
    noopLogger.throwable(new Throwable("Test Throwable"));
  }

  @Test
  public void test_throwable_withValidStringMessageAndValidThrowable() {
    // act
    noopLogger.throwable("Test Message", new Throwable("Test Throwable"));
  }

  @Test
  public void test_throwable_withValidIMessageConstantMessageAndValidThrowable() {
    // act
    noopLogger.throwable(() -> "Test Message", new Throwable("Test Throwable"));
  }

  @Test
  public void test_throwable_withValidILogEnabledException() {
    // act
    noopLogger.throwable(
        new ILoggerEnabledException("Test Throwable") {
          @Override
          public Map<String, Object> getFormattedMap() {
            return Map.of();
          }
        });
  }

  @Test
  public void test_throwable_withValidStringMessageAndValidILogEnabledException() {
    // act
    noopLogger.throwable(
        "Test Message",
        new ILoggerEnabledException("Test Throwable") {
          @Override
          public Map<String, Object> getFormattedMap() {
            return Map.of();
          }
        });
  }

  @Test
  public void test_throwable_withValidIMessageConstantMessageAndValidILogEnabledException() {
    // act
    noopLogger.throwable(
        () -> "Test Message",
        new ILoggerEnabledException("Test Throwable") {
          @Override
          public Map<String, Object> getFormattedMap() {
            return Map.of();
          }
        });
  }

  @Test
  public void test_stack() {
    // act
    noopLogger.stack();
  }

  @Test
  public void test_log() {
    // act
    noopLogger.log();
  }

  @Test
  public void test_message_withNullStringMessage() {
    assertThrows(NullPointerException.class, () -> noopLogger.message((String) null));
  }

  @Test
  public void test_message_withNullIMessageConstantMessage() {
    assertThrows(NullPointerException.class, () -> noopLogger.message((IMessageConstant) null));
  }

  @Test
  public void test_args_withNullStringMessage() {
    assertThrows(NullPointerException.class, () -> noopLogger.args((String) null, "args"));
  }

  @Test
  public void test_args_withNullArgs() {
    assertThrows(NullPointerException.class, () -> noopLogger.args("Message", (Object[]) null));
  }

  @Test
  public void test_args_withNullIMessageConstantMessage() {
    assertThrows(
        NullPointerException.class, () -> noopLogger.args((IMessageConstant) null, "args"));
  }

  @Test
  public void test_args_withNullIMessageConstantMessageArgs() {
    assertThrows(
        NullPointerException.class, () -> noopLogger.args(() -> "Message", (Object[]) null));
  }

  @Test
  public void test_map_withNullStringMessage() {
    assertThrows(NullPointerException.class, () -> noopLogger.map((String) null, Map.of()));
  }

  @Test
  public void test_map_withNullMap() {
    assertThrows(
        NullPointerException.class, () -> noopLogger.map("Message", (Map<String, ?>) null));
  }

  @Test
  public void test_map_withNullIMessageConstantMessage() {
    assertThrows(
        NullPointerException.class, () -> noopLogger.map((IMessageConstant) null, Map.of()));
  }

  @Test
  public void test_map_withNullIMessageConstantMessageMap() {
    assertThrows(
        NullPointerException.class, () -> noopLogger.map(() -> "Message", (Map<String, ?>) null));
  }

  @Test
  public void test_list_withNullStringMessage() {
    assertThrows(NullPointerException.class, () -> noopLogger.list((String) null, List.of()));
  }

  @Test
  public void test_list_withNullList() {
    assertThrows(NullPointerException.class, () -> noopLogger.list("Message", (List<?>) null));
  }

  @Test
  public void test_list_withNullIMessageConstantMessage() {
    assertThrows(
        NullPointerException.class, () -> noopLogger.list((IMessageConstant) null, List.of()));
  }

  @Test
  public void test_list_withNullIMessageConstantMessageList() {
    assertThrows(
        NullPointerException.class, () -> noopLogger.list(() -> "Message", (List<?>) null));
  }

  @Test
  public void test_json_withNullStringMessageJsonElement() {
    assertThrows(
        NullPointerException.class,
        () -> noopLogger.json((String) null, new com.google.gson.Gson().toJsonTree(Map.of())));
  }

  @Test
  public void test_json_withNullJsonElement() {
    assertThrows(
        NullPointerException.class,
        () -> noopLogger.json("Message", (com.google.gson.JsonElement) null));
  }

  @Test
  public void test_json_withNullIMessageConstantMessageJsonElement() {
    assertThrows(
        NullPointerException.class,
        () ->
            noopLogger.json(
                (IMessageConstant) null, new com.google.gson.Gson().toJsonTree(Map.of())));
  }

  @Test
  public void test_json_withNullIMessageConstantMessageJsonElementArg() {
    assertThrows(
        NullPointerException.class,
        () -> noopLogger.json(() -> "Message", (com.google.gson.JsonElement) null));
  }

  @Test
  public void test_json_withNullStringMessageJsonString() {
    assertThrows(NullPointerException.class, () -> noopLogger.json((String) null, "{}"));
  }

  @Test
  public void test_json_withNullJsonString() {
    assertThrows(NullPointerException.class, () -> noopLogger.json("Message", (String) null));
  }

  @Test
  public void test_json_withNullIMessageConstantMessageJsonString() {
    assertThrows(NullPointerException.class, () -> noopLogger.json((IMessageConstant) null, "{}"));
  }

  @Test
  public void test_json_withNullIMessageConstantMessageJsonStringArg() {
    assertThrows(NullPointerException.class, () -> noopLogger.json(() -> "Message", (String) null));
  }

  @Test
  public void test_throwable_withNullThrowable() {
    assertThrows(NullPointerException.class, () -> noopLogger.throwable((Throwable) null));
  }

  @Test
  public void test_throwable_withNullStringMessage() {
    assertThrows(
        NullPointerException.class, () -> noopLogger.throwable((String) null, new Throwable()));
  }

  @Test
  public void test_throwable_withNullStringMessageThrowable() {
    assertThrows(
        NullPointerException.class, () -> noopLogger.throwable("Message", (Throwable) null));
  }

  @Test
  public void test_throwable_withNullIMessageConstantMessage() {
    assertThrows(
        NullPointerException.class,
        () -> noopLogger.throwable((IMessageConstant) null, new Throwable()));
  }

  @Test
  public void test_throwable_withNullIMessageConstantMessageThrowable() {
    assertThrows(
        NullPointerException.class, () -> noopLogger.throwable(() -> "Message", (Throwable) null));
  }

  @Test
  public void test_throwable_withNullILoggerEnabledException() {
    assertThrows(
        NullPointerException.class, () -> noopLogger.throwable((ILoggerEnabledException) null));
  }

  @Test
  public void test_throwable_withNullStringMessageILoggerEnabledException() {
    assertThrows(
        NullPointerException.class,
        () ->
            noopLogger.throwable(
                (String) null,
                new ILoggerEnabledException("") {
                  @Override
                  public Map<String, Object> getFormattedMap() {
                    return Map.of();
                  }
                }));
  }

  @Test
  public void test_throwable_withNullStringMessageILoggerEnabledExceptionArg() {
    assertThrows(
        NullPointerException.class,
        () -> noopLogger.throwable("Message", (ILoggerEnabledException) null));
  }

  @Test
  public void test_throwable_withNullIMessageConstantMessageILoggerEnabledException() {
    assertThrows(
        NullPointerException.class,
        () ->
            noopLogger.throwable(
                (IMessageConstant) null,
                new ILoggerEnabledException("") {
                  @Override
                  public Map<String, Object> getFormattedMap() {
                    return Map.of();
                  }
                }));
  }

  @Test
  public void test_throwable_withNullIMessageConstantMessageILoggerEnabledExceptionArg() {
    assertThrows(
        NullPointerException.class,
        () -> noopLogger.throwable(() -> "Message", (ILoggerEnabledException) null));
  }
}
