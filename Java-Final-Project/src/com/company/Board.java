package com.company;

import java.awt.*;
import javax.swing.*;

class Board{
    JFrame board = new JFrame("TANKS");

    Board() {
        board.setSize(1000, 1000);
        board.getContentPane().setBackground(Color.BLACK);
        board.setResizable(false);
        board.setLocationRelativeTo(null);
        board.setLayout(null);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void show() { board.setVisible(true); }

}
