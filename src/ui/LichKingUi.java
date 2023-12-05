package ui;

import audio.SoundPlayer;

import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LichKingUi extends javax.swing.JFrame {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm");
    private final boolean newYear;
    private final boolean starting;
    private boolean exiting;
    private javax.swing.JTextPane jConsole;
    private final SoundPlayer soundPlayer;

    public LichKingUi(SoundPlayer soundPlayer) {
        initComponents();
        this.soundPlayer = soundPlayer;
        this.starting = true;
        this.exiting = false;
        this.newYear = false;
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
        panelConsoleLayout.setHorizontalGroup(panelConsoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE));
        panelConsoleLayout.setVerticalGroup(panelConsoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(panelConsoleLayout.createSequentialGroup().addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE)));

        killButton.setForeground(new java.awt.Color(255, 0, 0));
        killButton.setText("Kill");
        killButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        killButton.setFocusable(false);
        killButton.setRequestFocusEnabled(false);
        killButton.addActionListener(this::KillButtonActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(panelConsole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(killButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(223, 223, 223)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(panelConsole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(killButton).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
    }


    private void appendToPane(String msg, Color c) {
        StyleContext styleContext = StyleContext.getDefaultStyleContext();
        AttributeSet attributeSet = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.FontFamily, "Lucida Console");
        attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = jConsole.getDocument().getLength();
        jConsole.setCaretPosition(len);
        jConsole.setCharacterAttributes(attributeSet, false);
        jConsole.replaceSelection(msg);
    }

    private void KillButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Thread threadExit = new Thread(() -> {
            try {
                exiting = true;
                Timestamp exitTimeStamp = new Timestamp(System.currentTimeMillis());
                appendToPane("[" + SIMPLE_DATE_FORMAT.format(exitTimeStamp) + "] " + "Arthas says: I see... only... darkness... before... me... \n\n", Color.CYAN);
                appendToPane("\n", Color.CYAN);
                soundPlayer.playSound("data/OnlyDarkness.wav");

                Thread.sleep(9000);

                Runtime runtime = Runtime.getRuntime();
                runtime.exec("taskkill /F /IM Video.UI.exe");
                dispose();
                System.exit(0);
            } catch (Exception ex) {
               // Logger.getLogger(RandomLichKingV2.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        if (!starting && !newYear) {
            threadExit.start();
        }
    }
}
