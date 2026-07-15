package dev.arpit.learning.logger.models;

import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.Gson;
import dev.arpit.learning.logger.utils.StackTraceUtil;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class LogEventTest {
  @Test
  public void test_Builder_objectCreation() {
    // act
    LogEvent.Builder builder = LogEvent.builder();

    // assert
    assertNotNull(builder);
  }

  @Test
  public void test_Builder_settingValidDataAndBuild() {
    // arrange
    LogEvent.Builder builder = LogEvent.builder();

    // act
    LogEvent event =
        builder
            .setLevel(LogLevel.DEBUG)
            .setComponent("TestComponent")
            .setAttributes(Map.of(LoggerConstant.ATTRIBUTE_MESSAGE, "Test message"))
            .setMessage("Message")
            .setMessage(() -> "Message")
            .setArgs("arg1", "arg2")
            .setMap(Map.of("key1", "value1", "key2", "value2"))
            .setList(List.of("item1", 2))
            .setJson((new Gson()).toJsonTree("jsonItem"))
            .setJsonString("jsonString")
            .setThrowable(new Throwable("test throwable"))
            .setStackTrace(StackTraceUtil.formatStack())
            .build();

    // assert
    assertNotNull(builder);
    assertNotNull(event);
  }

  @Test
  public void test_Builder_settingNullLevel_shouldThrowNPE() {
    // arrange
    LogEvent.Builder builder = LogEvent.builder();

    // act & assert
    assertThrows(NullPointerException.class, () -> builder.setLevel(null));
  }

  @Test
  public void test_Builder_settingNullComponent_shouldThrowNPE() {
    // arrange
    LogEvent.Builder builder = LogEvent.builder();

    // act & assert
    assertThrows(NullPointerException.class, () -> builder.setComponent(null));
  }

  @Test
  public void test_Builder_settingNullAttributes_shouldThrowNPE() {
    // arrange
    LogEvent.Builder builder = LogEvent.builder();

    // act & assert
    assertThrows(NullPointerException.class, () -> builder.setAttributes(null));
  }

  @Test
  public void test_Builder_settingNullStringMessage_shouldThrowNPE() {
    // arrange
    LogEvent.Builder builder = LogEvent.builder();

    // act & assert
    assertThrows(NullPointerException.class, () -> builder.setMessage((String) null));
  }

  @Test
  public void test_Builder_settingNullIMessageConstantMessage_shouldThrowNPE() {
    // arrange
    LogEvent.Builder builder = LogEvent.builder();

    // act & assert
    assertThrows(NullPointerException.class, () -> builder.setMessage((IMessageConstant) null));
  }

  @Test
  public void test_Builder_settingNullArgs_shouldThrowNPE() {
    // arrange
    LogEvent.Builder builder = LogEvent.builder();

    // act & assert
    assertThrows(NullPointerException.class, () -> builder.setArgs(null));
  }

  @Test
  public void test_Builder_settingEmptyArgs_shouldNotUpdateArgs() {
    // arrange
    LogEvent.Builder builder = LogEvent.builder();

    // act
    LogEvent event =
        builder
            .setLevel(LogLevel.DEBUG)
            .setComponent("TestComponent")
            .setArgs(new Object[] {})
            .build();

    // assert
    assertFalse(event.getAttributes().containsKey(LoggerConstant.ATTRIBUTE_ARGS));
  }

  @Test
  public void test_Builder_settingNullMap_shouldThrowNPE() {
    // arrange
    LogEvent.Builder builder = LogEvent.builder();

    // act & assert
    assertThrows(NullPointerException.class, () -> builder.setMap(null));
  }

  @Test
  public void test_Builder_settingNullList_shouldThrowNPE() {
    // arrange
    LogEvent.Builder builder = LogEvent.builder();

    // act & assert
    assertThrows(NullPointerException.class, () -> builder.setList(null));
  }

  @Test
  public void test_Builder_settingNullJsonElement_shouldThrowNPE() {
    // arrange
    LogEvent.Builder builder = LogEvent.builder();

    // act & assert
    assertThrows(NullPointerException.class, () -> builder.setJson(null));
  }

  @Test
  public void test_Builder_settingNullJsonString_shouldThrowNPE() {
    // arrange
    LogEvent.Builder builder = LogEvent.builder();

    // act & assert
    assertThrows(NullPointerException.class, () -> builder.setJsonString(null));
  }

  @Test
  public void test_Builder_settingNullThrowable_shouldThrowNPE() {
    // arrange
    LogEvent.Builder builder = LogEvent.builder();

    // act & assert
    assertThrows(NullPointerException.class, () -> builder.setThrowable(null));
  }

  @Test
  public void test_Builder_settingNullStackTrace_shouldThrowNPE() {
    // arrange
    LogEvent.Builder builder = LogEvent.builder();

    // act & assert
    assertThrows(NullPointerException.class, () -> builder.setStackTrace(null));
  }

  @Test
  public void test_Builder_settingNullLogLevelAndBuild_shouldThrowNPE() {
    // arrange
    LogEvent.Builder builder = LogEvent.builder();

    // act & assert
    assertThrows(NullPointerException.class, () -> builder.build());
  }

  @Test
  public void test_Builder_settingNullComponentAndBuild_shouldThrowNPE() {
    // arrange
    LogEvent.Builder builder = LogEvent.builder();

    // act & assert
    assertThrows(NullPointerException.class, () -> builder.setLevel(LogLevel.DEBUG).build());
  }
}
