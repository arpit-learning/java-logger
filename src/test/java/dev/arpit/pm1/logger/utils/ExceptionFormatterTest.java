package dev.arpit.pm1.logger.utils;

import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import dev.arpit.pm1.logger.models.ILoggerEnabledException;
import dev.arpit.pm1.logger.models.LoggerConstant;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class ExceptionFormatterTest {
  @Test
  public void test_objectCreation() {
    // act
    ExceptionFormatter exceptionFormatter = new ExceptionFormatter();

    // assert
    assertNotNull(exceptionFormatter);
  }

  @Test
  public void
      test_format_withValidThrowableAndValidGson_shouldReturnAttributesHavingExceptionKeyWithData() {
    // act
    JsonElement attributes = ExceptionFormatter.format(new Throwable("test throwable"), new Gson());

    // assert
    assertTrue(
        attributes
            .getAsJsonObject()
            .keySet()
            .contains(LoggerConstant.ATTRIBUTE_EXCEPTION.getValue()));
  }

  @Test
  public void
      test_format_withValidILogEnabledExceptionAndValidGson_shouldReturnAttributesHavingDataBasedOnFormattedMapOfTheException() {
    // arrange
    Gson gson = new Gson();

    // act
    JsonElement attributes =
        ExceptionFormatter.format(
            new ILoggerEnabledException("test throwable") {
              @Override
              public Map<String, Object> getFormattedMap() {
                return Map.of("errorCode", 500, "message", "test throwable");
              }
            },
            gson);

    // assert
    assertFalse(
        attributes
            .getAsJsonObject()
            .keySet()
            .contains(LoggerConstant.ATTRIBUTE_EXCEPTION.getValue()));
    assertEquals(gson.toJsonTree(500), attributes.getAsJsonObject().get("errorCode"));
    assertEquals(gson.toJsonTree("test throwable"), attributes.getAsJsonObject().get("message"));
  }

  @Test
  public void test_format_withNullThrowableAndValidGson_shouldThrowNPE() {
    // act & assert
    assertThrows(NullPointerException.class, () -> ExceptionFormatter.format(null, new Gson()));
  }

  @Test
  public void test_format_withValidThrowableAndNullGson_shouldThrowNPE() {
    // act & assert
    assertThrows(
        NullPointerException.class,
        () -> ExceptionFormatter.format(new Throwable("Test throwable"), null));
  }
}
