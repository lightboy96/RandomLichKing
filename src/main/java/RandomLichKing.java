import audio.SoundPlayer;
import audio.SoundPlayerImpl;
import service.NewYearTask;
import service.logger.ConsoleLogger;
import service.logger.Logger;
import service.printer.MessagePrinter;
import service.printer.UiAppender;
import ui.LichKingUi;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Timer;


public class RandomLichKing extends JFrame {
    //TODO: ADHERE TO OOP AND SOLID. THIS CLASS SHOULD ONLY HAVE THE main method
    static DateFormat newYearDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static boolean starting;
    private static boolean exiting;
    private static boolean newYear;

    public static void main(String[] args) {

        starting = true;
        exiting = false;
        newYear = false;

        // Prod wait times
        /*int minWait = 300000; // 5 min
        int maxWait = 1200000; // 20 min*/

        // Test wait times
        int minWait = 10000; // 10 sec
        int maxWait = 30000; // 30 sec

        LichKingUi ui = new LichKingUi();
        SoundPlayerImpl soundPlayer = new SoundPlayerImpl();
        UiAppender appender = new UiAppender(ui);
        MessagePrinter messagePrinter = new MessagePrinter(appender, soundPlayer, ui);
        Logger logger = new ConsoleLogger();

        EventQueue.invokeLater(() -> ui.setVisible(true));
        ui.getKillButton().addActionListener(e -> KillButtonActionPerformed(messagePrinter, logger));

        try {
            messagePrinter.printWelcomeMessage();
            messagePrinter.printDeathKnightsMessage();

            Thread newYearThread = getNewYearThread(ui, soundPlayer, logger, messagePrinter);
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
            logger.logError(exception.getMessage());
        }
    }

    private static void getRandomYellWaitTime(int maxWait, int minWait) throws InterruptedException {
        int random = new Random().nextInt((maxWait - minWait) + 1) + minWait;
        Thread.sleep(random);
    }

    //TODO: implement this into the main method and make it yell the message from the MessagePrinter class
    private static void getRandomCritTime(Logger logger) {
        int minWait = 3600000;
        int maxWait = 7200000;
        int randomCritTime = new Random().nextInt((maxWait - minWait) + 1) + minWait;
        try {
            Thread.sleep(randomCritTime);
        } catch (InterruptedException e) {
            logger.logError(e.getMessage());
        }
    }

    private static Thread getNewYearThread(LichKingUi ui, SoundPlayer soundPlayer, Logger logger, MessagePrinter messagePrinter) {
        Thread newYearThread;
        newYearThread = new Thread(() -> {
            try {
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                String newYearDateString = currentYear + "-12-31 23:59:40";

                //Testing Date:
                Date testingDate = newYearDateFormatter.parse("2023-12-21 17:38:40");

                //Production Date:
                Date newYearDate = newYearDateFormatter.parse(newYearDateString);

                Timer timer = new Timer();
                timer.schedule(new NewYearTask(ui, soundPlayer, logger, messagePrinter), testingDate /*newYearDate*/);
            } catch (ParseException ex) {
                logger.logError(ex.getMessage());
            }
        });
        return newYearThread;
    }

    private static void KillButtonActionPerformed(MessagePrinter messagePrinter, Logger logger) {
        Thread threadExit = new Thread(() -> {
            try {
                exiting = true;
                messagePrinter.printKilledMessage();

                System.exit(0);

            } catch (Exception ex) {
                logger.logError(ex.getMessage());
            }
        });

        if (!starting && !newYear)
            threadExit.start();
    }
}
