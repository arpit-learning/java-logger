package dev.arpit.learning.logger.enrichers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

import dev.arpit.learning.logger.core.LoggerConfiguration;
import dev.arpit.learning.logger.models.ILoggerConstant;
import dev.arpit.learning.logger.models.LoggerConstant;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.slf4j.MDC;

public class MdcEnricherTest {
  private MdcEnricher enricher;

  @BeforeEach
  public void setUp() {
    LoggerConfiguration.setMdcKeys(List.of(LoggerConstant.ATTRIBUTE_MDC_TRACE_ID));
  }

  @AfterEach
  public void tearDown() {
    MDC.clear();
  }

  @Test
  public void test_enrich_shouldCopyMdcAndExtractsSpecificKeys() {
    // arrange
    enricher = new MdcEnricher();
    Map<String, String> mockMdcMap = Map.of(
        LoggerConstant.ATTRIBUTE_MDC_TRACE_ID.getValue(), "12345",
        "some_other_key", "value"
    );

    try (MockedStatic<MDC> mockedMdc = mockStatic(MDC.class)) {
      mockedMdc.when(MDC::getCopyOfContextMap).thenReturn(mockMdcMap);
      mockedMdc.when(() -> MDC.get(LoggerConstant.ATTRIBUTE_MDC_TRACE_ID.getValue())).thenReturn("12345");
      mockedMdc.when(() -> MDC.get("some_other_key")).thenReturn("value");

      // act
      Map<ILoggerConstant, Object> attributes = enricher.enrich();

      // assert
      assertNotNull(attributes);
      assertEquals(2, attributes.size());
      Object mdcObj = attributes.get(LoggerConstant.ATTRIBUTE_MDC);
      @SuppressWarnings("unchecked")
      Map<String, String> mdcMap = (Map<String, String>) mdcObj;
      assertNotNull(mdcMap);
      assertEquals("12345", mdcMap.get("trace_id"));
      assertEquals("value", mdcMap.get("some_other_key"));
      assertEquals("12345", attributes.get(LoggerConstant.ATTRIBUTE_MDC_TRACE_ID));
    }
  }

  @Test
  public void test_enrich_withKeyNotPresentInMdc_shouldCopyMdcAndExtractsOnlyPresentSpecificKeys() {
    // arrange
    enricher = new MdcEnricher();
    LoggerConfiguration.setMdcKeys(
        List.of(LoggerConstant.ATTRIBUTE_MDC_TRACE_ID, LoggerConstant.ATTRIBUTE_MDC_USER_ID));
    Map<String, String> mockMdcMap = Map.of(
        LoggerConstant.ATTRIBUTE_MDC_TRACE_ID.getValue(), "12345",
        "some_other_key", "value"
    );

    try (MockedStatic<MDC> mockedMdc = mockStatic(MDC.class)) {
      mockedMdc.when(MDC::getCopyOfContextMap).thenReturn(mockMdcMap);
      mockedMdc.when(() -> MDC.get(LoggerConstant.ATTRIBUTE_MDC_TRACE_ID.getValue())).thenReturn("12345");
      mockedMdc.when(() -> MDC.get("some_other_key")).thenReturn("value");
      mockedMdc.when(() -> MDC.get(LoggerConstant.ATTRIBUTE_MDC_USER_ID.getValue())).thenReturn(null);

      // act
      Map<ILoggerConstant, Object> attributes = enricher.enrich();

      // assert
      assertNotNull(attributes);
      assertEquals(2, attributes.size());
      Object mdcObj = attributes.get(LoggerConstant.ATTRIBUTE_MDC);
      @SuppressWarnings("unchecked")
      Map<String, String> mdcMap = (Map<String, String>) mdcObj;
      assertNotNull(mdcMap);
      assertEquals("12345", mdcMap.get("trace_id"));
      assertEquals("value", mdcMap.get("some_other_key"));
      assertEquals("12345", attributes.get(LoggerConstant.ATTRIBUTE_MDC_TRACE_ID));
      assertFalse(attributes.containsKey(LoggerConstant.ATTRIBUTE_MDC_USER_ID));
    }
  }

  @Test
  public void test_enrich_withNullMdc_shouldNotContainAnything() {
    // arrange
    enricher = new MdcEnricher();

    // act
    Map<ILoggerConstant, Object> attributes = enricher.enrich();

    // assert
    assertNotNull(attributes);
    assertTrue(attributes.isEmpty());
  }

  @Test
  public void test_enrich_withEmptyMdc_shouldNotContainAnything() {
    // arrange
    enricher = new MdcEnricher();
    try (MockedStatic<MDC> mockedMdc = mockStatic(MDC.class)) {
      mockedMdc.when(MDC::getCopyOfContextMap).thenReturn(Map.of());

      // act
      Map<ILoggerConstant, Object> attributes = enricher.enrich();

      // assert
      assertNotNull(attributes);
      assertTrue(attributes.isEmpty());
    }
  }
}
