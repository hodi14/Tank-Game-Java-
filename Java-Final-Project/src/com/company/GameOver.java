package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

class GameOver extends Board {
    GameOver(String winner_s, Color c) {
        JLabel winner_j = new JLabel(winner_s);
        winner_j.setBounds(20, 200, 500, 200);
        winner_j.setFont(new Font(winner_j.getFont().getName(), winner_j.getFont().getStyle(), 70));
        winner_j.setForeground(c);
        board.add(winner_j);

        JLabel won = new JLabel("WON !!!");
        won.setBounds(20, 300, 500, 200);
        won.setFont(new Font(winner_j.getFont().getName(), winner_j.getFont().getStyle(), 70));
        won.setForeground(c);
        board.add(won);

        JLabel cup_img = null;
        if (c == Color.GREEN) { cup_img = new JLabel(new ImageIcon("./resource/Images/green_win.png")); }
        if (c == Color.RED) { cup_img = new JLabel(new ImageIcon("./resource/Images/red_win.png")); }
        if (c == Color.BLUE) { cup_img = new JLabel(new ImageIcon("./resource/Images/blue_win.png")); }

        assert cup_img != null;
        cup_img.setBounds(520, 100, 450, 500);
        board.add(cup_img);

        JButton rematch = new JButton("REMATCH");
        rematch.setBounds(400, 650, 200, 50);
        rematch.setBackground(Color.LIGHT_GRAY);
        board.add(rematch);
        rematch.addActionListener(this::rematch);

        JButton back = new JButton("GO BACK TO MAIN MENU");
        back.setBounds(400, 750, 200, 50);
        back.setBackground(Color.LIGHT_GRAY);
        board.add(back);
        back.addActionListener(this::back);
    }

    private void back(ActionEvent e) {
        Data.getInstance().set_life(Data.getInstance().starting_life);
        Data.getInstance().set_bullet_n(Data.getInstance().starting_bullet);
        new Menu().show();
        board.setVisible(false);
    }

    private void rematch(ActionEvent e) {
        Data.getInstance().set_life(Data.getInstance().starting_life);
        Data.getInstance().set_bullet_n(Data.getInstance().starting_bullet);
        new GamePlay();
        board.setVisible(false);
    }
}
