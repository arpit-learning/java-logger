package dev.arpit.learning.logger.models;

public enum LoggerConstant implements ILoggerConstant {
  ATTRIBUTE_MESSAGE("__message"),
  ATTRIBUTE_ARGS("__args"),
  ATTRIBUTE_DATA("__data"),
  ATTRIBUTE_LEVEL("__level"),
  ENRICHMENT_ERROR("__enrichment_error"),
  ATTRIBUTE_THREAD_NAME("__thread_name"),
  ATTRIBUTE_CLASS("__class"),
  ATTRIBUTE_TIMESTAMP("__timestamp"),
  ATTRIBUTE_STACK_TRACE("__stacktrace"),
  ATTRIBUTE_EPOCH("__epoch"),
  ATTRIBUTE_ELK_INDEX_KEY("application"),
  ATTRIBUTE_SHORT_CODE("__short_code"),
  ATTRIBUTE_REQUEST("__request"),
  ATTRIBUTE_RESPONSE("__response"),
  ATTRIBUTE_COMPONENT("__component"),
  ATTRIBUTE_INDEX("__attr"),
  ATTRIBUTE_EXCEPTION("__exception"),
  ATTRIBUTE_PRIORITY("__priority"),
  ATTRIBUTE_ALERT_TO_DEV("__alert_dev"),
  ATTRIBUTE_MDC("__mdc"),
  ATTRIBUTE_METHOD("__method"),
  ATTRIBUTE_LINE("__line"),
  ATTRIBUTE_MDC_TRACE_ID("trace_id"),
  ATTRIBUTE_MDC_USER_ID("user_id"),
  ATTRIBUTE_MDC_EMPTY_KEY("empty_key");

  private final String value;

  LoggerConstant(String value) {
    this.value = value;
  }

  @Override
  public String getValue() {
    return value;
  }
}
