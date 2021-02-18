package com.company;

import java.awt.*;

class Life extends PowerUp {
    Life(int x, int y) { super(x, y); }

    void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.PINK);
        g2d.drawOval(this.x , this.y , 30, 30);
        g2d.setFont(new Font(g2d.getFont().getName(), g2d.getFont().getStyle(), 38));
        g2d.drawString("♥", this.x+2, this.y+29);
    }

    void growOld() {
        this.age--;
        if (this.age <= 0) { this.on_map = false; }
    }
}
