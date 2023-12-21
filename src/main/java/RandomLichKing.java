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
        service.logger.Logger logger = new ConsoleLogger();

        EventQueue.invokeLater(() -> ui.setVisible(true));
        ui.getKillButton().addActionListener(e -> KillButtonActionPerformed(messagePrinter));

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

    private static Thread getNewYearThread(LichKingUi ui, SoundPlayer soundPlayer, service.logger.Logger logger, MessagePrinter messagePrinter) {
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
                Logger.getLogger(RandomLichKing.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return newYearThread;
    }

    private static void KillButtonActionPerformed(MessagePrinter messagePrinter) {
        Thread threadExit = new Thread(() -> {
            try {
                exiting = true;
                messagePrinter.printKilledMessage();

                System.exit(0);

            } catch (Exception ex) {
                Logger.getLogger(RandomLichKing.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        if (!starting && !newYear)
            threadExit.start();
    }

}
