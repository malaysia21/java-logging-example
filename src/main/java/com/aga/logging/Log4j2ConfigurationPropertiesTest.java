package com.aga.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Example of Log4j2 logging with configuration ine log42j.properties file
 */
public class Log4j2ConfigurationPropertiesTest {

    private static final Logger loggerLog4j2 = LogManager.getLogger(Log4j2ConfigurationPropertiesTest.class);

    public static void main(String[] args) {
        loggerLog4j2.debug("Logging level: {}", loggerLog4j2.getLevel());
        logDifferentLevels();
    }

    private static void logDifferentLevels() {
        loggerLog4j2.trace("Logging Logback - TRACE - lev.1");
        loggerLog4j2.debug("Logging Logback - DEBUG - lev.2");
        loggerLog4j2.info("Logging Logback - INFO - lev.3");
        loggerLog4j2.warn("Logging Logback - WARN - lev.4");
        loggerLog4j2.error("Logging Logback - ERROR - lev.5");
    }
}
