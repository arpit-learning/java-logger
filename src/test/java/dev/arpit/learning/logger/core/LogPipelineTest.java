package dev.arpit.learning.logger.core;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import dev.arpit.learning.logger.enrichers.ILogEnricher;
import dev.arpit.learning.logger.models.ILoggerConstant;
import dev.arpit.learning.logger.models.LogEvent;
import dev.arpit.learning.logger.models.LogLevel;
import dev.arpit.learning.logger.models.LoggerConstant;
import dev.arpit.learning.logger.serializers.ILogSerializer;
import dev.arpit.learning.logger.writers.ILogWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class LogPipelineTest {
  private final ILogSerializer mockSerializer = mock(ILogSerializer.class);
  private final ILogWriter mockWriter = mock(ILogWriter.class);
  private final ILogEnricher mockEnricher = mock(ILogEnricher.class);
  private LogPipeline logPipeline;

  @Test
  public void
      test_objectCreation_withValidEnrichersAndValidSerializerAndValidWriter_shouldCreationObject() {
    // act
    logPipeline = new LogPipeline(List.of(mockEnricher), mockSerializer, mockWriter);

    // assert
    assertNotNull(logPipeline);
  }

  @Test
  public void
      test_objectCreation_withNullEnrichersAndValidSerializerAndValidWriter_shouldThrowNPE() {
    // act & assert
    assertThrows(
        NullPointerException.class, () -> new LogPipeline(null, mockSerializer, mockWriter));
  }

  @Test
  public void
      test_objectCreation_withValidEnrichersAndNullSerializerAndValidWriter_shouldThrowNPE() {
    // act & assert
    assertThrows(
        NullPointerException.class, () -> new LogPipeline(List.of(mockEnricher), null, mockWriter));
  }

  @Test
  public void
      test_objectCreation_withValidEnrichersAndValidSerializerAndNullWriter_shouldThrowNPE() {
    // act & assert
    assertThrows(
        NullPointerException.class,
        () -> new LogPipeline(List.of(mockEnricher), mockSerializer, null));
  }

  @Test
  public void test_execute_withValidLogEvent_shouldWriteLog() {
    // arrange
    logPipeline = new LogPipeline(List.of(mockEnricher), mockSerializer, mockWriter);
    LogEvent event =
        LogEvent.builder().setLevel(LogLevel.INFO).setComponent("testComponent").build();

    when(mockSerializer.serialize(any(LogEvent.class))).thenReturn("serialized-message");

    // act
    logPipeline.execute(event);

    // assert
    verify(mockEnricher, times(1)).enrich();
    verify(mockSerializer, times(1)).serialize(event);
    verify(mockWriter, times(1)).write(LogLevel.INFO, "serialized-message");
  }

  @Test
  public void test_execute_withNullLogEvent_shouldThrowNPE() {
    // arrange
    logPipeline = new LogPipeline(List.of(mockEnricher), mockSerializer, mockWriter);

    // act & assert
    assertThrows(NullPointerException.class, () -> logPipeline.execute(null));
  }

  @Test
  public void test_formatMessage_withValidLogEvent_shouldReturnFormattedMessage() {
    // arrange
    logPipeline = new LogPipeline(List.of(mockEnricher), mockSerializer, mockWriter);
    LogEvent event =
        LogEvent.builder().setLevel(LogLevel.INFO).setComponent("testComponent").build();

    Map<ILoggerConstant, Object> attributes = new HashMap<>();
    attributes.put(LoggerConstant.ATTRIBUTE_CLASS, "testClass");
    attributes.put(LoggerConstant.ATTRIBUTE_EPOCH, System.currentTimeMillis());

    when(mockEnricher.enrich()).thenReturn(attributes);
    when(mockSerializer.serialize(any(LogEvent.class))).thenReturn("serialized-message");

    // act
    String formattedMessage = logPipeline.formatMessage(event);

    // assert
    verify(mockEnricher, times(1)).enrich();
    verify(mockSerializer, times(1)).serialize(event);
    assertEquals("serialized-message", formattedMessage);
  }

  @Test
  public void test_formatMessage_withNullLogEvent_shouldThrowNPE() {
    // arrange
    logPipeline = new LogPipeline(List.of(mockEnricher), mockSerializer, mockWriter);

    // act & assert
    assertThrows(NullPointerException.class, () -> logPipeline.formatMessage(null));
  }

  @Test
  public void
      test_formatMessage_withValidLogEventAndEnricherThrowsError_shouldHandleErrorGracefully() {
    // arrange
    logPipeline = new LogPipeline(List.of(mockEnricher), mockSerializer, mockWriter);
    LogEvent event =
        LogEvent.builder().setLevel(LogLevel.INFO).setComponent("testComponent").build();

    doThrow(new RuntimeException("Test enricher exception")).when(mockEnricher).enrich();
    when(mockSerializer.serialize(any(LogEvent.class))).thenReturn("serialized-message");

    // act
    String formattedMessage = logPipeline.formatMessage(event);

    // assert
    verify(mockEnricher, times(1)).enrich();
    verify(mockSerializer, times(1)).serialize(event);

    Map<ILoggerConstant, Object> attributes = event.getAttributes();

    boolean foundError = false;
    for (Map.Entry<ILoggerConstant, Object> entry : attributes.entrySet()) {
      if (entry.getKey().getValue().startsWith(LoggerConstant.ENRICHMENT_ERROR.getValue())) {
        foundError = true;
        assertEquals("Test enricher exception", entry.getValue());
        assertTrue(entry.getKey().getValue().contains(mockEnricher.getClass().getSimpleName()));
      }
    }
    assertTrue(foundError, "Should have added an enrichment error key");
    assertEquals("serialized-message", formattedMessage);
  }
}
