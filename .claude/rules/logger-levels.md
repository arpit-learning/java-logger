---
paths:
  - src/main/java/dev/arpit/learning/logger/levels/**
---

# Logger Levels Conventions

- Extend class `AbstractJsonLogger.java` when adding log levels under `src/main/java/dev/arpit/learning/logger/levels/`.
- Implement interface methods from `IJsonLogger.java` for builder pattern support.
- Override method `log()` to invoke `executePipeline()`.
- Override method `toString()` to invoke `formatMessage()`.
