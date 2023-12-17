import audio.SoundPlayer;
import service.UiAppender;
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
    private static final SoundPlayer soundPlayer = new SoundPlayer();
    private static final UiAppender uiAppender = new UiAppender();
    static DateFormat newYearDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static boolean starting;
    private static boolean exiting;
    private static boolean newYear;

    // private static javax.swing.JTextPane jConsole;

    public RandomLichKing() {
    }

    /*private static void appendToPane(LichKingUi ui, String msg, Color c) {
        StyleContext styleContext = StyleContext.getDefaultStyleContext();
        AttributeSet attributeSet = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.FontFamily, "Lucida Console");
        attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = ui.getConsoleTextPane().getDocument().getLength();
        ui.getConsoleTextPane().setCaretPosition(len);
        ui.getConsoleTextPane().setCharacterAttributes(attributeSet, false);
        ui.getConsoleTextPane().replaceSelection(msg);
    }*/

    /*static void playSound(String soundFile) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File file = new File("./" + soundFile);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.toURI().toURL());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }*/

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
        ui.getKillButton().addActionListener(e -> KillButtonActionPerformed(ui, e));

        java.awt.EventQueue.invokeLater(() -> ui.setVisible(true));
        SoundPlayer soundPlayer = new SoundPlayer();
        UiAppender appender = new UiAppender();

        try {
            printWelcomeMessage(appender, ui, soundPlayer);
            printDeathKnightsMessage(appender, ui, soundPlayer);

            Thread newYearThread = getNewYearThread(ui);
            newYearThread.start();

            while (!exiting) {
                if (!newYear) {
                    printDormantMessage(appender, ui);
                }

                getRandomWaitTime(maxWait, minWait);

                if (!exiting && !newYear) {
                    printYellMessage(appender, ui, soundPlayer);
                }
            }
        } catch (Exception exception) {
            Logger.getLogger(RandomLichKing.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

    //TODO:MOVE "printMessage" methods to MessagePrinter class
    private static void printWelcomeMessage(UiAppender appender, LichKingUi ui, SoundPlayer soundPlayer) throws InterruptedException {
        Timestamp welcomeTimeStamp = new Timestamp(System.currentTimeMillis());
        appender.appendToPane(ui, "[" + SIMPLE_DATE_FORMAT.format(welcomeTimeStamp) + "] " + "The Lich King says: I suppose a welcome is in order... So welcome, insects, welcome to MY WORLD! \n\n", Color.CYAN);

        soundPlayer.playSound("data/Welcome.wav");
        Thread.sleep(15000);
        starting = false;
    }

    private static void printDeathKnightsMessage(UiAppender appender, LichKingUi ui, SoundPlayer soundPlayer) throws InterruptedException {
        Timestamp deathKnightTimeStamp = new Timestamp(System.currentTimeMillis());
        appender.appendToPane(ui, "[" + SIMPLE_DATE_FORMAT.format(deathKnightTimeStamp) + "]" + "The Lich King orders: Go now! And claim your destiny, Death Knight \n\n", Color.red);
        soundPlayer.playSound("data/DeathKnights.wav");
        Thread.sleep(7000);
    }

    private static void printDormantMessage(UiAppender appender, LichKingUi ui) {
        Timestamp dormantTimeStamp = new Timestamp(System.currentTimeMillis());
        appender.appendToPane(ui, "[" + SIMPLE_DATE_FORMAT.format(dormantTimeStamp) + "] " + "The Lich King is dormant... \n\n", Color.WHITE);
    }

    private static void printYellMessage(UiAppender appender, LichKingUi ui, SoundPlayer soundPlayer) throws InterruptedException {
        Timestamp yellTimeStamp = new Timestamp(System.currentTimeMillis());
        appender.appendToPane(ui, "[" + SIMPLE_DATE_FORMAT.format(yellTimeStamp) + "] " + "The Lich King yells: Frostmourne hungers! \n", Color.red);
        appender.appendToPane(ui, "\n", Color.red);
        soundPlayer.playSound("data/FrostmourneHungers.wav");
        Thread.sleep(7000);
    }

    private static void getRandomWaitTime(int maxWait, int minWait) throws InterruptedException {
        int random = new Random().nextInt((maxWait - minWait) + 1) + minWait;
        Thread.sleep(random);
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

    private static void KillButtonActionPerformed(LichKingUi ui, java.awt.event.ActionEvent evt) {
        Thread threadExit = new Thread(() -> {
            try {
                exiting = true;
                Timestamp exitTimeStamp = new Timestamp(System.currentTimeMillis());
                uiAppender.appendToPane(ui, "[" + SIMPLE_DATE_FORMAT.format(exitTimeStamp) + "] " + "Arthas says: I see... only... darkness... before... me... \n\n", Color.CYAN);
                uiAppender.appendToPane(ui, "\n", Color.CYAN);
                soundPlayer.playSound("data/OnlyDarkness.wav");

                Thread.sleep(9000);

                System.exit(0);
            } catch (Exception ex) {
                Logger.getLogger(RandomLichKing.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        if (!starting && !newYear)
            threadExit.start();
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
                Logger.getLogger(RandomLichKing.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
