import audio.SoundPlayerImpl;
import service.printer.MessagePrinter;
import service.printer.UiAppender;
import ui.LichKingUi;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RandomLichKing extends javax.swing.JFrame {
    //TODO: ADHERE TO OOP AND SOLID. THIS CLASS SHOULD ONLY HAVE THE main method
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm");
    private static final SoundPlayerImpl soundPlayer = new SoundPlayerImpl();
    private static final LichKingUi ui = new LichKingUi();
    private static final UiAppender uiAppender = new UiAppender(ui);
    static DateFormat newYearDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static boolean starting;
    private static boolean exiting;
    private static boolean newYear;


    public RandomLichKing() {
    }

    public static void main(String[] args) {

        starting = true;
        exiting = false;
        newYear = false;

        // Prod wait times
        //int minWait = 300000; // 5 min
        //int maxWait = 1200000; // 20 min

        // Test wait times
        int minWait = 10000; // 10 sec
        int maxWait = 30000; // 30 sec

        LichKingUi ui = new LichKingUi();
        ui.getKillButton().addActionListener(e -> KillButtonActionPerformed(ui));

        java.awt.EventQueue.invokeLater(() -> ui.setVisible(true));
        SoundPlayerImpl soundPlayer = new SoundPlayerImpl();
        UiAppender appender = new UiAppender(ui);
        MessagePrinter messagePrinter = new MessagePrinter(appender, soundPlayer, ui);

        try {
            messagePrinter.printWelcomeMessage();
            messagePrinter.printDeathKnightsMessage();

            Thread newYearThread = getNewYearThread(ui);
            newYearThread.start();

            while (!exiting) {
                if (!newYear) {
                    messagePrinter.printDormantMessage();
                }

                getRandomYellWaitTime(maxWait, minWait);

                if (!exiting && !newYear) {
                    messagePrinter.printYellMessage();
                }
            }
        } catch (Exception exception) {
            Logger.getLogger(RandomLichKing.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

    private static void getRandomYellWaitTime(int maxWait, int minWait) throws InterruptedException {
        int random = new Random().nextInt((maxWait - minWait) + 1) + minWait;
        Thread.sleep(random);
    }

    //TODO: implement this into the main method and make it yell the message from the MessagePrinter class
    private static void getRandomCritTime() {
        int minWait = 3600000;
        int maxWait = 7200000;
        int randomCritTime = new Random().nextInt((maxWait - minWait) + 1) + minWait;
        try {
            Thread.sleep(randomCritTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Thread getNewYearThread(LichKingUi ui) {
        Thread newYearThread;
        newYearThread = new Thread(() -> {
            try {
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                String newYearDateString = currentYear + "-12-31 23:59:40";

                Date date = newYearDateFormatter.parse(newYearDateString);
                Timer timer = new Timer();
                timer.schedule(new NewYearTask(ui), date);
            } catch (ParseException ex) {
                Logger.getLogger(RandomLichKing.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return newYearThread;
    }

    private static void KillButtonActionPerformed(LichKingUi ui) {
        Thread threadExit = new Thread(() -> {
            try {
                exiting = true;
                printKilledMessage(ui);

                System.exit(0);
            } catch (Exception ex) {
                Logger.getLogger(RandomLichKing.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        if (!starting && !newYear)
            threadExit.start();
    }

    private static void printKilledMessage(LichKingUi ui) throws InterruptedException {
        Timestamp exitTimeStamp = new Timestamp(System.currentTimeMillis());
        uiAppender.appendToPane("[" + SIMPLE_DATE_FORMAT.format(exitTimeStamp) + "] " + "Arthas says: I see... only... darkness... before... me... \n\n", Color.CYAN);
        uiAppender.appendToPane("\n", Color.CYAN);
        soundPlayer.playSound("data/OnlyDarkness.wav");
        Thread.sleep(9000);
    }

    private static class NewYearTask extends TimerTask { //TODO: Move to own class
        private final LichKingUi ui;

        public NewYearTask(LichKingUi ui) {
            this.ui = ui;
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
                Logger.getLogger(RandomLichKing.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
