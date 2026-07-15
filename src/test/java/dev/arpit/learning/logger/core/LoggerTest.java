package dev.arpit.learning.logger.core;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import dev.arpit.learning.logger.models.ILoggerEnabledException;
import dev.arpit.learning.logger.models.IMessageConstant;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoggerTest {
  private org.slf4j.Logger slf4jLogger;
  private Logger logger;
  private IMessageConstant mockMessageConstant;
  private ILoggerEnabledException mockException;

  @BeforeEach
  public void setUp() {
    slf4jLogger = mock(org.slf4j.Logger.class);
    when(slf4jLogger.isInfoEnabled()).thenReturn(true);
    when(slf4jLogger.isErrorEnabled()).thenReturn(true);
    when(slf4jLogger.isDebugEnabled()).thenReturn(true);
    when(slf4jLogger.isWarnEnabled()).thenReturn(true);
    when(slf4jLogger.isTraceEnabled()).thenReturn(true);

    logger = new Logger(slf4jLogger);

    mockMessageConstant = mock(IMessageConstant.class);
    when(mockMessageConstant.getMessage()).thenReturn("mock message");

    mockException =
        new ILoggerEnabledException("test exception") {
          @Override
          public Map<String, Object> getFormattedMap() {
            return Map.of();
          }
        };
  }

  @Test
  public void test_info_withString() {
    logger.info("message");
    verify(slf4jLogger, times(1)).info(anyString());
  }

  @Test
  public void test_info_withNullString_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.info((String) null));
  }

  @Test
  public void test_info_withIMessageConstant() {
    logger.info(mockMessageConstant);
    verify(slf4jLogger, times(1)).info(anyString());
  }

  @Test
  public void test_info_withNullIMessageConstant_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.info((IMessageConstant) null));
  }

  @Test
  public void test_info_withString_Args() {
    logger.info("message", new Object[] {"arg"});
    verify(slf4jLogger, times(1)).info(anyString());
  }

  @Test
  public void test_info_withNullString_Args_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.info((String) null, new Object[] {"arg"}));
  }

  @Test
  public void test_info_withString_NullArgs_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.info("message", (Object[]) null));
  }

  @Test
  public void test_info_withIMessageConstant_Args() {
    logger.info(mockMessageConstant, new Object[] {"arg"});
    verify(slf4jLogger, times(1)).info(anyString());
  }

  @Test
  public void test_info_withNullIMessageConstant_Args_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class,
        () -> logger.info((IMessageConstant) null, new Object[] {"arg"}));
  }

  @Test
  public void test_info_withIMessageConstant_NullArgs_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.info(mockMessageConstant, (Object[]) null));
  }

  @Test
  public void test_error_withString() {
    logger.error("message");
    verify(slf4jLogger, times(1)).error(anyString());
  }

  @Test
  public void test_error_withNullString_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.error((String) null));
  }

  @Test
  public void test_error_withIMessageConstant() {
    logger.error(mockMessageConstant);
    verify(slf4jLogger, times(1)).error(anyString());
  }

  @Test
  public void test_error_withNullIMessageConstant_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.error((IMessageConstant) null));
  }

  @Test
  public void test_error_withString_Args() {
    logger.error("message", new Object[] {"arg"});
    verify(slf4jLogger, times(1)).error(anyString());
  }

  @Test
  public void test_error_withNullString_Args_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.error((String) null, new Object[] {"arg"}));
  }

  @Test
  public void test_error_withString_NullArgs_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.error("message", (Object[]) null));
  }

  @Test
  public void test_error_withIMessageConstant_Args() {
    logger.error(mockMessageConstant, new Object[] {"arg"});
    verify(slf4jLogger, times(1)).error(anyString());
  }

  @Test
  public void test_error_withNullIMessageConstant_Args_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class,
        () -> logger.error((IMessageConstant) null, new Object[] {"arg"}));
  }

  @Test
  public void test_error_withIMessageConstant_NullArgs_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.error(mockMessageConstant, (Object[]) null));
  }

  @Test
  public void test_error_withThrowable() {
    logger.error(new RuntimeException());
    verify(slf4jLogger, times(1)).error(anyString());
  }

  @Test
  public void test_error_withNullThrowable_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.error((Throwable) null));
  }

  @Test
  public void test_error_withString_Throwable() {
    logger.error("message", new RuntimeException());
    verify(slf4jLogger, times(1)).error(anyString());
  }

  @Test
  public void test_error_withNullString_Throwable_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.error((String) null, new RuntimeException()));
  }

  @Test
  public void test_error_withString_NullThrowable_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.error("message", (Throwable) null));
  }

  @Test
  public void test_error_withIMessageConstant_Throwable() {
    logger.error(mockMessageConstant, new RuntimeException());
    verify(slf4jLogger, times(1)).error(anyString());
  }

  @Test
  public void test_error_withNullIMessageConstant_Throwable_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class,
        () -> logger.error((IMessageConstant) null, new RuntimeException()));
  }

  @Test
  public void test_error_withIMessageConstant_NullThrowable_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.error(mockMessageConstant, (Throwable) null));
  }

  @Test
  public void test_error_withILoggerEnabledException() {
    logger.error(mockException);
    verify(slf4jLogger, times(1)).error(anyString());
  }

  @Test
  public void test_error_withNullILoggerEnabledException_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.error((ILoggerEnabledException) null));
  }

  @Test
  public void test_error_withString_ILoggerEnabledException() {
    logger.error("message", mockException);
    verify(slf4jLogger, times(1)).error(anyString());
  }

  @Test
  public void test_error_withNullString_ILoggerEnabledException_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.error((String) null, mockException));
  }

  @Test
  public void test_error_withString_NullILoggerEnabledException_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.error("message", (ILoggerEnabledException) null));
  }

  @Test
  public void test_error_withIMessageConstant_ILoggerEnabledException() {
    logger.error(mockMessageConstant, mockException);
    verify(slf4jLogger, times(1)).error(anyString());
  }

  @Test
  public void test_error_withNullIMessageConstant_ILoggerEnabledException_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.error((IMessageConstant) null, mockException));
  }

  @Test
  public void test_error_withIMessageConstant_NullILoggerEnabledException_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class,
        () -> logger.error(mockMessageConstant, (ILoggerEnabledException) null));
  }

  @Test
  public void test_debug_withString() {
    logger.debug("message");
    verify(slf4jLogger, times(1)).debug(anyString());
  }

  @Test
  public void test_debug_withNullString_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.debug((String) null));
  }

  @Test
  public void test_debug_withIMessageConstant() {
    logger.debug(mockMessageConstant);
    verify(slf4jLogger, times(1)).debug(anyString());
  }

  @Test
  public void test_debug_withNullIMessageConstant_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.debug((IMessageConstant) null));
  }

  @Test
  public void test_debug_withString_Args() {
    logger.debug("message", new Object[] {"arg"});
    verify(slf4jLogger, times(1)).debug(anyString());
  }

  @Test
  public void test_debug_withNullString_Args_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.debug((String) null, new Object[] {"arg"}));
  }

  @Test
  public void test_debug_withString_NullArgs_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.debug("message", (Object[]) null));
  }

  @Test
  public void test_debug_withIMessageConstant_Args() {
    logger.debug(mockMessageConstant, new Object[] {"arg"});
    verify(slf4jLogger, times(1)).debug(anyString());
  }

  @Test
  public void test_debug_withNullIMessageConstant_Args_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class,
        () -> logger.debug((IMessageConstant) null, new Object[] {"arg"}));
  }

  @Test
  public void test_debug_withIMessageConstant_NullArgs_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.debug(mockMessageConstant, (Object[]) null));
  }

  @Test
  public void test_debug_withString_Map() {
    logger.debug("message", Map.of());
    verify(slf4jLogger, times(1)).debug(anyString());
  }

  @Test
  public void test_debug_withNullString_Map_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.debug((String) null, Map.of()));
  }

  @Test
  public void test_debug_withString_NullMap_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.debug("message", (Map<String, ?>) null));
  }

  @Test
  public void test_debug_withIMessageConstant_Map() {
    logger.debug(mockMessageConstant, Map.of());
    verify(slf4jLogger, times(1)).debug(anyString());
  }

  @Test
  public void test_debug_withNullIMessageConstant_Map_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.debug((IMessageConstant) null, Map.of()));
  }

  @Test
  public void test_debug_withIMessageConstant_NullMap_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.debug(mockMessageConstant, (Map<String, ?>) null));
  }

  @Test
  public void test_debug_withString_List() {
    logger.debug("message", List.of());
    verify(slf4jLogger, times(1)).debug(anyString());
  }

  @Test
  public void test_debug_withNullString_List_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.debug((String) null, List.of()));
  }

  @Test
  public void test_debug_withString_NullList_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.debug("message", (List<?>) null));
  }

  @Test
  public void test_debug_withIMessageConstant_List() {
    logger.debug(mockMessageConstant, List.of());
    verify(slf4jLogger, times(1)).debug(anyString());
  }

  @Test
  public void test_debug_withNullIMessageConstant_List_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.debug((IMessageConstant) null, List.of()));
  }

  @Test
  public void test_debug_withIMessageConstant_NullList_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.debug(mockMessageConstant, (List<?>) null));
  }

  @Test
  public void test_debug_withThrowable() {
    logger.debug(new RuntimeException());
    verify(slf4jLogger, times(1)).debug(anyString());
  }

  @Test
  public void test_debug_withNullThrowable_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.debug((Throwable) null));
  }

  @Test
  public void test_debug_withString_Throwable() {
    logger.debug("message", new RuntimeException());
    verify(slf4jLogger, times(1)).debug(anyString());
  }

  @Test
  public void test_debug_withNullString_Throwable_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.debug((String) null, new RuntimeException()));
  }

  @Test
  public void test_debug_withString_NullThrowable_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.debug("message", (Throwable) null));
  }

  @Test
  public void test_debug_withIMessageConstant_Throwable() {
    logger.debug(mockMessageConstant, new RuntimeException());
    verify(slf4jLogger, times(1)).debug(anyString());
  }

  @Test
  public void test_debug_withNullIMessageConstant_Throwable_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class,
        () -> logger.debug((IMessageConstant) null, new RuntimeException()));
  }

  @Test
  public void test_debug_withIMessageConstant_NullThrowable_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.debug(mockMessageConstant, (Throwable) null));
  }

  @Test
  public void test_debug_withILoggerEnabledException() {
    logger.debug(mockException);
    verify(slf4jLogger, times(1)).debug(anyString());
  }

  @Test
  public void test_debug_withNullILoggerEnabledException_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.debug((ILoggerEnabledException) null));
  }

  @Test
  public void test_debug_withString_ILoggerEnabledException() {
    logger.debug("message", mockException);
    verify(slf4jLogger, times(1)).debug(anyString());
  }

  @Test
  public void test_debug_withNullString_ILoggerEnabledException_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.debug((String) null, mockException));
  }

  @Test
  public void test_debug_withString_NullILoggerEnabledException_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.debug("message", (ILoggerEnabledException) null));
  }

  @Test
  public void test_debug_withIMessageConstant_ILoggerEnabledException() {
    logger.debug(mockMessageConstant, mockException);
    verify(slf4jLogger, times(1)).debug(anyString());
  }

  @Test
  public void test_debug_withNullIMessageConstant_ILoggerEnabledException_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.debug((IMessageConstant) null, mockException));
  }

  @Test
  public void test_debug_withIMessageConstant_NullILoggerEnabledException_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class,
        () -> logger.debug(mockMessageConstant, (ILoggerEnabledException) null));
  }

  @Test
  public void test_warn_withString() {
    logger.warn("message");
    verify(slf4jLogger, times(1)).warn(anyString());
  }

  @Test
  public void test_warn_withNullString_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.warn((String) null));
  }

  @Test
  public void test_warn_withIMessageConstant() {
    logger.warn(mockMessageConstant);
    verify(slf4jLogger, times(1)).warn(anyString());
  }

  @Test
  public void test_warn_withNullIMessageConstant_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.warn((IMessageConstant) null));
  }

  @Test
  public void test_warn_withString_Args() {
    logger.warn("message", new Object[] {"arg"});
    verify(slf4jLogger, times(1)).warn(anyString());
  }

  @Test
  public void test_warn_withNullString_Args_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.warn((String) null, new Object[] {"arg"}));
  }

  @Test
  public void test_warn_withString_NullArgs_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.warn("message", (Object[]) null));
  }

  @Test
  public void test_warn_withIMessageConstant_Args() {
    logger.warn(mockMessageConstant, new Object[] {"arg"});
    verify(slf4jLogger, times(1)).warn(anyString());
  }

  @Test
  public void test_warn_withNullIMessageConstant_Args_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class,
        () -> logger.warn((IMessageConstant) null, new Object[] {"arg"}));
  }

  @Test
  public void test_warn_withIMessageConstant_NullArgs_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.warn(mockMessageConstant, (Object[]) null));
  }

  @Test
  public void test_warn_withThrowable() {
    logger.warn(new RuntimeException());
    verify(slf4jLogger, times(1)).warn(anyString());
  }

  @Test
  public void test_warn_withNullThrowable_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.warn((Throwable) null));
  }

  @Test
  public void test_warn_withString_Throwable() {
    logger.warn("message", new RuntimeException());
    verify(slf4jLogger, times(1)).warn(anyString());
  }

  @Test
  public void test_warn_withNullString_Throwable_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.warn((String) null, new RuntimeException()));
  }

  @Test
  public void test_warn_withString_NullThrowable_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.warn("message", (Throwable) null));
  }

  @Test
  public void test_warn_withIMessageConstant_Throwable() {
    logger.warn(mockMessageConstant, new RuntimeException());
    verify(slf4jLogger, times(1)).warn(anyString());
  }

  @Test
  public void test_warn_withNullIMessageConstant_Throwable_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class,
        () -> logger.warn((IMessageConstant) null, new RuntimeException()));
  }

  @Test
  public void test_warn_withIMessageConstant_NullThrowable_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.warn(mockMessageConstant, (Throwable) null));
  }

  @Test
  public void test_warn_withILoggerEnabledException() {
    logger.warn(mockException);
    verify(slf4jLogger, times(1)).warn(anyString());
  }

  @Test
  public void test_warn_withNullILoggerEnabledException_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.warn((ILoggerEnabledException) null));
  }

  @Test
  public void test_warn_withString_ILoggerEnabledException() {
    logger.warn("message", mockException);
    verify(slf4jLogger, times(1)).warn(anyString());
  }

  @Test
  public void test_warn_withNullString_ILoggerEnabledException_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.warn((String) null, mockException));
  }

  @Test
  public void test_warn_withString_NullILoggerEnabledException_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.warn("message", (ILoggerEnabledException) null));
  }

  @Test
  public void test_warn_withIMessageConstant_ILoggerEnabledException() {
    logger.warn(mockMessageConstant, mockException);
    verify(slf4jLogger, times(1)).warn(anyString());
  }

  @Test
  public void test_warn_withNullIMessageConstant_ILoggerEnabledException_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.warn((IMessageConstant) null, mockException));
  }

  @Test
  public void test_warn_withIMessageConstant_NullILoggerEnabledException_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class,
        () -> logger.warn(mockMessageConstant, (ILoggerEnabledException) null));
  }

  @Test
  public void test_trace_withString() {
    logger.trace("message");
    verify(slf4jLogger, times(1)).trace(anyString());
  }

  @Test
  public void test_trace_withNullString_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.trace((String) null));
  }

  @Test
  public void test_trace_withIMessageConstant() {
    logger.trace(mockMessageConstant);
    verify(slf4jLogger, times(1)).trace(anyString());
  }

  @Test
  public void test_trace_withNullIMessageConstant_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.trace((IMessageConstant) null));
  }

  @Test
  public void test_trace_withString_Args() {
    logger.trace("message", new Object[] {"arg"});
    verify(slf4jLogger, times(1)).trace(anyString());
  }

  @Test
  public void test_trace_withNullString_Args_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.trace((String) null, new Object[] {"arg"}));
  }

  @Test
  public void test_trace_withString_NullArgs_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.trace("message", (Object[]) null));
  }

  @Test
  public void test_trace_withIMessageConstant_Args() {
    logger.trace(mockMessageConstant, new Object[] {"arg"});
    verify(slf4jLogger, times(1)).trace(anyString());
  }

  @Test
  public void test_trace_withNullIMessageConstant_Args_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class,
        () -> logger.trace((IMessageConstant) null, new Object[] {"arg"}));
  }

  @Test
  public void test_trace_withIMessageConstant_NullArgs_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.trace(mockMessageConstant, (Object[]) null));
  }

  @Test
  public void test_trace_withThrowable() {
    logger.trace(new RuntimeException());
    verify(slf4jLogger, times(1)).trace(anyString());
  }

  @Test
  public void test_trace_withNullThrowable_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.trace((Throwable) null));
  }

  @Test
  public void test_trace_withString_Throwable() {
    logger.trace("message", new RuntimeException());
    verify(slf4jLogger, times(1)).trace(anyString());
  }

  @Test
  public void test_trace_withNullString_Throwable_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.trace((String) null, new RuntimeException()));
  }

  @Test
  public void test_trace_withString_NullThrowable_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.trace("message", (Throwable) null));
  }

  @Test
  public void test_trace_withIMessageConstant_Throwable() {
    logger.trace(mockMessageConstant, new RuntimeException());
    verify(slf4jLogger, times(1)).trace(anyString());
  }

  @Test
  public void test_trace_withNullIMessageConstant_Throwable_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class,
        () -> logger.trace((IMessageConstant) null, new RuntimeException()));
  }

  @Test
  public void test_trace_withIMessageConstant_NullThrowable_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.trace(mockMessageConstant, (Throwable) null));
  }

  @Test
  public void test_trace_withILoggerEnabledException() {
    logger.trace(mockException);
    verify(slf4jLogger, times(1)).trace(anyString());
  }

  @Test
  public void test_trace_withNullILoggerEnabledException_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.trace((ILoggerEnabledException) null));
  }

  @Test
  public void test_trace_withString_ILoggerEnabledException() {
    logger.trace("message", mockException);
    verify(slf4jLogger, times(1)).trace(anyString());
  }

  @Test
  public void test_trace_withNullString_ILoggerEnabledException_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.trace((String) null, mockException));
  }

  @Test
  public void test_trace_withString_NullILoggerEnabledException_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.trace("message", (ILoggerEnabledException) null));
  }

  @Test
  public void test_trace_withIMessageConstant_ILoggerEnabledException() {
    logger.trace(mockMessageConstant, mockException);
    verify(slf4jLogger, times(1)).trace(anyString());
  }

  @Test
  public void test_trace_withNullIMessageConstant_ILoggerEnabledException_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.trace((IMessageConstant) null, mockException));
  }

  @Test
  public void test_trace_withIMessageConstant_NullILoggerEnabledException_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class,
        () -> logger.trace(mockMessageConstant, (ILoggerEnabledException) null));
  }

  @Test
  public void test_wtf_withString() {
    logger.wtf("message");
    verify(slf4jLogger, times(1)).error(anyString());
  }

  @Test
  public void test_wtf_withNullString_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.wtf((String) null));
  }

  @Test
  public void test_wtf_withIMessageConstant() {
    logger.wtf(mockMessageConstant);
    verify(slf4jLogger, times(1)).error(anyString());
  }

  @Test
  public void test_wtf_withNullIMessageConstant_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.wtf((IMessageConstant) null));
  }

  @Test
  public void test_wtf_withString_Args() {
    logger.wtf("message", new Object[] {"arg"});
    verify(slf4jLogger, times(1)).error(anyString());
  }

  @Test
  public void test_wtf_withNullString_Args_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.wtf((String) null, new Object[] {"arg"}));
  }

  @Test
  public void test_wtf_withString_NullArgs_shouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> logger.wtf("message", (Object[]) null));
  }

  @Test
  public void test_wtf_withIMessageConstant_Args() {
    logger.wtf(mockMessageConstant, new Object[] {"arg"});
    verify(slf4jLogger, times(1)).error(anyString());
  }

  @Test
  public void test_wtf_withNullIMessageConstant_Args_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class,
        () -> logger.wtf((IMessageConstant) null, new Object[] {"arg"}));
  }

  @Test
  public void test_wtf_withIMessageConstant_NullArgs_shouldThrowNPE() {
    assertThrows(
        NullPointerException.class, () -> logger.wtf(mockMessageConstant, (Object[]) null));
  }
}
