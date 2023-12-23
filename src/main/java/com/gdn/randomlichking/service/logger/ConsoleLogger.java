package com.gdn.randomlichking.service.logger;

import java.time.LocalDateTime;

public class ConsoleLogger implements Logger {
    @Override
    public void logInfo(String message) {
        logMessage(message, "INFO");
    }

    @Override
    public void logError(String message) {
        logMessage(message, "ERROR");
    }

    private void logMessage(String message, String type) {
        String entry = String.format("[%s] %s: %s", LocalDateTime.now(), type, message);
        System.out.println(entry);
    }
}
