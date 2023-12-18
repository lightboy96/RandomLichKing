package service;

import audio.SoundPlayer;
import service.logger.Logger;
import service.printer.UiAppender;
import ui.LichKingUi;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimerTask;

public class NewYearTask extends TimerTask {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm");
    private final LichKingUi ui;
    private final SoundPlayer soundPlayer;
    private final UiAppender uiAppender;
    private boolean newYear;
    private final Logger logger;

    public NewYearTask(LichKingUi ui, SoundPlayer soundPlayer, UiAppender uiAppender, Logger logger) {
        this.ui = ui;
        this.soundPlayer = soundPlayer;
        this.uiAppender = uiAppender;
        this.logger = logger;
        this.newYear = false;
    }

    @Override
    public void run() {
        try {
            newYear = true;
            Thread.sleep(10000);
            Timestamp newYearTS = new Timestamp(System.currentTimeMillis());
            soundPlayer.playSound("data/Lich King countdown.wav");
            uiAppender.appendToPane(ui, "[" + SIMPLE_DATE_FORMAT.format(newYearTS) + "] " + "The Lich King says: 10 \n\n", Color.CYAN);
            Thread.sleep(1000);
            uiAppender.appendToPane(ui, "The Lich King says: 9 \n\n", Color.CYAN);
            Thread.sleep(1000);
            uiAppender.appendToPane(ui, "The Lich King says: 8 \n\n", Color.CYAN);
            Thread.sleep(1000);
            uiAppender.appendToPane(ui, "The Lich King says: 7 \n\n", Color.CYAN);
            Thread.sleep(1000);
            uiAppender.appendToPane(ui, "The Lich King says: 6 \n\n", Color.CYAN);
            Thread.sleep(1000);
            uiAppender.appendToPane(ui, "The Lich King says: 5 \n\n", Color.CYAN);
            Thread.sleep(1000);
            uiAppender.appendToPane(ui, "The Lich King says: 4 \n\n", Color.CYAN);
            Thread.sleep(1000);
            uiAppender.appendToPane(ui, "The Lich King says: 3 \n\n", Color.CYAN);
            Thread.sleep(1000);
            uiAppender.appendToPane(ui, "The Lich King says: 2 \n\n", Color.CYAN);
            Thread.sleep(1000);
            uiAppender.appendToPane(ui, "The Lich King says: 1 \n\n", Color.CYAN);
            Thread.sleep(1000);

            uiAppender.appendToPane(ui, "The Lich King says: Happy New Year, insects! \n\n", Color.CYAN);
            uiAppender.appendToPane(ui, "\n", Color.CYAN);
            Thread.sleep(3000);
            uiAppender.appendToPane(ui, "Everyone have earned the achievement: ", Color.WHITE);
            uiAppender.appendToPane(ui, "[Happy New Year, Insects!]\n\n", Color.ORANGE);
            uiAppender.appendToPane(ui, "\n", Color.CYAN);
            soundPlayer.playSound("data/AchievmentSound.wav");
            ui.getGifLabel().setIcon(new ImageIcon("data/Achi/Happy New Year Achievement.png"));
            Thread.sleep(10000);

            ui.getGifLabel().setIcon(new ImageIcon("data/Fireworks.gif"));
            soundPlayer.playSound("data/Fireworks.wav");
            Thread.sleep(60000);
            ui.getGifLabel().setIcon(new ImageIcon("data/LichKingAnimatedWallpaper.gif"));

            newYear = false;

        } catch (Exception ex) {
            logger.logError(ex.getMessage());
        }
    }
}
