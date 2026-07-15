package dev.arpit.learning.logger.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.Test;

public class ILoggerEnabledExceptionTest {
  private static class TempLoggerEnabledException extends ILoggerEnabledException {
    private Map<String, Object> formattedMap;

    public void setFormattedMap(Map<String, Object> formattedMap) {
      this.formattedMap = formattedMap;
    }

    public TempLoggerEnabledException() {
      super();
    }

    public TempLoggerEnabledException(String message) {
      super(message);
    }

    @Override
    public Map<String, Object> getFormattedMap() {
      return formattedMap;
    }
  }

  @Test
  public void test_objectCreation_withNoParams() {
    // act
    TempLoggerEnabledException exception = new TempLoggerEnabledException();

    // assert
    assertNotNull(exception);
  }

  @Test
  public void test_objectCreation_withValidMessage() {
    // act
    TempLoggerEnabledException exception = new TempLoggerEnabledException("Test Message");

    // assert
    assertNotNull(exception);
    assertEquals("Test Message", exception.getMessage());
  }

  @Test
  public void test_objectCreation_withNullMessage_shouldThrowNPE() {
    // act & assert
    assertThrows(NullPointerException.class, () -> new TempLoggerEnabledException(null));
  }

  @Test
  public void test_getFormattedMap() {
    // arrange
    TempLoggerEnabledException exception = new TempLoggerEnabledException("Test Message");
    exception.setFormattedMap(Map.of("key", "value"));

    // act
    Map<String, Object> formattedMap = exception.getFormattedMap();

    // assert
    assertNotNull(formattedMap);
    assertEquals(1, formattedMap.size());
    assertEquals("value", formattedMap.get("key"));
  }
}
