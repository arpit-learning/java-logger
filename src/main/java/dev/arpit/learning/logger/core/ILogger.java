package dev.arpit.learning.logger.core;

import dev.arpit.learning.logger.models.ILoggerEnabledException;
import dev.arpit.learning.logger.models.IMessageConstant;
import java.util.List;
import java.util.Map;

public interface ILogger extends IBaseLogger {
  void info(String message);

  void info(IMessageConstant messageConstant);

  void info(String message, Object... args);

  void info(IMessageConstant messageConstant, Object... args);

  void error(String message);

  void error(IMessageConstant messageConstant);

  void error(String message, Object... args);

  void error(IMessageConstant messageConstant, Object... args);

  void error(Throwable e);

  void error(String message, Throwable e);

  void error(IMessageConstant messageConstant, Throwable e);

  void error(ILoggerEnabledException e);

  void error(String message, ILoggerEnabledException e);

  void error(IMessageConstant messageConstant, ILoggerEnabledException e);

  void debug(String message);

  void debug(IMessageConstant messageConstant);

  void debug(String message, Object... args);

  void debug(IMessageConstant messageConstant, Object... args);

  void debug(String message, Map<String, ?> map);

  void debug(IMessageConstant messageConstant, Map<String, ?> map);

  void debug(String message, List<?> list);

  void debug(IMessageConstant messageConstant, List<?> list);

  void debug(Throwable e);

  void debug(String message, Throwable e);

  void debug(IMessageConstant messageConstant, Throwable e);

  void debug(ILoggerEnabledException e);

  void debug(String message, ILoggerEnabledException e);

  void debug(IMessageConstant messageConstant, ILoggerEnabledException e);

  void warn(String message);

  void warn(IMessageConstant messageConstant);

  void warn(String message, Object... args);

  void warn(IMessageConstant messageConstant, Object... args);

  void warn(Throwable e);

  void warn(String message, Throwable e);

  void warn(IMessageConstant messageConstant, Throwable e);

  void warn(ILoggerEnabledException e);

  void warn(String message, ILoggerEnabledException e);

  void warn(IMessageConstant messageConstant, ILoggerEnabledException e);

  void trace(String message);

  void trace(IMessageConstant messageConstant);

  void trace(String message, Object... args);

  void trace(IMessageConstant messageConstant, Object... args);

  void trace(Throwable e);

  void trace(String message, Throwable e);

  void trace(IMessageConstant messageConstant, Throwable e);

  void trace(ILoggerEnabledException e);

  void trace(String message, ILoggerEnabledException e);

  void trace(IMessageConstant messageConstant, ILoggerEnabledException e);

  void wtf(String message);

  void wtf(IMessageConstant messageConstant);

  void wtf(String message, Object... args);

  void wtf(IMessageConstant messageConstant, Object... args);
}
