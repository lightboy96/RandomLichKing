package com.gdn.randomlichking.service.scheduledtasks;

import com.gdn.randomlichking.service.logger.Logger;
import com.gdn.randomlichking.service.printer.MessagePrinter;

import java.util.TimerTask;

public class CriticalHitTask extends TimerTask {

    private final Logger logger;
    private final MessagePrinter messagePrinter;

    public CriticalHitTask(Logger logger, MessagePrinter messagePrinter) {
        this.logger = logger;
        this.messagePrinter = messagePrinter;
    }

    @Override
    public void run() {
        try {
            messagePrinter.printCriticalHitMessage();

        } catch (Exception exception) {
            logger.logError(exception.getMessage());
        }
    }
}
