import javax.sound.sampled.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RandomLichKingV2 extends javax.swing.JFrame {
    //TODO: ADHERE TO OOP AND SOLID. THIS CLASS SHOULD ONLY HAVE THE main method
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm");
    static DateFormat newYearDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static int minWait;
    private static int maxWait;
    private static boolean starting;
    private static boolean exiting;
    private static boolean newYear;
    private static javax.swing.JTextPane jConsole;

    public RandomLichKingV2() {
        initComponents();

        starting = true;
        exiting = false;
        newYear = false;

        // Test wait times
        //minWait = 10000; // 10 sec
        //maxWait = 30000; // 30 sec

        // Prod wait times
        minWait = 300000; // 5 min
        maxWait = 1200000; // 20 min

    }

    private static void appendToPane(String msg, Color c) {
        StyleContext styleContext = StyleContext.getDefaultStyleContext();
        AttributeSet attributeSet = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.FontFamily, "Lucida Console");
        attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = jConsole.getDocument().getLength();
        jConsole.setCaretPosition(len);
        jConsole.setCharacterAttributes(attributeSet, false);
        jConsole.replaceSelection(msg);
    }

    static void playSound(String soundFile) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File file = new File("./" + soundFile);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.toURI().toURL());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> new RandomLichKingV2().setVisible(true));

        try {
            Desktop.getDesktop().open(new File("data/LichKingAnimatedWallpaper.mp4"));

            Timestamp welcomTimeStamp = new Timestamp(System.currentTimeMillis());
            appendToPane("[" + SIMPLE_DATE_FORMAT.format(welcomTimeStamp) + "] " + "The Lich King says: I suppose a welcome is in order... So welcome, insects, welcome to MY WORLD! \n\n", Color.CYAN);

            playSound("data/Welcome.wav");
            Thread.sleep(15000);
            starting = false;

            Thread newYearThread = getNewYearThread();
            newYearThread.start();

            while (!exiting) {
                if (!newYear) {
                    Timestamp dormantTimeStamp = new Timestamp(System.currentTimeMillis());
                    appendToPane("[" + SIMPLE_DATE_FORMAT.format(dormantTimeStamp) + "] " + "The Lich King is dormant... \n\n", Color.WHITE);
                }

                int random = 0;
                random = new Random().nextInt((maxWait - minWait) + 1) + minWait;
                Thread.sleep(random);

                if (!exiting && !newYear) {
                    Timestamp yellTimeStamp = new Timestamp(System.currentTimeMillis());
                    appendToPane("[" + SIMPLE_DATE_FORMAT.format(yellTimeStamp) + "] " + "The Lich King yells: Frostmourne hungers! \n", Color.red);
                    appendToPane("\n", Color.red);
                    playSound("data/FrostmourneHungers.wav");
                    Thread.sleep(7000);
                }
            }
        } catch (Exception exception) {
            Logger.getLogger(RandomLichKingV2.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

    private static Thread getNewYearThread() {
        Thread newYearThread = new Thread(() -> {
            try {
                Date date = newYearDateFormatter.parse("2023-12-31 23:59:40"); //TODO: make this dynamic
                //Testing date:
                //Date date = newYearDateFormatter.parse("2021-12-25 18:59:40");
                Timer timer = new Timer();
                timer.schedule(new NewYearTask(), date);
            } catch (ParseException ex) {
                Logger.getLogger(RandomLichKingV2.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return newYearThread;
    }


    private void initComponents() {

        javax.swing.JPanel panelConsole = new javax.swing.JPanel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        jConsole = new javax.swing.JTextPane();
        javax.swing.JButton killButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocation(new java.awt.Point(980, 8));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 10));

        jConsole.setBackground(new java.awt.Color(0, 0, 0));
        jConsole.setBorder(null);
        jConsole.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 18));
        jConsole.setForeground(new java.awt.Color(255, 255, 255));
        jConsole.setFocusable(false);
        jScrollPane1.setViewportView(jConsole);

        javax.swing.GroupLayout panelConsoleLayout = new javax.swing.GroupLayout(panelConsole);
        panelConsole.setLayout(panelConsoleLayout);
        panelConsoleLayout.setHorizontalGroup(
                panelConsoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
        );
        panelConsoleLayout.setVerticalGroup(
                panelConsoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelConsoleLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        killButton.setForeground(new java.awt.Color(255, 0, 0));
        killButton.setText("Kill");
        killButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        killButton.setFocusable(false);
        killButton.setRequestFocusEnabled(false);
        killButton.addActionListener(this::KillButtonActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelConsole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(killButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(223, 223, 223))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelConsole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(killButton)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void KillButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Thread threadExit = new Thread(() -> {
            try {
                exiting = true;
                Timestamp exitTimeStamp = new Timestamp(System.currentTimeMillis());
                appendToPane("[" + SIMPLE_DATE_FORMAT.format(exitTimeStamp) + "] " + "Arthas says: I see... only... darkness... before... me... \n\n", Color.CYAN);
                appendToPane("\n", Color.CYAN);
                playSound("data/OnlyDarkness.wav");

                Thread.sleep(9000);

                Runtime runtime = Runtime.getRuntime();
                runtime.exec("taskkill /F /IM Video.UI.exe");
                dispose();
                System.exit(0);
            } catch (Exception ex) {
                Logger.getLogger(RandomLichKingV2.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        if (!starting && !newYear)
            threadExit.start();
    }

    private static class NewYearTask extends TimerTask { //TODO: Move to own class
        @Override
        public void run() {
            try {
                newYear = true;
                Thread.sleep(10000);
                Timestamp newYearTS = new Timestamp(System.currentTimeMillis());
                playSound("data/Lich King countdown.wav");
                appendToPane("[" + SIMPLE_DATE_FORMAT.format(newYearTS) + "] " + "The Lich King says: 10 \n\n", Color.CYAN);
                Thread.sleep(1000);
                appendToPane("The Lich King says: 9 \n\n", Color.CYAN);
                Thread.sleep(1000);
                appendToPane("The Lich King says: 8 \n\n", Color.CYAN);
                Thread.sleep(1000);
                appendToPane("The Lich King says: 7 \n\n", Color.CYAN);
                Thread.sleep(1000);
                appendToPane("The Lich King says: 6 \n\n", Color.CYAN);
                Thread.sleep(1000);
                appendToPane("The Lich King says: 5 \n\n", Color.CYAN);
                Thread.sleep(1000);
                appendToPane("The Lich King says: 4 \n\n", Color.CYAN);
                Thread.sleep(1000);
                appendToPane("The Lich King says: 3 \n\n", Color.CYAN);
                Thread.sleep(1000);
                appendToPane("The Lich King says: 2 \n\n", Color.CYAN);
                Thread.sleep(1000);
                appendToPane("The Lich King says: 1 \n\n", Color.CYAN);
                Thread.sleep(1000);

                appendToPane("The Lich King says: Happy New Year, insects! \n\n", Color.CYAN);
                appendToPane("\n", Color.CYAN);
                Thread.sleep(3000);
                appendToPane("Everyone have earned the achievement: ", Color.WHITE);
                appendToPane("[Happy New Year, Insects!]\n\n", Color.ORANGE);
                appendToPane("\n", Color.CYAN);
                playSound("data/AchievementSound.wav");
                Desktop.getDesktop().open(new File("data/Achi/Happy New Year Achievement.png"));

                Desktop.getDesktop().open(new File("data/Fireworks.mp4"));
                Thread.sleep(60000);
                Runtime runtime = Runtime.getRuntime();
                runtime.exec("taskkill /F /IM Microsoft.Photos.exe");

                Desktop.getDesktop().open(new File("data/LichKingAnimatedWallpaper.mp4"));
                newYear = false;
            } catch (Exception ex) {
                Logger.getLogger(RandomLichKingV2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
