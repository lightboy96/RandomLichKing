package service;

import audio.SoundPlayer;
import service.logger.Logger;
import service.printer.MessagePrinter;
import ui.LichKingUi;

import javax.swing.*;
import java.util.TimerTask;

public class NewYearTask extends TimerTask {
    private final LichKingUi ui;
    private final SoundPlayer soundPlayer;
    private final Logger logger;
    private boolean newYear;
    private final MessagePrinter messagePrinter;

    public NewYearTask(LichKingUi ui, SoundPlayer soundPlayer, Logger logger, MessagePrinter messagePrinter) {
        this.ui = ui;
        this.soundPlayer = soundPlayer;
        this.logger = logger;
        this.messagePrinter = messagePrinter;
        this.newYear = false;
    }

    @Override
    public void run() {
        try {
            newYear = true;
            Thread.sleep(10000);

            messagePrinter.printNewYearCountDown();
            messagePrinter.printAchievement();
            displayFireWorks();

            newYear = false;

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
