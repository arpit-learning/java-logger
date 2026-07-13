package dev.arpit.pm1.logger.core;

import com.google.gson.Gson;
import dev.arpit.pm1.logger.enrichers.*;
import dev.arpit.pm1.logger.levels.*;
import dev.arpit.pm1.logger.models.ILoggerConstant;
import dev.arpit.pm1.logger.models.LogLevel;
import dev.arpit.pm1.logger.serializers.GsonLogSerializer;
import dev.arpit.pm1.logger.serializers.ILogSerializer;
import dev.arpit.pm1.logger.serializers.PrettyLogSerializer;
import dev.arpit.pm1.logger.utils.ExceptionFormatter;
import dev.arpit.pm1.logger.writers.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;

public class LoggerConfiguration {
  @Getter @Setter private static @NonNull String componentName = "";
  @Getter @Setter private static boolean prettyPrintEnabled = false;
  @Getter @Setter private static boolean systemOutLoggingEnabled = false;
  @Getter @Setter private static boolean fileLoggingEnabled = false;
  @Getter @Setter private static @NonNull String fileLoggingPath = "";
  @Getter @Setter private static @NonNull List<ILoggerConstant> mdcKeys = new ArrayList<>();
  @Getter private static @NonNull String dateFormat = "yyyy-MM-dd HH:mm:ss.SSSZ";
  @Getter private static @NonNull FastDateFormat formatter = FastDateFormat.getInstance(dateFormat);
  @Getter private static final LoggerConfiguration instance = new LoggerConfiguration();
  @Getter private final Set<String> excludedClasses = new HashSet<>();
  @Getter private final List<ILogEnricher> enrichers = new ArrayList<>();
  @Setter private Function<Gson, ILogSerializer> serializerFactory;
  @Setter private Function<Logger, ILogWriter> writerFactory;
  @Setter private IJsonLoggerFactory loggerFactory;

  @FunctionalInterface
  public interface IJsonLoggerFactory {
    IJsonLogger create(LogLevel level, Logger logger, Gson gson);
  }

  private LoggerConfiguration() {
    // Initialize defaults
    excludedClasses.addAll(
        Set.of(
            BaseLogger.class.getName(),
            Logger.class.getName(),
            LoggerConfiguration.class.getName(),
            LoggerFactory.class.getName(),
            LogPipeline.class.getName(),
            CallerClassEnricher.class.getName(),
            MdcEnricher.class.getName(),
            ThreadNameEnricher.class.getName(),
            TimestampEnricher.class.getName(),
            AbstractJsonLogger.class.getName(),
            DebugLogger.class.getName(),
            ErrorLogger.class.getName(),
            InfoLogger.class.getName(),
            NoopLogger.class.getName(),
            TraceLogger.class.getName(),
            WarnLogger.class.getName(),
            WtfLogger.class.getName(),
            GsonLogSerializer.class.getName(),
            PrettyLogSerializer.class.getName(),
            ExceptionFormatter.class.getName(),
            CompositeLogWriter.class.getName(),
            FileLogWriter.class.getName(),
            Slf4jLogWriter.class.getName(),
            SystemOutLogWriter.class.getName()));

    enrichers.add(new ThreadNameEnricher());
    enrichers.add(new TimestampEnricher());
    enrichers.add(new CallerClassEnricher(excludedClasses));
    enrichers.add(new MdcEnricher());

    serializerFactory =
        gson -> {
          if (LoggerConfiguration.isPrettyPrintEnabled()) {
            return new PrettyLogSerializer();
          } else {
            return new GsonLogSerializer(gson);
          }
        };

    writerFactory =
        logger -> {
          CompositeLogWriter compositeWriter = new CompositeLogWriter();
          compositeWriter.addWriter(new Slf4jLogWriter(logger));
          if (LoggerConfiguration.isSystemOutLoggingEnabled()) {
            compositeWriter.addWriter(new SystemOutLogWriter());
          }
          if (LoggerConfiguration.isFileLoggingEnabled()) {
            String fileLoggingPath = LoggerConfiguration.getFileLoggingPath();
            if (!fileLoggingPath.trim().isEmpty()) {
              compositeWriter.addWriter(new FileLogWriter());
            }
          }
          return compositeWriter;
        };

    loggerFactory =
        (level, logger, gson) ->
            switch (level) {
              case INFO -> new InfoLogger(logger, gson);
              case DEBUG -> new DebugLogger(logger, gson);
              case TRACE -> new TraceLogger(logger, gson);
              case WARN -> new WarnLogger(logger, gson);
              case ERROR -> new ErrorLogger(logger, gson);
              case WTF -> new WtfLogger(logger, gson);
            };
  }

  public void addExcludedClass(@NonNull String className) {
    this.excludedClasses.add(className);
  }

  public void addEnricher(@NonNull ILogEnricher enricher) {
    this.enrichers.add(enricher);
  }

  public void clearEnrichers() {
    this.enrichers.clear();
  }

  public @NonNull ILogSerializer createSerializer(@NonNull Gson gson) {
    return this.serializerFactory.apply(gson);
  }

  public @NonNull ILogWriter createWriter(@NonNull Logger logger) {
    return this.writerFactory.apply(logger);
  }

  public @NonNull IJsonLogger createLogger(
      @NonNull LogLevel level, @NonNull Logger logger, @NonNull Gson gson) {
    return this.loggerFactory.create(level, logger, gson);
  }

  public static void setDateFormat(@NonNull String dateFormat) {
    LoggerConfiguration.dateFormat = dateFormat;
    LoggerConfiguration.formatter = FastDateFormat.getInstance(dateFormat);
  }
}
