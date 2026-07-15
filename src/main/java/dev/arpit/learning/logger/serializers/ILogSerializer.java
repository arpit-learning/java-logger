package dev.arpit.learning.logger.serializers;

import dev.arpit.learning.logger.models.LogEvent;
import lombok.NonNull;

public interface ILogSerializer {
  String serialize(@NonNull LogEvent event);
}
