package com.gdn.randomlichking.service;

import com.gdn.randomlichking.RandomLichKing;
import com.gdn.randomlichking.audio.SoundPlayer;
import com.gdn.randomlichking.service.logger.Logger;
import com.gdn.randomlichking.service.printer.MessagePrinter;
import com.gdn.randomlichking.ui.LichKingUi;

import javax.swing.*;
import java.util.TimerTask;

public class NewYearTask extends TimerTask {
    private final LichKingUi ui;
    private final SoundPlayer soundPlayer;
    private final Logger logger;
    private final MessagePrinter messagePrinter;

    public NewYearTask(LichKingUi ui, SoundPlayer soundPlayer, Logger logger, MessagePrinter messagePrinter) {
        this.ui = ui;
        this.soundPlayer = soundPlayer;
        this.logger = logger;
        this.messagePrinter = messagePrinter;
    }

    @Override
    public void run() {
        try {
            RandomLichKing.setNewYear(true);
            Thread.sleep(10000);

            messagePrinter.printNewYearCountDown();
            messagePrinter.printAchievement();
            displayFireWorks();
            RandomLichKing.setNewYear(false);


        } catch (Exception ex) {
            logger.logError(ex.getMessage());
        }
    }

    private void displayFireWorks() throws InterruptedException {
        ui.getGifLabel().setIcon(new ImageIcon("data/Fireworks.gif"));
        soundPlayer.playSound("data/Fireworks.wav");
        Thread.sleep(60000);
        ui.getGifLabel().setIcon(new ImageIcon("data/LichKingAnimatedWallpaper.gif"));
    }

}
