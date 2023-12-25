package com.gdn.randomlichking.service.scheduledtasks;

import com.gdn.randomlichking.RandomLichKing;
import com.gdn.randomlichking.service.logger.Logger;
import com.gdn.randomlichking.service.printer.MessagePrinter;

import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class CriticalHitTask extends TimerTask {

    private final Logger logger;
    private final MessagePrinter messagePrinter;
    private final Semaphore mutex;

    public CriticalHitTask(Logger logger, MessagePrinter messagePrinter, Semaphore mutex) {
        this.logger = logger;
        this.messagePrinter = messagePrinter;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        try {
            mutex.acquire();
            if (!RandomLichKing.isNewYear() && !RandomLichKing.isExiting()){
                messagePrinter.printCriticalHitMessage();
                Thread.sleep(2000);
                messagePrinter.printDormantMessage();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.logError(e.getMessage());
        } catch (Exception exception) {
            logger.logError(exception.getMessage());
        } finally {
            mutex.release();
        }
    }
}
