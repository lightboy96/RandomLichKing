package com.gdn.randomlichking;

import com.gdn.randomlichking.audio.SoundPlayer;
import com.gdn.randomlichking.audio.SoundPlayerImpl;
import com.gdn.randomlichking.service.logger.ConsoleLogger;
import com.gdn.randomlichking.service.logger.Logger;
import com.gdn.randomlichking.service.printer.MessagePrinter;
import com.gdn.randomlichking.service.printer.UiAppender;
import com.gdn.randomlichking.service.scheduledtasks.CriticalHitTask;
import com.gdn.randomlichking.service.scheduledtasks.NewYearTask;
import com.gdn.randomlichking.ui.LichKingUi;

import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Timer;


public class RandomLichKing {
    private static boolean starting;
    private static boolean exiting;
    private static boolean newYear;

    public static void setStarting(boolean starting) {
        RandomLichKing.starting = starting;
    }

    public static void setExiting(boolean exiting) {
        RandomLichKing.exiting = exiting;
    }

    public static void setNewYear(boolean newYear) {
        RandomLichKing.newYear = newYear;
    }

    public static void main(String[] args) {

        setStarting(true);
        setExiting(false);
        setNewYear(false);

        // Prod wait times
        /*int minWait = 300000; // 5 min
        int maxWait = 1200000; // 20 min*/

        // Test wait times
        int minWait = 10000; // 10 sec
        int maxWait = 30000; // 30 sec

        LichKingUi ui = new LichKingUi();
        SoundPlayer soundPlayer = new SoundPlayerImpl();
        UiAppender appender = new UiAppender(ui);
        MessagePrinter messagePrinter = new MessagePrinter(appender, soundPlayer, ui);
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
            Thread criticalHitThread = scheduleCriticalHit(logger, messagePrinter);
            criticalHitThread.start();

            while (!exiting) {
                if (!newYear) {
                    messagePrinter.printDormantMessage();
                }

                sleepRandomTime(maxWait, minWait);

                if (!exiting && !newYear) {
                    messagePrinter.printYellMessage();
                }
            }
        } catch (Exception exception) {
            logger.logError(exception.getMessage());
        }
    }

    private static void sleepRandomTime(int maxWait, int minWait) throws InterruptedException {
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
                Date testingDate = newYearDateFormatter.parse("2023-12-22 20:25:50");

                //Production Date:
                //Date newYearDate = newYearDateFormatter.parse(newYearDateString);

                Timer timer = new Timer();
                timer.schedule(new NewYearTask(ui, soundPlayer, logger, messagePrinter), testingDate /*newYearDate*/);
            } catch (ParseException ex) {
                logger.logError(ex.getMessage());
            }
        });
        return newYearThread;
    }

    private static Thread scheduleCriticalHit(Logger logger, MessagePrinter messagePrinter) {
        Thread criticalHitThread;
        criticalHitThread = new Thread(() -> {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new CriticalHitTask(logger, messagePrinter), 3600000, 7200000); //setting it to play once in an hour
        });
        return criticalHitThread;
    }

    private static void KillButtonActionPerformed(MessagePrinter messagePrinter, Logger logger) {
        Thread exitThread = new Thread(() -> {
            try {
                setExiting(true);
                messagePrinter.printKilledMessage();

                System.exit(0);

            } catch (Exception ex) {
                logger.logError(ex.getMessage());
            }
        });
        if (!starting && !newYear) exitThread.start();
    }
}
