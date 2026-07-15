package dev.arpit.learning.logger.enrichers;

import static org.junit.jupiter.api.Assertions.*;

import dev.arpit.learning.logger.models.ILoggerConstant;
import dev.arpit.learning.logger.models.LoggerConstant;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class ThreadNameEnricherTest {
  private ThreadNameEnricher enricher;

  @Test
  public void test_enrich_shouldReturnAttributesContainingThreadDetails() {
    // arrange
    enricher = new ThreadNameEnricher();

    // act
    Map<ILoggerConstant, Object> attributes = enricher.enrich();

    // assert
    assertNotNull(attributes);
    assertEquals(1, attributes.size());
    Object threadName = attributes.get(LoggerConstant.ATTRIBUTE_THREAD_NAME);
    assertNotNull(threadName);
    assertFalse(threadName.toString().isEmpty());
  }
}
