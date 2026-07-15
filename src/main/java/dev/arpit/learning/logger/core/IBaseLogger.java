package dev.arpit.learning.logger.core;

import dev.arpit.learning.logger.levels.IJsonLogger;

public interface IBaseLogger {
  IJsonLogger info();

  IJsonLogger debug();

  IJsonLogger trace();

  IJsonLogger warn();

  IJsonLogger error();

  IJsonLogger wtf();
}
