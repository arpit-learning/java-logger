package dev.arpit.pm1.logger.serializers;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.arpit.pm1.logger.models.ILoggerConstant;
import dev.arpit.pm1.logger.models.LogEvent;
import dev.arpit.pm1.logger.models.LogLevel;
import dev.arpit.pm1.logger.models.LoggerConstant;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class PrettyLogSerializerTest {
  private PrettyLogSerializer serializer;

  @Test
  public void test_serialize_withValidLogEvent() {
    // arrange
    serializer = new PrettyLogSerializer();
    Map<String, String> data = new HashMap<>();
    data.put("key1", "value1");

    Map<ILoggerConstant, Object> attributes = new HashMap<>();
    attributes.put(LoggerConstant.ATTRIBUTE_THREAD_NAME, "main-thread");
    attributes.put(LoggerConstant.ATTRIBUTE_CLASS, "com.test.MyClass");
    attributes.put(LoggerConstant.ATTRIBUTE_METHOD, "myMethod");
    attributes.put(LoggerConstant.ATTRIBUTE_LINE, 42);
    attributes.put(
        LoggerConstant.ATTRIBUTE_MESSAGE, "This is a pretty message\nwith multiple lines");
    attributes.put(LoggerConstant.ATTRIBUTE_DATA, data);

    LogEvent event =
        LogEvent.builder()
            .setLevel(LogLevel.INFO)
            .setComponent("TestComponent")
            .setAttributes(attributes)
            .build();

    // act
    String result = serializer.serialize(event);

    // assert
    assertTrue(result.contains("Thread: main-thread"));
    assertTrue(result.contains("com.test.MyClass"));
    assertTrue(result.contains("myMethod"));
    assertTrue(result.contains("42"));
    assertTrue(result.contains("This is a pretty message"));
    assertTrue(result.contains("with multiple lines"));
    assertTrue(result.contains("\"key1\": \"value1\""));
  }

  @Test
  public void test_serialize_withValidLogEvent_withoutClassDetails() {
    // arrange
    serializer = new PrettyLogSerializer();
    Map<String, String> data = new HashMap<>();
    data.put("key1", "value1");

    Map<ILoggerConstant, Object> attributes = new HashMap<>();
    attributes.put(LoggerConstant.ATTRIBUTE_THREAD_NAME, "main-thread");
    attributes.put(
        LoggerConstant.ATTRIBUTE_MESSAGE, "This is a pretty message\nwith multiple lines");
    attributes.put(LoggerConstant.ATTRIBUTE_DATA, data);

    LogEvent event =
        LogEvent.builder()
            .setLevel(LogLevel.INFO)
            .setComponent("TestComponent")
            .setAttributes(attributes)
            .build();

    // act
    String result = serializer.serialize(event);

    // assert
    assertTrue(result.contains("Thread: main-thread"));
    assertTrue(result.contains("This is a pretty message"));
    assertTrue(result.contains("with multiple lines"));
    assertTrue(result.contains("\"key1\": \"value1\""));
  }

  @Test
  public void test_serialize_withValidLogEvent_withoutMethodDetails() {
    // arrange
    serializer = new PrettyLogSerializer();
    Map<String, String> data = new HashMap<>();
    data.put("key1", "value1");

    Map<ILoggerConstant, Object> attributes = new HashMap<>();
    attributes.put(LoggerConstant.ATTRIBUTE_THREAD_NAME, "main-thread");
    attributes.put(LoggerConstant.ATTRIBUTE_CLASS, "com.test.MyClass");
    attributes.put(LoggerConstant.ATTRIBUTE_LINE, 42);
    attributes.put(
        LoggerConstant.ATTRIBUTE_MESSAGE, "This is a pretty message\nwith multiple lines");
    attributes.put(LoggerConstant.ATTRIBUTE_DATA, data);

    LogEvent event =
        LogEvent.builder()
            .setLevel(LogLevel.INFO)
            .setComponent("TestComponent")
            .setAttributes(attributes)
            .build();

    // act
    String result = serializer.serialize(event);

    // assert
    assertTrue(result.contains("Thread: main-thread"));
    assertTrue(result.contains("com.test.MyClass"));
    assertTrue(result.contains("42"));
    assertTrue(result.contains("This is a pretty message"));
    assertTrue(result.contains("with multiple lines"));
    assertTrue(result.contains("\"key1\": \"value1\""));
  }

  @Test
  public void test_serialize_withValidLogEvent_withoutLineDetails() {
    // arrange
    serializer = new PrettyLogSerializer();
    Map<String, String> data = new HashMap<>();
    data.put("key1", "value1");

    Map<ILoggerConstant, Object> attributes = new HashMap<>();
    attributes.put(LoggerConstant.ATTRIBUTE_THREAD_NAME, "main-thread");
    attributes.put(LoggerConstant.ATTRIBUTE_CLASS, "com.test.MyClass");
    attributes.put(LoggerConstant.ATTRIBUTE_METHOD, "myMethod");
    attributes.put(
        LoggerConstant.ATTRIBUTE_MESSAGE, "This is a pretty message\nwith multiple lines");
    attributes.put(LoggerConstant.ATTRIBUTE_DATA, data);

    LogEvent event =
        LogEvent.builder()
            .setLevel(LogLevel.INFO)
            .setComponent("TestComponent")
            .setAttributes(attributes)
            .build();

    // act
    String result = serializer.serialize(event);

    // assert
    assertTrue(result.contains("Thread: main-thread"));
    assertTrue(result.contains("com.test.MyClass"));
    assertTrue(result.contains("myMethod"));
    assertTrue(result.contains("This is a pretty message"));
    assertTrue(result.contains("with multiple lines"));
    assertTrue(result.contains("\"key1\": \"value1\""));
  }

  @Test
  public void test_serialize_withValidLogEvent_withoutThreadDetails() {
    // arrange
    serializer = new PrettyLogSerializer();
    Map<String, String> data = new HashMap<>();
    data.put("key1", "value1");

    Map<ILoggerConstant, Object> attributes = new HashMap<>();
    attributes.put(LoggerConstant.ATTRIBUTE_CLASS, "com.test.MyClass");
    attributes.put(LoggerConstant.ATTRIBUTE_METHOD, "myMethod");
    attributes.put(LoggerConstant.ATTRIBUTE_LINE, 42);
    attributes.put(
        LoggerConstant.ATTRIBUTE_MESSAGE, "This is a pretty message\nwith multiple lines");
    attributes.put(LoggerConstant.ATTRIBUTE_DATA, data);

    LogEvent event =
        LogEvent.builder()
            .setLevel(LogLevel.INFO)
            .setComponent("TestComponent")
            .setAttributes(attributes)
            .build();

    // act
    String result = serializer.serialize(event);

    // assert
    assertTrue(result.contains("com.test.MyClass"));
    assertTrue(result.contains("myMethod"));
    assertTrue(result.contains("42"));
    assertTrue(result.contains("This is a pretty message"));
    assertTrue(result.contains("with multiple lines"));
    assertTrue(result.contains("\"key1\": \"value1\""));
  }

  @Test
  public void test_serialize_withValidLogEvent_withoutMessage() {
    // arrange
    serializer = new PrettyLogSerializer();
    Map<String, String> data = new HashMap<>();
    data.put("key1", "value1");

    Map<ILoggerConstant, Object> attributes = new HashMap<>();
    attributes.put(LoggerConstant.ATTRIBUTE_THREAD_NAME, "main-thread");
    attributes.put(LoggerConstant.ATTRIBUTE_CLASS, "com.test.MyClass");
    attributes.put(LoggerConstant.ATTRIBUTE_METHOD, "myMethod");
    attributes.put(LoggerConstant.ATTRIBUTE_LINE, 42);
    attributes.put(LoggerConstant.ATTRIBUTE_DATA, data);

    LogEvent event =
        LogEvent.builder()
            .setLevel(LogLevel.INFO)
            .setComponent("TestComponent")
            .setAttributes(attributes)
            .build();

    // act
    String result = serializer.serialize(event);

    // assert
    assertTrue(result.contains("Thread: main-thread"));
    assertTrue(result.contains("com.test.MyClass"));
    assertTrue(result.contains("myMethod"));
    assertTrue(result.contains("42"));
    assertTrue(result.contains("\"key1\": \"value1\""));
  }

  @Test
  public void test_serialize_withValidLogEvent_withoutData() {
    // arrange
    serializer = new PrettyLogSerializer();
    Map<String, String> data = new HashMap<>();
    data.put("key1", "value1");

    Map<ILoggerConstant, Object> attributes = new HashMap<>();
    attributes.put(LoggerConstant.ATTRIBUTE_THREAD_NAME, "main-thread");
    attributes.put(LoggerConstant.ATTRIBUTE_CLASS, "com.test.MyClass");
    attributes.put(LoggerConstant.ATTRIBUTE_METHOD, "myMethod");
    attributes.put(LoggerConstant.ATTRIBUTE_LINE, 42);
    attributes.put(
        LoggerConstant.ATTRIBUTE_MESSAGE, "This is a pretty message\nwith multiple lines");

    LogEvent event =
        LogEvent.builder()
            .setLevel(LogLevel.INFO)
            .setComponent("TestComponent")
            .setAttributes(attributes)
            .build();

    // act
    String result = serializer.serialize(event);

    // assert
    assertTrue(result.contains("Thread: main-thread"));
    assertTrue(result.contains("com.test.MyClass"));
    assertTrue(result.contains("myMethod"));
    assertTrue(result.contains("42"));
    assertTrue(result.contains("This is a pretty message"));
    assertTrue(result.contains("with multiple lines"));
  }

  @Test
  public void test_serialize_withValidLogEvent_withThrowable() {
    // arrange
    serializer = new PrettyLogSerializer();
    Map<String, String> data = new HashMap<>();
    data.put("key1", "value1");

    Map<ILoggerConstant, Object> attributes = new HashMap<>();
    attributes.put(LoggerConstant.ATTRIBUTE_THREAD_NAME, "main-thread");
    attributes.put(LoggerConstant.ATTRIBUTE_CLASS, "com.test.MyClass");
    attributes.put(LoggerConstant.ATTRIBUTE_METHOD, "myMethod");
    attributes.put(LoggerConstant.ATTRIBUTE_LINE, 42);
    attributes.put(
        LoggerConstant.ATTRIBUTE_MESSAGE, "This is a pretty message\nwith multiple lines");
    attributes.put(LoggerConstant.ATTRIBUTE_DATA, data);

    LogEvent event =
        LogEvent.builder()
            .setLevel(LogLevel.INFO)
            .setComponent("TestComponent")
            .setAttributes(attributes)
            .setThrowable(new Throwable("test throwable"))
            .build();

    // act
    String result = serializer.serialize(event);

    // assert
    assertTrue(result.contains("Thread: main-thread"));
    assertTrue(result.contains("com.test.MyClass"));
    assertTrue(result.contains("myMethod"));
    assertTrue(result.contains("42"));
    assertTrue(result.contains("This is a pretty message"));
    assertTrue(result.contains("with multiple lines"));
    assertTrue(result.contains("\"key1\": \"value1\""));
    assertTrue(result.contains("java.lang.Throwable: test throwable"));
  }

  @Test
  public void test_serialize_withValidLogEvent_withDataAsString() {
    // arrange
    serializer = new PrettyLogSerializer();

    Map<ILoggerConstant, Object> attributes = new HashMap<>();
    attributes.put(LoggerConstant.ATTRIBUTE_THREAD_NAME, "main-thread");
    attributes.put(LoggerConstant.ATTRIBUTE_CLASS, "com.test.MyClass");
    attributes.put(LoggerConstant.ATTRIBUTE_METHOD, "myMethod");
    attributes.put(LoggerConstant.ATTRIBUTE_LINE, 42);
    attributes.put(
        LoggerConstant.ATTRIBUTE_MESSAGE, "This is a pretty message\nwith multiple lines");
    attributes.put(LoggerConstant.ATTRIBUTE_DATA, "data");

    LogEvent event =
        LogEvent.builder()
            .setLevel(LogLevel.INFO)
            .setComponent("TestComponent")
            .setAttributes(attributes)
            .build();

    // act
    String result = serializer.serialize(event);

    // assert
    assertTrue(result.contains("Thread: main-thread"));
    assertTrue(result.contains("com.test.MyClass"));
    assertTrue(result.contains("myMethod"));
    assertTrue(result.contains("42"));
    assertTrue(result.contains("This is a pretty message"));
    assertTrue(result.contains("with multiple lines"));
    assertTrue(result.contains("data"));
  }

  @Test
  public void test_serialize_withValidLogEvent_withJsonData() {
    // arrange
    serializer = new PrettyLogSerializer();

    Map<ILoggerConstant, Object> attributes = new HashMap<>();
    attributes.put(LoggerConstant.ATTRIBUTE_THREAD_NAME, "main-thread");
    attributes.put(LoggerConstant.ATTRIBUTE_CLASS, "com.test.MyClass");
    attributes.put(LoggerConstant.ATTRIBUTE_METHOD, "myMethod");
    attributes.put(LoggerConstant.ATTRIBUTE_LINE, 42);
    attributes.put(
        LoggerConstant.ATTRIBUTE_MESSAGE, "This is a pretty message\nwith multiple lines");
    attributes.put(LoggerConstant.ATTRIBUTE_DATA, "{\"key1\": \"value1\"}");

    LogEvent event =
        LogEvent.builder()
            .setLevel(LogLevel.INFO)
            .setComponent("TestComponent")
            .setAttributes(attributes)
            .build();

    // act
    String result = serializer.serialize(event);

    // assert
    assertTrue(result.contains("Thread: main-thread"));
    assertTrue(result.contains("com.test.MyClass"));
    assertTrue(result.contains("myMethod"));
    assertTrue(result.contains("42"));
    assertTrue(result.contains("This is a pretty message"));
    assertTrue(result.contains("with multiple lines"));
    assertTrue(result.contains("\"key1\": \"value1\""));
  }

  @Test
  public void test_serialize_withValidLogEvent_withJsonDataArray() {
    // arrange
    serializer = new PrettyLogSerializer();

    Map<ILoggerConstant, Object> attributes = new HashMap<>();
    attributes.put(LoggerConstant.ATTRIBUTE_THREAD_NAME, "main-thread");
    attributes.put(LoggerConstant.ATTRIBUTE_CLASS, "com.test.MyClass");
    attributes.put(LoggerConstant.ATTRIBUTE_METHOD, "myMethod");
    attributes.put(LoggerConstant.ATTRIBUTE_LINE, 42);
    attributes.put(
        LoggerConstant.ATTRIBUTE_MESSAGE, "This is a pretty message\nwith multiple lines");
    attributes.put(LoggerConstant.ATTRIBUTE_DATA, "[{\"key1\": \"value1\"}]");

    LogEvent event =
        LogEvent.builder()
            .setLevel(LogLevel.INFO)
            .setComponent("TestComponent")
            .setAttributes(attributes)
            .build();

    // act
    String result = serializer.serialize(event);

    // assert
    assertTrue(result.contains("Thread: main-thread"));
    assertTrue(result.contains("com.test.MyClass"));
    assertTrue(result.contains("myMethod"));
    assertTrue(result.contains("42"));
    assertTrue(result.contains("This is a pretty message"));
    assertTrue(result.contains("with multiple lines"));
    assertTrue(result.contains("\"key1\": \"value1\""));
  }

  @Test
  public void test_serialize_withValidLogEvent_withInvalidJsonData() {
    // arrange
    serializer = new PrettyLogSerializer();

    Map<ILoggerConstant, Object> attributes = new HashMap<>();
    attributes.put(LoggerConstant.ATTRIBUTE_THREAD_NAME, "main-thread");
    attributes.put(LoggerConstant.ATTRIBUTE_CLASS, "com.test.MyClass");
    attributes.put(LoggerConstant.ATTRIBUTE_METHOD, "myMethod");
    attributes.put(LoggerConstant.ATTRIBUTE_LINE, 42);
    attributes.put(
        LoggerConstant.ATTRIBUTE_MESSAGE, "This is a pretty message\nwith multiple lines");
    attributes.put(LoggerConstant.ATTRIBUTE_DATA, "{\"key1\": \"value1\"");

    LogEvent event =
        LogEvent.builder()
            .setLevel(LogLevel.INFO)
            .setComponent("TestComponent")
            .setAttributes(attributes)
            .build();

    // act
    String result = serializer.serialize(event);

    // assert
    assertTrue(result.contains("Thread: main-thread"));
    assertTrue(result.contains("com.test.MyClass"));
    assertTrue(result.contains("myMethod"));
    assertTrue(result.contains("42"));
    assertTrue(result.contains("This is a pretty message"));
    assertTrue(result.contains("with multiple lines"));
    assertTrue(result.contains("\"key1\": \"value1\""));
  }

  @Test
  public void test_serialize_withNullLogEvent_shouldThrowNPE() {
    // arrange
    serializer = new PrettyLogSerializer();

    // act & assert
    assertThrows(NullPointerException.class, () -> serializer.serialize(null));
  }
}
