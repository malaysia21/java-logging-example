package com.aga.logging;

import ch.qos.logback.classic.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Example of logback logging.
 * Logback uses SLF4J as its native interface.
 * Appender in logback.xml: CONSOLE, FILE-ROLLING
 */
public class LogbackTest {

    private static final Logger loggerSlf = LoggerFactory.getLogger(LogbackTest.class);
    private static final ch.qos.logback.classic.Logger loggerLogback = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(LogbackTest.class);
    private static final ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);

    public static void main(String[] args) {
        addToMappedDiagnosticContext();
        logDifferentLevels();
        logIfEnabled();
        produceLogs();

        loggerSlf.debug("Message logged DEBUG = DEBUG");
        loggerLogback.debug("Message logged DEBUG == DEBUG");

        loggerLogback.setLevel(Level.WARN);
        loggerSlf.warn("Message logged WARN == WARN");
        loggerSlf.debug("Message not logged DEBUG < WARN");
        loggerLogback.warn("Message logged WARN == WARN");
        loggerLogback.debug("Message not logged DEBUG < WARN");

        rootLogger.setLevel(Level.ERROR);
        loggerSlf.debug("Message not logged DEBUG > ERROR");
        loggerSlf.error("Message logged ERROR = ERROR");
        loggerLogback.debug("Message not logged DEBUG > ERROR");
        loggerLogback.error("Message logged ERROR = ERROR");
    }

    private static void produceLogs() {
        for (int i =0; i< 100; i++) {
            loggerSlf.debug("Current thread: {} - {}", Thread.currentThread().getName(), Thread.currentThread().getId());
        }
    }

    private static void logIfEnabled() {
        if(loggerSlf.isDebugEnabled()) {
            loggerSlf.debug("Is trace enabled: {}", loggerSlf.isTraceEnabled());
        }
    }

    private static void logDifferentLevels() {
        loggerSlf.trace("Logging Logback - TRACE - lev.1");
        loggerSlf.debug("Logging Logback - DEBUG - lev.2");
        loggerSlf.info("Logging Logback - INFO - lev.3");
        loggerSlf.warn("Logging Logback - WARN - lev.4");
        loggerSlf.error("Logging Logback - ERROR - lev.5");
    }

    private static void addToMappedDiagnosticContext(){
        MDC.put("test-key", "test-value");
    }

}
