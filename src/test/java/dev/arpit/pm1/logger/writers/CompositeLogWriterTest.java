package dev.arpit.pm1.logger.writers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import dev.arpit.pm1.logger.models.LogLevel;
import org.junit.jupiter.api.Test;

public class CompositeLogWriterTest {
  private CompositeLogWriter writer;

  @Test
  public void test_objectCreation() {
    // act
    writer = new CompositeLogWriter();

    // assert
    assertNotNull(writer);
  }

  @Test
  public void test_addWriter_withNullWriter_shouldThrowNPE() {
    // arrange
    writer = new CompositeLogWriter();

    // act & assert
    assertThrows(NullPointerException.class, () -> writer.addWriter(null));
  }

  @Test
  public void test_addWriter_withValidWriter_shouldAddWriter() {
    // arrange
    writer = new CompositeLogWriter();
    ILogWriter mockWriter1 = mock(ILogWriter.class);

    // act
    writer.addWriter(mockWriter1);
  }

  @Test
  public void test_write_delegatesToAllWriters() {
    // arrange
    ILogWriter writer1 = mock(ILogWriter.class);
    ILogWriter writer2 = mock(ILogWriter.class);
    writer = new CompositeLogWriter();
    writer.addWriter(writer1);
    writer.addWriter(writer2);

    // act
    writer.write(LogLevel.INFO, "test message");

    // assert
    verify(writer1).write(LogLevel.INFO, "test message");
    verify(writer2).write(LogLevel.INFO, "test message");
  }

  @Test
  public void test_write_withNullLogLevelAndValidMessage_shouldThrowNPE() {
    // arrange
    writer = new CompositeLogWriter();

    // act & assert
    assertThrows(NullPointerException.class, () -> writer.write(null, "test message"));
  }

  @Test
  public void test_write_withValidLogLevelAndNullMessage_shouldThrowNPE() {
    // arrange
    writer = new CompositeLogWriter();

    // act & assert
    assertThrows(NullPointerException.class, () -> writer.write(LogLevel.DEBUG, null));
  }
}
