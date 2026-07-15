package dev.arpit.learning.logger.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class StackTraceUtilTest {
  @Test
  public void test_objectCreation() {
    // act
    StackTraceUtil stackTraceUtil = new StackTraceUtil();

    // assert
    assertNotNull(stackTraceUtil);
  }

  @Test
  public void test_formatStack() {
    // act
    String stack = StackTraceUtil.formatStack();

    // assert
    assertNotNull(stack);
    assertFalse(stack.isEmpty());
  }

  @Test
  public void test_formatStack_withShortStackTrace() throws InterruptedException {
    // We use a custom Thread-overriding run() to minimize the call stack depth.
    // The JVM invokes run() directly, meaning the stack only contains:
    // 0: StackTraceUtil.formatStack()
    // 1: CustomThread.run()
    // Total length = 2, which covers the false branch of `if (stackTraceElements.length > 2)`

    // arrange
    final String[] result = new String[1];

    // act
    Thread t = new Thread(() -> result[0] = StackTraceUtil.formatStack());

    t.start();
    t.join();

    // assert
    assertNotNull(result[0]);
    assertTrue(result[0].startsWith("java.base/java.lang.Thread"));
  }
}
