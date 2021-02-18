package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

class NewGame extends Board{
    private JTextField p1_name_t = new JTextField(Data.getInstance().get_name_1());
    private JTextField p2_name_t = new JTextField(Data.getInstance().get_name_2());

    private JComboBox<Data.tank_color> p1_color_c = new JComboBox<>(Data.tank_color.values());
    private JComboBox<Data.tank_color> p2_color_c = new JComboBox<>(Data.tank_color.values());

    NewGame() {
        JLabel logo_img = new JLabel(new ImageIcon("./resource/Images/logo.png"));
        logo_img.setBounds(110, 0, 800, 400);
        board.add(logo_img);
        JLabel name = new JLabel("  TANKS GAME");
        name.setBounds(100, 320, 800, 200);
        name.setFont(new Font(name.getFont().getName(), name.getFont().getStyle(), 100));
        name.setForeground(Color.LIGHT_GRAY);
        board.add(name);

        JButton map = new JButton("CHOOSE MAP");
        map.setBounds(400, 700, 200, 50);
        map.setBackground(Color.LIGHT_GRAY);
        board.add(map);
        map.addActionListener(this::map);

        JButton back = new JButton("GO BACK");
        back.setBounds(400, 800, 200, 50);
        back.setBackground(Color.LIGHT_GRAY);
        board.add(back);
        back.addActionListener(this::back);

        JLabel p1_name = new JLabel("PLAYER 1 NAME");
        p1_name.setBounds(50, 550, 200, 50);
        p1_name.setBackground(Color.LIGHT_GRAY);
        p1_name.setOpaque(true);
        board.add(p1_name);

        JLabel p2_name = new JLabel("PLAYER 2 NAME");
        p2_name.setBounds(550, 550, 200, 50);
        p2_name.setBackground(Color.LIGHT_GRAY);
        p2_name.setOpaque(true);
        board.add(p2_name);

        JLabel p1_color = new JLabel("PLAYER 1 COLOR");
        p1_color.setBounds(50, 610, 200, 50);
        p1_color.setBackground(Color.LIGHT_GRAY);
        p1_color.setOpaque(true);
        board.add(p1_color);

        JLabel p2_color = new JLabel("PLAYER 2 COLOR");
        p2_color.setBounds(550, 610, 200, 50);
        p2_color.setBackground(Color.LIGHT_GRAY);
        p2_color.setOpaque(true);
        board.add(p2_color);

        p1_name_t.setBounds(250, 550, 200, 50);
        board.add(p1_name_t);

        p2_name_t.setBounds(750, 550, 200, 50);
        board.add(p2_name_t);

        p1_color_c.setBounds(250, 610, 200, 50);
        board.add(p1_color_c);

        p2_color_c.setBounds(750, 610, 200, 50);
        board.add(p2_color_c);
    }

    private void back(ActionEvent e) {
        new Menu().show();
        board.setVisible(false);
    }

    private void map(ActionEvent e) {
        Data.getInstance().set_name_1(p1_name_t.getText());
        Data.getInstance().set_name_2(p2_name_t.getText());
        int c1 = p1_color_c.getSelectedIndex(), c2 = p2_color_c.getSelectedIndex();
        if (c1 != c2) {
            Data.getInstance().set_color_1((Data.tank_color)p1_color_c.getSelectedItem());
            Data.getInstance().set_color_2((Data.tank_color)p2_color_c.getSelectedItem());
            new Map().show();
            board.setVisible(false);
        }
    }
}
