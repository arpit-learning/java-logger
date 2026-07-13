package dev.arpit.pm1.logger.core;

import dev.arpit.pm1.logger.levels.IJsonLogger;

public interface IBaseLogger {
  IJsonLogger info();

  IJsonLogger debug();

  IJsonLogger trace();

  IJsonLogger warn();

  IJsonLogger error();

  IJsonLogger wtf();
}
