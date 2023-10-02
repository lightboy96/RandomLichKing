/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.awt.Color;
import java.awt.Desktop;
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
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 *
 * @author kozan
 */
public class RandomLichKingV2 extends javax.swing.JFrame
{

    private static int minWait;
    private static int maxWait;
    static DateFormat newYearDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    private static boolean starting;
    private static boolean exiting;
    private static boolean newYear;

    /**
     * Creates new form RandomLichKingWindow
     */
    public RandomLichKingV2()
    {
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

    private static void appendToPane(String msg, Color c)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = jConsole.getDocument().getLength();
        jConsole.setCaretPosition(len);
        jConsole.setCharacterAttributes(aset, false);
        jConsole.replaceSelection(msg);
    }

    static void playSound(String soundFile) throws IOException, UnsupportedAudioFileException, LineUnavailableException
    {
        File f = new File("./" + soundFile);
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }
    
    private static class NewYearTask extends TimerTask
    {
        @Override
        public void run()
        {
            try
            {
                newYear = true; 
                Thread.sleep(10000);
                Timestamp newYearTS = new Timestamp(System.currentTimeMillis());
                playSound("data/Lich King countdown.wav");
                appendToPane("[" + sdf.format(newYearTS) + "] " + "The Lich King says: 10 \n\n", Color.CYAN);
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
                appendToPane("Everyone have earned the achievment: ", Color.WHITE);
                appendToPane("[Happy New Year, Insects!]\n\n", Color.ORANGE);
                appendToPane("\n", Color.CYAN);
                playSound("data/AchievmentSound.wav");                
                Desktop.getDesktop().open(new File("data/Achi/Happy New Year Achievement.png"));               
                
                Desktop.getDesktop().open(new File("data/Fireworks.mp4")); 
                Thread.sleep(60000);
                Runtime rt = Runtime.getRuntime();
                rt.exec("taskkill /F /IM Microsoft.Photos.exe");
                
                Desktop.getDesktop().open(new File("data/LichKingAnimatedWallpaper.mp4"));
                newYear = false;
            }
            catch (Exception ex)
            {
                Logger.getLogger(RandomLichKingV2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        panelConsole = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jConsole = new javax.swing.JTextPane();
        KillButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocation(new java.awt.Point(980, 8));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 10));

        jConsole.setBackground(new java.awt.Color(0, 0, 0));
        jConsole.setBorder(null);
        jConsole.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
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

        KillButton.setForeground(new java.awt.Color(255, 0, 0));
        KillButton.setText("Kill");
        KillButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        KillButton.setFocusable(false);
        KillButton.setRequestFocusEnabled(false);
        KillButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                KillButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelConsole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(KillButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(223, 223, 223))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelConsole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(KillButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void KillButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_KillButtonActionPerformed
    {//GEN-HEADEREND:event_KillButtonActionPerformed
        Thread tExit = new Thread(() ->
        {
            try
            {
                exiting = true;
                Timestamp exitTS = new Timestamp(System.currentTimeMillis());
                appendToPane("[" + sdf.format(exitTS) + "] " + "Arthas says: I see... only... darkness... before... me... \n\n", Color.CYAN);
                appendToPane("\n", Color.CYAN);
                playSound("data/OnlyDarkness.wav");

                Thread.sleep(9000);
                
                Runtime rt = Runtime.getRuntime();
                rt.exec("taskkill /F /IM Video.UI.exe");
                dispose();
                System.exit(0);
            }
            catch (Exception ex)
            {
                Logger.getLogger(RandomLichKingV2.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        if (!starting && !newYear)
            tExit.start();
    }//GEN-LAST:event_KillButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() ->
        {
            new RandomLichKingV2().setVisible(true);
        });

        try
        {
            Desktop.getDesktop().open(new File("data/LichKingAnimatedWallpaper.mp4"));

            Timestamp welcomeTS = new Timestamp(System.currentTimeMillis());
            appendToPane("[" + sdf.format(welcomeTS) + "] " + "The Lich King says: I suppose a welcome is in order... So welcome, insects, welcome to MY WORLD! \n\n", Color.CYAN);

            playSound("data/Welcome.wav");
            Thread.sleep(15000);
            starting = false;

            Thread newYearThread;
            newYearThread = new Thread(()->
            {
                try
                {
                    Date date = newYearDateFormatter.parse("2021-12-31 23:59:40");
                    // Date date = newYearDateFormatter.parse("2021-12-25 18:59:40");
                    Timer timer = new Timer();
                    timer.schedule(new NewYearTask(), date);
                }
                catch (ParseException ex)
                {
                    Logger.getLogger(RandomLichKingV2.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            newYearThread.start();
            
            while (!exiting)
            {  
                if (!exiting && !newYear)
                {            
                    Timestamp dormantTS = new Timestamp(System.currentTimeMillis());
                    appendToPane("[" + sdf.format(dormantTS) + "] " + "The Lich King is dormant... \n\n", Color.WHITE);                   
                }

                int random = 0;
                random = new Random().nextInt((maxWait - minWait) + 1) + minWait;
                Thread.sleep(random);
                
                if (!exiting && !newYear)
                {
                    Timestamp yellTS = new Timestamp(System.currentTimeMillis());
                    appendToPane("[" + sdf.format(yellTS) + "] " + "The Lich King yells: Frostmourne hungers! \n", Color.red);
                    appendToPane("\n", Color.red);
                    playSound("data/FrostmourneHungers.wav");
                    Thread.sleep(7000);
                }
            }          
        }
        catch (Exception ex)
        {
            Logger.getLogger(RandomLichKingV2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton KillButton;
    private static javax.swing.JTextPane jConsole;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelConsole;
    // End of variables declaration//GEN-END:variables
}
