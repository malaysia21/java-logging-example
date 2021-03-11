package com.aga.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * Example of logback logging.
 * Logback uses SLF4J as its native interface.
 * Appender in logback.xml: CONSOLE_JSON
 */
public class LogbackJsonTest {

    private static final Logger logger = LoggerFactory.getLogger(LogbackJsonTest.class);
    private static final String SESSION_ID = "session-id";
    private static final String TRACKING_ID = "tracking-id";

    public static void main(String[] args) {
        addToMappedDiagnosticContext();
        logDifferentLevels();
        logException();

        logger.debug("Current thread: {} - {}", Thread.currentThread().getName(), Thread.currentThread().getId());
    }

    private static void logException() {
        try {
            throw new IllegalArgumentException("Illegal argument");
        } catch (Exception ex) {
            logger.error("Exception occurred", ex);
        }
    }

    private static void logDifferentLevels() {
        logger.trace("Logging Logback - TRACE - lev.1");
        logger.debug("Logging Logback - DEBUG - lev.2");
        logger.info("Logging Logback - INFO - lev.3");
        logger.warn("Logging Logback - WARN - lev.4");
        logger.error("Logging Logback - ERROR - lev.5");
    }

    private static void addToMappedDiagnosticContext(){
        MDC.put(SESSION_ID, UUID.randomUUID().toString());
        MDC.put(TRACKING_ID, UUID.randomUUID().toString());
    }

}
