package dev.arpit.pm1.logger.enrichers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import dev.arpit.pm1.logger.models.ILoggerConstant;
import dev.arpit.pm1.logger.models.LoggerConstant;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class TimestampEnricherTest {
  private TimestampEnricher enricher;

  @Test
  public void test_enrich_shouldReturnsAttributesContainingTimestampAndEpoch() {
    // arrange
    enricher = new TimestampEnricher();

    // act
    Map<ILoggerConstant, Object> attributes = enricher.enrich();

    // assert
    assertNotNull(attributes);
    assertEquals(2, attributes.size());
    assertNotNull(attributes.get(LoggerConstant.ATTRIBUTE_TIMESTAMP));
    assertNotNull(attributes.get(LoggerConstant.ATTRIBUTE_EPOCH));
  }
}
