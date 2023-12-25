package com.gdn.randomlichking.service.scheduledtasks;

import com.gdn.randomlichking.RandomLichKing;
import com.gdn.randomlichking.audio.SoundPlayer;
import com.gdn.randomlichking.service.logger.Logger;
import com.gdn.randomlichking.service.printer.MessagePrinter;
import com.gdn.randomlichking.ui.LichKingUi;

import javax.swing.*;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class NewYearTask extends TimerTask {
    private final LichKingUi ui;
    private final SoundPlayer soundPlayer;
    private final Logger logger;
    private final MessagePrinter messagePrinter;
    private final Semaphore mutex;

    public NewYearTask(LichKingUi ui, SoundPlayer soundPlayer, Logger logger, MessagePrinter messagePrinter, Semaphore mutex) {
        this.ui = ui;
        this.soundPlayer = soundPlayer;
        this.logger = logger;
        this.messagePrinter = messagePrinter;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        try {
            mutex.acquire();
            RandomLichKing.setNewYear(true);
            Thread.sleep(10000);

            messagePrinter.printNewYearCountDown();
            messagePrinter.printNewYearReward();

            displayFireWorks();

            RandomLichKing.setNewYear(false);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.logError(e.getMessage());
        } catch (Exception ex) {
            logger.logError(ex.getMessage());
        } finally {
            mutex.release();
        }
    }

    private void displayFireWorks() throws InterruptedException {
        ui.getGifLabel().setIcon(new ImageIcon("data/images/Fireworks.gif"));
        soundPlayer.playSound("data/sound effects/Fireworks.wav");
        Thread.sleep(60000);
        ui.getGifLabel().setIcon(new ImageIcon("data/images/LichKingAnimatedWallpaper.gif"));
    }

}
