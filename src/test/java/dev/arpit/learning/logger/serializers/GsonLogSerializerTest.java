package dev.arpit.learning.logger.serializers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import dev.arpit.learning.logger.models.ILoggerConstant;
import dev.arpit.learning.logger.models.LogEvent;
import dev.arpit.learning.logger.models.LogLevel;
import dev.arpit.learning.logger.models.LoggerConstant;
import dev.arpit.learning.logger.utils.ExceptionFormatter;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

public class GsonLogSerializerTest {
  private GsonLogSerializer serializer;

  private static class CircularRef {
    CircularRef self;
  }

  @Test
  public void test_objectCreation_withValidGson_shouldCreateValidObject() {
    // arrange
    Gson gson = (new GsonBuilder()).create();

    // act
    serializer = new GsonLogSerializer(gson);

    // assert
    assertNotNull(serializer);
  }

  @Test
  public void test_objectCreation_withNullGson_shouldThrowNPE() {
    // act & assert
    assertThrows(NullPointerException.class, () -> new GsonLogSerializer(null));
  }

  @Test
  public void test_serialize_withNullLogEvent_shouldThrowNPE() {
    // arrange
    Gson gson = (new GsonBuilder()).create();
    serializer = new GsonLogSerializer(gson);

    // act & assert
    assertThrows(NullPointerException.class, () -> serializer.serialize(null));
  }

  @Test
  public void test_serialize_withNormalFields_shouldSerializeSuccessfully() {
    // arrange
    Gson gson = (new GsonBuilder()).create();
    serializer = new GsonLogSerializer(gson);
    Map<ILoggerConstant, Object> attributes = new HashMap<>();
    attributes.put(LoggerConstant.ATTRIBUTE_MESSAGE, "Hello JSON");
    LogEvent event =
        LogEvent.builder()
            .setLevel(LogLevel.INFO)
            .setComponent("TestComponent")
            .setAttributes(attributes)
            .build();

    // act
    String json = serializer.serialize(event);

    // assert
    assertNotNull(json);
    assertTrue(json.contains("\"__message\":\"Hello JSON\""));
    assertTrue(json.contains("\"__level\":\"INFO\""));
    assertTrue(json.contains("\"__component\":\"TestComponent\""));
  }

  @Test
  public void test_serialize_withSerializationError_shouldFallbackToStringifyReference() {
    // arrange
    Gson gson = mock(Gson.class);
    serializer = new GsonLogSerializer(gson);

    Map<ILoggerConstant, Object> attributes = new HashMap<>();
    Object data = new Object();
    attributes.put(LoggerConstant.ATTRIBUTE_DATA, data);

    LogEvent event =
        LogEvent.builder()
            .setLevel(LogLevel.INFO)
            .setComponent("TestComponent")
            .setAttributes(attributes)
            .build();

    // mock gson behavior to throw an exception for the data field, and return
    // normal JsonElement for others
    when(gson.toJsonTree(data)).thenThrow(new RuntimeException("Serialization failed"));
    when(gson.toJsonTree(LogLevel.INFO)).thenReturn(new JsonPrimitive("INFO"));
    when(gson.toJsonTree("TestComponent")).thenReturn(new JsonPrimitive("TestComponent"));
    when(gson.toJson(any(JsonObject.class)))
        .thenAnswer(invocation -> invocation.getArgument(0).toString());

    // act
    String json = serializer.serialize(event);

    // assert
    assertNotNull(json);
    assertTrue(json.contains("\"__level\":\"INFO\""));
    assertTrue(json.contains("\"__component\":\"TestComponent\""));
    assertTrue(json.contains("\"__data\":\"java.lang.Object@"));
  }

  @Test
  public void test_serialize_withNormalThrowable_shouldSerializeSuccessfully() {
    // arrange
    Gson gson = (new GsonBuilder()).create();
    serializer = new GsonLogSerializer(gson);
    Map<ILoggerConstant, Object> attributes = new HashMap<>();
    attributes.put(LoggerConstant.ATTRIBUTE_MESSAGE, "Hello JSON");
    LogEvent event =
        LogEvent.builder()
            .setLevel(LogLevel.INFO)
            .setComponent("TestComponent")
            .setAttributes(attributes)
            .setThrowable(new Throwable("Test throwable"))
            .build();

    // act
    String json = serializer.serialize(event);

    // assert
    assertNotNull(json);
    assertTrue(json.contains("\"__message\":\"Hello JSON\""));
    assertTrue(json.contains("\"__level\":\"INFO\""));
    assertTrue(json.contains("\"__component\":\"TestComponent\""));
    assertTrue(json.contains("\"__exception\":\"java.lang.Throwable: Test throwable"));
  }

  @Test
  public void
      test_serialize_withExceptionFormatterThrowingError_shouldHandleAndAddExceptionAttribute() {
    // arrange
    Gson gson = (new GsonBuilder()).create();
    serializer = new GsonLogSerializer(gson);
    Map<ILoggerConstant, Object> attributes = new HashMap<>();
    attributes.put(LoggerConstant.ATTRIBUTE_MESSAGE, "Hello JSON");
    LogEvent event =
        LogEvent.builder()
            .setLevel(LogLevel.INFO)
            .setComponent("TestComponent")
            .setAttributes(attributes)
            .setThrowable(new Throwable("1st Throwable"))
            .build();

    try (MockedStatic<ExceptionFormatter> mocked = mockStatic(ExceptionFormatter.class)) {
      mocked
          .when(() -> ExceptionFormatter.format(any(Throwable.class), any(Gson.class)))
          .thenThrow(new RuntimeException("2nd throwable"));

      // act
      String json = serializer.serialize(event);

      // assert
      assertNotNull(json);
      assertTrue(json.contains("\"__message\":\"Hello JSON\""));
      assertTrue(json.contains("\"__level\":\"INFO\""));
      assertTrue(json.contains("\"__component\":\"TestComponent\""));
      assertTrue(json.contains("\"__exception\":\"java.lang.RuntimeException: 2nd throwable"));
    }
  }
}
