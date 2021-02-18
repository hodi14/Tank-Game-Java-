package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;
import javax.swing.*;

class Map extends Board {
    private String[] map_numbers = {"Map 1", "Map 2", "Map 3", "Map 4", "Map 5", "Map 6"};
    private JComboBox<String> map_number = new JComboBox<>(map_numbers);

    Map() {
        JLabel map1_lb = new JLabel("1");
        map1_lb.setBounds(80, 50, 50, 50);
        map1_lb.setForeground(Color.LIGHT_GRAY);
        map1_lb.setFont(new Font(map1_lb.getFont().getName(), map1_lb.getFont().getStyle(), 30));
        board.add(map1_lb);
        JLabel map1_img = new JLabel(new ImageIcon("./resource/Images/map1.png"));
        map1_img.setBounds(100, 50, 200, 200);
        board.add(map1_img);

        JLabel map2_lb = new JLabel("2");
        map2_lb.setBounds(380, 50, 50, 50);
        map2_lb.setForeground(Color.LIGHT_GRAY);
        map2_lb.setFont(new Font(map2_lb.getFont().getName(), map2_lb.getFont().getStyle(), 30));
        board.add(map2_lb);
        JLabel map2_img = new JLabel(new ImageIcon("./resource/Images/map2.png"));
        map2_img.setBounds(400, 50, 200, 200);
        board.add(map2_img);

        JLabel map3_lb = new JLabel("3");
        map3_lb.setBounds(680, 50, 50, 50);
        map3_lb.setForeground(Color.LIGHT_GRAY);
        map3_lb.setFont(new Font(map3_lb.getFont().getName(), map3_lb.getFont().getStyle(), 30));
        board.add(map3_lb);
        JLabel map3_img = new JLabel(new ImageIcon("./resource/Images/map3.png"));
        map3_img.setBounds(700, 50, 200, 200);
        board.add(map3_img);

        JLabel map4_lb = new JLabel("4");
        map4_lb.setBounds(80, 300, 50, 50);
        map4_lb.setForeground(Color.LIGHT_GRAY);
        map4_lb.setFont(new Font(map4_lb.getFont().getName(), map4_lb.getFont().getStyle(), 30));
        board.add(map4_lb);
        JLabel map4_img = new JLabel(new ImageIcon("./resource/Images/map4.png"));
        map4_img.setBounds(100, 300, 200, 200);
        board.add(map4_img);

        JLabel map5_lb = new JLabel("5");
        map5_lb.setBounds(380, 300, 50, 50);
        map5_lb.setForeground(Color.LIGHT_GRAY);
        map5_lb.setFont(new Font(map5_lb.getFont().getName(), map5_lb.getFont().getStyle(), 30));
        board.add(map5_lb);
        JLabel map5_img = new JLabel(new ImageIcon("./resource/Images/map5.png"));
        map5_img.setBounds(400, 300, 200, 200);
        board.add(map5_img);

        JLabel map6_lb = new JLabel("6");
        map6_lb.setBounds(680, 300, 50, 50);
        map6_lb.setForeground(Color.LIGHT_GRAY);
        map6_lb.setFont(new Font(map6_lb.getFont().getName(), map6_lb.getFont().getStyle(), 30));
        board.add(map6_lb);
        JLabel map6_img = new JLabel(new ImageIcon("./resource/Images/map6.png"));
        map6_img.setBounds(700, 300, 200, 200);
        board.add(map6_img);

        JButton back = new JButton("GO BACK");
        back.setBounds(400, 750, 200, 50);
        back.setBackground(Color.LIGHT_GRAY);
        board.add(back);
        back.addActionListener(this::back);

        JButton start = new JButton("START THE GAME");
        start.setBounds(400, 650, 200, 50);
        start.setBackground(Color.LIGHT_GRAY);
        board.add(start);
        start.addActionListener(this::start);

        JLabel map = new JLabel("MAP NUMBER");
        map.setBounds(300, 550, 200, 50);
        map.setBackground(Color.LIGHT_GRAY);
        map.setOpaque(true);
        board.add(map);

        map_number.setBounds(500, 550, 200, 50);
        board.add(map_number);
    }

    private void start(ActionEvent e) {
        char mc = (Objects.requireNonNull(map_number.getSelectedItem()).toString()).charAt(4);
        int mi = Character.getNumericValue(mc);
        Data.getInstance().set_map(mi);
        new GamePlay();
        board.setVisible(false);
    }

    private void back(ActionEvent e) {
        new NewGame().show();
        board.setVisible(false);
    }
}
