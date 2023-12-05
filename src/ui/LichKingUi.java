package ui;

import javax.swing.*;
import java.awt.*;

public class LichKingUi extends javax.swing.JFrame {

    private JPanel mainPanel;
    private JPanel consolePanel;
    private JPanel videoPanel;
    private JScrollPane jScrollPane;
    private JTextPane consoleTextPane;
    private JButton killButton;

    public LichKingUi() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        jScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 10));

        consoleTextPane.setBackground(new java.awt.Color(0, 0, 0));
        consoleTextPane.setBorder(null);
        consoleTextPane.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 18));
        consoleTextPane.setForeground(new java.awt.Color(255, 255, 255));
        consoleTextPane.setFocusable(false);
        jScrollPane.setViewportView(consoleTextPane);

        killButton.setForeground(new java.awt.Color(255, 0, 0));
        killButton.setText("Kill");
        killButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        killButton.setFocusable(false);
        killButton.setRequestFocusEnabled(false);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    public JTextPane getConsoleTextPane() {
        return consoleTextPane;
    }

    public JButton getKillButton() {
        return killButton;
    }
}
