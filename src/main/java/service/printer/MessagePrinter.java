package service.printer;

import audio.SoundPlayer;
import ui.LichKingUi;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class MessagePrinter {
    private final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm");
    private final UiAppender appender;
    private final SoundPlayer soundPlayer;
    private final LichKingUi ui;
    private boolean starting;

    public MessagePrinter(UiAppender appender, SoundPlayer soundPlayer, LichKingUi ui) {
        this.ui = ui;
        this.starting = true;
        this.appender = appender;
        this.soundPlayer = soundPlayer;
    }

    public void printWelcomeMessage() throws InterruptedException {
        Timestamp welcomeTimeStamp = new Timestamp(System.currentTimeMillis());
        appender.appendToPane("[" + SIMPLE_DATE_FORMAT.format(welcomeTimeStamp) + "] " + "The Lich King says: I suppose a welcome is in order... So welcome, insects, welcome to MY WORLD! \n\n", Color.CYAN);

        soundPlayer.playSound("data/Welcome.wav");
        Thread.sleep(15000);
        starting = false;
    }

    public void printDeathKnightsMessage() throws InterruptedException {
        Timestamp deathKnightTimeStamp = new Timestamp(System.currentTimeMillis());
        appender.appendToPane("[" + SIMPLE_DATE_FORMAT.format(deathKnightTimeStamp) + "] " + "The Lich King orders: Go now! And claim your destiny, Death Knight! \n\n", Color.red);
        appender.appendToPane("[Instruction]: Drink now! \n\n", Color.WHITE);
        soundPlayer.playSound("data/DeathKnights.wav");
        Thread.sleep(7000);
    }

    public void printDormantMessage() {
        Timestamp dormantTimeStamp = new Timestamp(System.currentTimeMillis());
        appender.appendToPane("[" + SIMPLE_DATE_FORMAT.format(dormantTimeStamp) + "] " + "The Lich King is dormant... \n\n", Color.WHITE);
    }

    public void printYellMessage() throws InterruptedException {
        Timestamp yellTimeStamp = new Timestamp(System.currentTimeMillis());
        appender.appendToPane("[" + SIMPLE_DATE_FORMAT.format(yellTimeStamp) + "] " + "The Lich King yells: Frostmourne hungers! \n", Color.red);
        appender.appendToPane("\n", Color.red);
        appender.appendToPane("[Instruction]: DRINK! \n\n", Color.WHITE);
        soundPlayer.playSound("data/FrostmourneHungers.wav");
        Thread.sleep(7000);
    }

    public void printKilledMessage() throws InterruptedException {
        Timestamp exitTimeStamp = new Timestamp(System.currentTimeMillis());
        appender.appendToPane("[" + SIMPLE_DATE_FORMAT.format(exitTimeStamp) + "] " + "Arthas says: I see... only... darkness... before... me... \n\n", Color.CYAN);
        appender.appendToPane("\n", Color.CYAN);
        soundPlayer.playSound("data/OnlyDarkness.wav");
        Thread.sleep(9000);
    }

    public void printCriticalHitMessage() throws InterruptedException {
        Timestamp criticalHitTimeStamp = new Timestamp(System.currentTimeMillis());
        appender.appendToPane("[" + SIMPLE_DATE_FORMAT.format(criticalHitTimeStamp) + "] " + "The Lich King roars: FINISH IT!!! \n\n", Color.red);
        appender.appendToPane("[Instruction]: Drain your cup! \n\n", Color.WHITE);
        soundPlayer.playSound("data/Criticalhit.wav");
        Thread.sleep(3000);
    }

    public void printAchievement() throws InterruptedException {
        appender.appendToPane("The Lich King says: Happy New Year, insects! \n\n", Color.CYAN);
        appender.appendToPane("\n", Color.CYAN);
        Thread.sleep(3000);
        appender.appendToPane("Everyone have earned the achievement: ", Color.WHITE);
        appender.appendToPane("[Happy New Year, Insects!]\n\n", Color.ORANGE);
        appender.appendToPane("\n", Color.CYAN);
        soundPlayer.playSound("data/AchievementSound.wav");
        ui.getGifLabel().setIcon(new ImageIcon("data/Achi/Happy New Year Achievement.png"));
        Thread.sleep(10000);
    }

    public void printNewYearCountDown() throws InterruptedException {
        Timestamp newYearTimeStamp = new Timestamp(System.currentTimeMillis());
        soundPlayer.playSound("data/Lich King countdown.wav");
        appender.appendToPane("[" + SIMPLE_DATE_FORMAT.format(newYearTimeStamp) + "] " + "The Lich King says: 10 \n\n", Color.CYAN);
        Thread.sleep(1000);
        appender.appendToPane("The Lich King says: 9 \n\n", Color.CYAN);
        Thread.sleep(1000);
        appender.appendToPane("The Lich King says: 8 \n\n", Color.CYAN);
        Thread.sleep(1000);
        appender.appendToPane("The Lich King says: 7 \n\n", Color.CYAN);
        Thread.sleep(1000);
        appender.appendToPane("The Lich King says: 6 \n\n", Color.CYAN);
        Thread.sleep(1000);
        appender.appendToPane("The Lich King says: 5 \n\n", Color.CYAN);
        Thread.sleep(1000);
        appender.appendToPane("The Lich King says: 4 \n\n", Color.CYAN);
        Thread.sleep(1000);
        appender.appendToPane("The Lich King says: 3 \n\n", Color.CYAN);
        Thread.sleep(1000);
        appender.appendToPane("The Lich King says: 2 \n\n", Color.CYAN);
        Thread.sleep(1000);
        appender.appendToPane("The Lich King says: 1 \n\n", Color.CYAN);
        Thread.sleep(1000);
    }
}
