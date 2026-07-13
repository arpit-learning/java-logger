package dev.arpit.pm1.logger.serializers;

import dev.arpit.pm1.logger.models.LogEvent;
import lombok.NonNull;

public interface ILogSerializer {
  String serialize(@NonNull LogEvent event);
}
