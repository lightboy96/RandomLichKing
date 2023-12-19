package service;

import audio.SoundPlayerImpl;
import service.logger.Logger;
import service.printer.UiAppender;
import ui.LichKingUi;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimerTask;

public class NewYearTask extends TimerTask {
    private final SimpleDateFormat SIMPLE_DATE_FORMAT;
    private final LichKingUi ui;
    private final SoundPlayerImpl soundPlayer;
    private final UiAppender uiAppender;
    private final Logger logger;
    private boolean newYear;

    public NewYearTask(SimpleDateFormat SIMPLE_DATE_FORMAT, LichKingUi ui, SoundPlayerImpl soundPlayer, UiAppender uiAppender, Logger logger) {
        this.SIMPLE_DATE_FORMAT = SIMPLE_DATE_FORMAT;
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
            uiAppender.appendToPane("[" + SIMPLE_DATE_FORMAT.format(newYearTS) + "] " + "The Lich King says: 10 \n\n", Color.CYAN);
            Thread.sleep(1000);
            uiAppender.appendToPane("The Lich King says: 9 \n\n", Color.CYAN);
            Thread.sleep(1000);
            uiAppender.appendToPane("The Lich King says: 8 \n\n", Color.CYAN);
            Thread.sleep(1000);
            uiAppender.appendToPane("The Lich King says: 7 \n\n", Color.CYAN);
            Thread.sleep(1000);
            uiAppender.appendToPane("The Lich King says: 6 \n\n", Color.CYAN);
            Thread.sleep(1000);
            uiAppender.appendToPane("The Lich King says: 5 \n\n", Color.CYAN);
            Thread.sleep(1000);
            uiAppender.appendToPane("The Lich King says: 4 \n\n", Color.CYAN);
            Thread.sleep(1000);
            uiAppender.appendToPane("The Lich King says: 3 \n\n", Color.CYAN);
            Thread.sleep(1000);
            uiAppender.appendToPane("The Lich King says: 2 \n\n", Color.CYAN);
            Thread.sleep(1000);
            uiAppender.appendToPane("The Lich King says: 1 \n\n", Color.CYAN);
            Thread.sleep(1000);

            uiAppender.appendToPane("The Lich King says: Happy New Year, insects! \n\n", Color.CYAN);
            uiAppender.appendToPane("\n", Color.CYAN);
            Thread.sleep(3000);
            uiAppender.appendToPane("Everyone have earned the achievement: ", Color.WHITE);
            uiAppender.appendToPane("[Happy New Year, Insects!]\n\n", Color.ORANGE);
            uiAppender.appendToPane("\n", Color.CYAN);
            soundPlayer.playSound("data/AchievementSound.wav");
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
