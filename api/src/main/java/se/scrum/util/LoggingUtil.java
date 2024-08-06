package se.scrum.util;

import org.apache.log4j.Logger;

/**
 * Util class for logging
 * 
 * @author fidde
 *
 */
public class LoggingUtil {

    /**
     * Convinience method for creating two logs
     * 
     * @param log
     * @param message
     * @param obj
     */
    public static void createInfoAndDebugLog(Logger log, String message,
            Object obj) {
        log.info(message + obj);
        log.debug(message + obj);
    }
}
