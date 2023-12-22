package com.gdn.randomlichking.ui;

import javax.swing.*;
import java.awt.*;

public class LichKingUi extends javax.swing.JFrame {

    private JPanel mainPanel;
    private JPanel videoPanel;
    private JPanel consolePanel;
    private JScrollPane jScrollPane;
    private JTextPane consoleTextPane;
    private JButton killButton;
    private JLabel gifLabel;

    public LichKingUi() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle("Wrath of the Booze King");
        setIconImage(new ImageIcon("data/LichKingIcon.png").getImage());

        jScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 10));

        consoleTextPane.setBackground(new java.awt.Color(0, 0, 0));
        consoleTextPane.setBorder(null);
        consoleTextPane.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 18));
        consoleTextPane.setForeground(new java.awt.Color(255, 255, 255));
        consoleTextPane.setFocusable(false);
        jScrollPane.setViewportView(consoleTextPane);

        gifLabel.setIcon(new ImageIcon("data/LichKingAnimatedWallpaper.gif"));

        add(mainPanel);
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public JTextPane getConsoleTextPane() {
        return consoleTextPane;
    }

    public JButton getKillButton() {
        return killButton;
    }

    public JLabel getGifLabel() {
        return gifLabel;
    }
}
