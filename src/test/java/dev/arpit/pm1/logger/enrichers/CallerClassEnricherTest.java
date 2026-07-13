package dev.arpit.pm1.logger.enrichers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import dev.arpit.pm1.logger.core.LoggerConfiguration;
import dev.arpit.pm1.logger.models.ILoggerConstant;
import dev.arpit.pm1.logger.models.LoggerConstant;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class CallerClassEnricherTest {
  private CallerClassEnricher enricher;
  private StackWalker WALKER = mock(StackWalker.class);

  @Test
  public void test_enrich_resolvesCallerClass() {
    // arrange
    enricher = new CallerClassEnricher(LoggerConfiguration.getInstance().getExcludedClasses());
    LoggerConfiguration.getInstance()
        .getExcludedClasses()
        .remove(CallerClassEnricherTest.class.getName());

    // act
    Map<ILoggerConstant, Object> attributes = enricher.enrich();

    // assert
    Object className = attributes.get(LoggerConstant.ATTRIBUTE_CLASS);
    assertNotNull(className);
    assertTrue(
        className.toString().contains("CallerClassEnricherTest")
            || className.toString().contains("junit"));
  }

  @Test
  public void test_enrich_resolvesUnknownClass() {
    // arrange
    @SuppressWarnings("unchecked")
    Set<String> excludeAll = mock(Set.class);
    when(excludeAll.contains(any())).thenReturn(true);

    enricher = new CallerClassEnricher(excludeAll);

    // act
    Map<ILoggerConstant, Object> attributes = enricher.enrich();

    // assert
    Object className = attributes.get(LoggerConstant.ATTRIBUTE_CLASS);
    assertNotNull(className);
    assertEquals("Unknown", className.toString());
  }

  @Test
  public void test_enrich_coversJavaLangBranch() throws InterruptedException {
    // arrange
    // We want to force the stream to evaluate a "java.lang." frame.
    // Since stream.findFirst() short-circuits, we must exclude all frames that come
    // BEFORE
    // the java.lang frame. We use a mock Set that excludes everything EXCEPT
    // java.lang classes.
    @SuppressWarnings("unchecked")
    Set<String> excludeAllExceptJavaLang = mock(Set.class);
    when(excludeAllExceptJavaLang.contains(any()))
        .thenAnswer(
            invocation -> {
              String className = invocation.getArgument(0);
              return !className.startsWith("java.lang."); // exclude if NOT java.lang
            });

    enricher = new CallerClassEnricher(excludeAllExceptJavaLang);

    // act
    // Running in a new thread ensures java.lang.Thread.run is in the stack trace
    Thread thread = new Thread(() -> enricher.enrich());
    thread.start();
    thread.join();

    // which covers the !frame.getClassName().startsWith("java.lang.") == false
    // branch.
  }
}
