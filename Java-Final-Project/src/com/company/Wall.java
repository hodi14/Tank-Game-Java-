package com.company;

import java.awt.*;

class Wall {
    int x, y, width, height;

    Wall(int x, int y, int w, int h ) {
        this.x = x;
        this.y = y;
        this.width= w;
        this.height = h;
    }

    void paint(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(this.x , this.y , this.width, this.height);
    }
}
