package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

class Menu extends Board {
    Menu() {
        JLabel logo_img = new JLabel(new ImageIcon("./resource/Images/logo.png"));
        logo_img.setBounds(110, 0, 800, 400);
        board.add(logo_img);
        JLabel name = new JLabel("  TANKS GAME");
        name.setBounds(100, 320, 800, 200);
        name.setFont(new Font(name.getFont().getName(), name.getFont().getStyle(), 100));
        name.setForeground(Color.LIGHT_GRAY);
        board.add(name);

        JButton newgame = new JButton("NEW GAME");
        newgame.setBounds(400, 550, 200, 50);
        newgame.setBackground(Color.LIGHT_GRAY);
        board.add(newgame);
        newgame.addActionListener(this::newgame);

        JButton settings = new JButton("SETTINGS");
        settings.setBounds(400, 650, 200, 50);
        settings.setBackground(Color.LIGHT_GRAY);
        board.add(settings);
        settings.addActionListener(this::settings);

        JButton exit = new JButton("EXIT");
        exit.setBounds(400, 750, 200, 50);
        exit.setBackground(Color.LIGHT_GRAY);
        board.add(exit);
        exit.addActionListener(this::exit);
    }

    private void newgame(ActionEvent e) {
        new NewGame().show();
        board.setVisible(false);
    }

    private void settings(ActionEvent e) {
        new Settings().show();
        board.setVisible(false);
    }

    private void exit(ActionEvent e) {
        board.dispose();
    }
}