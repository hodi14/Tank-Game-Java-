package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

class Settings extends Board {
    private JTextField bullet_pt = new JTextField(Integer.toString(Data.getInstance().get_bullet_p()));
    private JTextField life_t = new JTextField(Integer.toString(Data.getInstance().starting_life));
    private JTextField bullet_nt = new JTextField(Integer.toString(Data.getInstance().starting_bullet));

    Settings() {
        JLabel logo_img = new JLabel(new ImageIcon("./resource/Images/logo.png"));
        logo_img.setBounds(110, 0, 800, 400);
        board.add(logo_img);
        JLabel name = new JLabel("  TANKS GAME");
        name.setBounds(100, 320, 800, 200);
        name.setFont(new Font(name.getFont().getName(), name.getFont().getStyle(), 100));
        name.setForeground(Color.LIGHT_GRAY);
        board.add(name);

        JButton back = new JButton("GO BACK");
        back.setBounds(400, 850, 200, 50);
        back.setBackground(Color.LIGHT_GRAY);
        board.add(back);
        back.addActionListener(this::back);

        JLabel life = new JLabel("        STARTING LIFE (<1000)");
        life.setBounds(300, 550, 200, 50);
        life.setBackground(Color.LIGHT_GRAY);
        life.setOpaque(true);
        board.add(life);

        JLabel bullet_p = new JLabel("        BULLET POWER (<1000)");
        bullet_p.setBounds(300, 650, 200, 50);
        bullet_p.setBackground(Color.LIGHT_GRAY);
        bullet_p.setOpaque(true);
        board.add(bullet_p);

        JLabel bullet_n = new JLabel("        BULLET NUMBER (<1000)");
        bullet_n.setBounds(300, 750, 200, 50);
        bullet_n.setBackground(Color.LIGHT_GRAY);
        bullet_n.setOpaque(true);
        board.add(bullet_n);

        life_t.setBounds(500, 550, 200, 50);
        board.add(life_t);

        bullet_pt.setBounds(500, 650, 200, 50);
        board.add(bullet_pt);

        bullet_nt.setBounds(500, 750, 200, 50);
        board.add(bullet_nt);
    }

    private void back(ActionEvent e) {
        try {
            int l = Integer.parseInt(life_t.getText())%1000;
            int bn = Integer.parseInt(bullet_nt.getText())%1000;
            int bp = Integer.parseInt(bullet_pt.getText())%1000;
            Data.getInstance().set_life(l);
            Data.getInstance().set_bullet_p(bp);
            Data.getInstance().set_bullet_n(bn);
            new Menu().show();
            board.setVisible(false);
        }
        catch (Exception e1){
               life_t.setText("*");
               bullet_nt.setText("*");
               bullet_pt.setText("*");
        }
    }
}
