package org.kdu.constansts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingSystem {
    private static final Logger logger = LoggerFactory.getLogger(LoggingSystem.class);
    private LoggingSystem(){}
    public static void logError(String log) {
        logger.error(log);
    }

    public static void logInfo(String log) {
        logger.info(log);
    }

    public static void logDebug(String log) {
        logger.debug(log);
    }

    public static void logTrace(String log) {
        logger.trace(log);
    }

    public static void logWarn(String log) {
        logger.warn(log);
    }

}



