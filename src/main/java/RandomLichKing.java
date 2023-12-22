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
        SoundPlayer soundPlayer = new SoundPlayerImpl();
        UiAppender appender = new UiAppender(ui);
        MessagePrinter messagePrinter = new MessagePrinter(appender, soundPlayer, ui, starting);
        Logger logger = new ConsoleLogger();
        DateFormat newYearDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        EventQueue.invokeLater(() -> ui.setVisible(true));
        ui.getKillButton().addActionListener(e -> KillButtonActionPerformed(messagePrinter, logger));
        runApplication(messagePrinter, ui, soundPlayer, logger, newYearDateFormatter, maxWait, minWait);

    }

    private static void runApplication(MessagePrinter messagePrinter, LichKingUi ui, SoundPlayer soundPlayer, Logger logger, DateFormat newYearDateFormatter, int maxWait, int minWait) {
        try {
            messagePrinter.printWelcomeMessage();
            messagePrinter.printDeathKnightsMessage();

            Thread newYearThread = getNewYearThread(ui, soundPlayer, logger, messagePrinter, newYearDateFormatter);
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

    private static Thread getNewYearThread(LichKingUi ui, SoundPlayer soundPlayer, Logger logger, MessagePrinter messagePrinter, DateFormat newYearDateFormatter) {
        Thread newYearThread;
        newYearThread = new Thread(() -> {
            try {
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                String newYearDateString = currentYear + "-12-31 23:59:40";

                //Testing Date:
                Date testingDate = newYearDateFormatter.parse("2023-12-21 17:38:40");

                //Production Date:
                //Date newYearDate = newYearDateFormatter.parse(newYearDateString);

                Timer timer = new Timer();
                timer.schedule(new NewYearTask(ui, soundPlayer, logger, messagePrinter, newYear), testingDate /*newYearDate*/);
            } catch (ParseException ex) {
                logger.logError(ex.getMessage());
            }
        });
        return newYearThread;
    }

    private static void KillButtonActionPerformed(MessagePrinter messagePrinter, Logger logger) {
        Thread exitThread = new Thread(() -> {
            try {
                exiting = true;
                messagePrinter.printKilledMessage();

                System.exit(0);

            } catch (Exception ex) {
                logger.logError(ex.getMessage());
            }
        });
        if (!starting && !newYear)
            exitThread.start();
    }
}
