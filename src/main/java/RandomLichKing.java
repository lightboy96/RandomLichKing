import audio.SoundPlayer;
import audio.SoundPlayerImpl;
import service.NewYearTask;
import service.logger.ConsoleLogger;
import service.printer.MessagePrinter;
import service.printer.UiAppender;
import ui.LichKingUi;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RandomLichKing extends JFrame {
    //TODO: ADHERE TO OOP AND SOLID. THIS CLASS SHOULD ONLY HAVE THE main method
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm");
    private static final SoundPlayer soundPlayer = new SoundPlayerImpl();
    private static final LichKingUi ui = new LichKingUi();
    private static final UiAppender uiAppender = new UiAppender(ui);
    private static final service.logger.Logger logger = new ConsoleLogger();
    private static final MessagePrinter messagePrinter = new MessagePrinter(uiAppender, soundPlayer, ui);
    static DateFormat newYearDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static boolean starting;
    private static boolean exiting;
    private static boolean newYear;

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
        ui.getKillButton().addActionListener(e -> KillButtonActionPerformed());

        EventQueue.invokeLater(() -> ui.setVisible(true));
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

                //Testing Date:
                Date testingDate = newYearDateFormatter.parse("2023-12-21 15:01:40");

                //Production Date:
                Date newYearDate = newYearDateFormatter.parse(newYearDateString);

                Timer timer = new Timer();
                timer.schedule(new NewYearTask(ui, soundPlayer, logger, messagePrinter), testingDate /*newYearDate*/);
            } catch (ParseException ex) {
                Logger.getLogger(RandomLichKing.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return newYearThread;
    }

    private static void KillButtonActionPerformed() {
        Thread threadExit = new Thread(() -> {
            try {
                exiting = true;
                printKilledMessage();

                System.exit(0);

            } catch (Exception ex) {
                Logger.getLogger(RandomLichKing.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        if (!starting && !newYear)
            threadExit.start();
    }

    private static void printKilledMessage() throws InterruptedException {
        Timestamp exitTimeStamp = new Timestamp(System.currentTimeMillis());
        uiAppender.appendToPane("[" + SIMPLE_DATE_FORMAT.format(exitTimeStamp) + "] " + "Arthas says: I see... only... darkness... before... me... \n\n", Color.CYAN);
        uiAppender.appendToPane("\n", Color.CYAN);

        soundPlayer.playSound("data/OnlyDarkness.wav");
        Thread.sleep(9000);
    }
}
