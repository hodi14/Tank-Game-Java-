package com.company;

import java.awt.*;

class Bullet_p extends PowerUp {
    Bullet_p(int x, int y) { super(x, y); }

    void paint(Graphics g, Color c) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(c);
        g2d.drawOval(this.x , this.y , 30, 30);
        g2d.setFont(new Font(g2d.getFont().getName(), g2d.getFont().getStyle(), 28));
        g2d.drawString("◙", this.x+3, this.y+24);
    }

    void growOld() {
        this.age--;
        if (this.age <= 0) { this.on_map = false; }
    }
}
