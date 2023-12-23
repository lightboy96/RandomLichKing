package com.gdn.randomlichking.service.scheduledtasks;

import com.gdn.randomlichking.RandomLichKing;
import com.gdn.randomlichking.service.logger.Logger;
import com.gdn.randomlichking.service.printer.MessagePrinter;

import java.util.Random;
import java.util.TimerTask;

public class ValkyrTask extends TimerTask {
    private final MessagePrinter messagePrinter;
    private final Logger logger;

    public ValkyrTask(MessagePrinter messagePrinter, Logger logger) {
        this.messagePrinter = messagePrinter;
        this.logger = logger;
    }


    @Override
    public void run() {
        try {
            if(!RandomLichKing.isNewYear() && !RandomLichKing.isExiting()){
                messagePrinter.printValkyrMessage();
            }
        }catch (Exception e){
            logger.logError(e.getMessage());
        }
    }
}
