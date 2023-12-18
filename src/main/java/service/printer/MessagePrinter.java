package service.printer;

import audio.SoundPlayer;
import ui.LichKingUi;

import java.awt.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class MessagePrinter {
    private final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm");
    private boolean starting;

    public void printWelcomeMessage(UiAppender appender, LichKingUi ui, SoundPlayer soundPlayer) throws InterruptedException {
        Timestamp welcomeTimeStamp = new Timestamp(System.currentTimeMillis());
        appender.appendToPane(ui, "[" + SIMPLE_DATE_FORMAT.format(welcomeTimeStamp) + "] " + "The Lich King says: I suppose a welcome is in order... So welcome, insects, welcome to MY WORLD! \n\n", Color.CYAN);

        soundPlayer.playSound("data/Welcome.wav");
        Thread.sleep(15000);
        starting = false;
    }

    public void printDeathKnightsMessage(UiAppender appender, LichKingUi ui, SoundPlayer soundPlayer) throws InterruptedException {
        Timestamp deathKnightTimeStamp = new Timestamp(System.currentTimeMillis());
        appender.appendToPane(ui, "[" + SIMPLE_DATE_FORMAT.format(deathKnightTimeStamp) + "]" + "The Lich King orders: Go now! And claim your destiny, Death Knight \n\n", Color.red);
        soundPlayer.playSound("data/DeathKnights.wav");
        Thread.sleep(7000);
    }

    public void printDormantMessage(UiAppender appender, LichKingUi ui) {
        Timestamp dormantTimeStamp = new Timestamp(System.currentTimeMillis());
        appender.appendToPane(ui, "[" + SIMPLE_DATE_FORMAT.format(dormantTimeStamp) + "] " + "The Lich King is dormant... \n\n", Color.WHITE);
    }

    public void printYellMessage(UiAppender appender, LichKingUi ui, SoundPlayer soundPlayer) throws InterruptedException {
        Timestamp yellTimeStamp = new Timestamp(System.currentTimeMillis());
        appender.appendToPane(ui, "[" + SIMPLE_DATE_FORMAT.format(yellTimeStamp) + "] " + "The Lich King yells: Frostmourne hungers! \n", Color.red);
        appender.appendToPane(ui, "\n", Color.red);
        soundPlayer.playSound("data/FrostmourneHungers.wav");
        Thread.sleep(7000);
    }

    public void printKilledMessage(UiAppender appender, LichKingUi ui, SoundPlayer soundPlayer) throws InterruptedException {
        Timestamp exitTimeStamp = new Timestamp(System.currentTimeMillis());
        appender.appendToPane(ui, "[" + SIMPLE_DATE_FORMAT.format(exitTimeStamp) + "] " + "Arthas says: I see... only... darkness... before... me... \n\n", Color.CYAN);
        appender.appendToPane(ui, "\n", Color.CYAN);
        soundPlayer.playSound("data/OnlyDarkness.wav");
        Thread.sleep(9000);
    }
}
