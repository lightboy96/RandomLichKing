package com.gdn.randomlichking.service.scheduledtasks;

import com.gdn.randomlichking.RandomLichKing;
import com.gdn.randomlichking.service.logger.Logger;
import com.gdn.randomlichking.service.printer.MessagePrinter;

import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class ValkyrTask extends TimerTask {
    private final MessagePrinter messagePrinter;
    private final Logger logger;
    private final Semaphore mutex;

    public ValkyrTask(MessagePrinter messagePrinter, Logger logger, Semaphore mutex) {
        this.messagePrinter = messagePrinter;
        this.logger = logger;
        this.mutex = mutex;
    }


    @Override
    public void run() {
        try {
            if (!RandomLichKing.isNewYear() && !RandomLichKing.isExiting()) {
                messagePrinter.printValkyrMessage();
                Thread.sleep(2000);
                messagePrinter.printDormantMessage();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.logError(e.getMessage());

        } catch (Exception e) {
            logger.logError(e.getMessage());
        } finally {
            mutex.release();
        }
    }
}
