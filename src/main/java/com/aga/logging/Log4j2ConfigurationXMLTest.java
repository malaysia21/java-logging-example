package com.aga.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;

/**
 * Example of Log4j2 logging with configuration ine log42j.xml file
 */
public class Log4j2ConfigurationXMLTest {

    private static final Logger loggerLog4j2 = LogManager.getLogger();
    private static final String XML_CONFIG_FILE = "./src/main/resources/log4j2.xml";

    public static void main(String[] args) {
        LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
        context.setConfigLocation(new File(XML_CONFIG_FILE).toURI());

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
