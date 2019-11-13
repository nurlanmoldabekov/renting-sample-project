package com.kiko.rent.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationUtil {
    private static final Logger logger = LoggerFactory.getLogger(NotificationUtil.class);
    public static void notifyUser(String email, String text){
        logger.info("Notifying user {} with text {}", email, text);
    }
}
