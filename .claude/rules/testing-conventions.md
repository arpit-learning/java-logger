---
paths:
  - src/test/java/**
---

# Testing Conventions

- Write unit tests under `src/test/java/dev/arpit/pm1/logger/` structure.
- Enforce JUnit 5 annotations like `@Test`, `@BeforeEach`, and `@AfterEach`.
- Enforce JUnit 5 assertions like `assertNotNull()` and `assertThrows()`.
- Enforce Mockito mocking via `org.mockito.Mockito.mock()` for class `org.slf4j.Logger`.
- Verify call counts using Mockito `verify()` utility.