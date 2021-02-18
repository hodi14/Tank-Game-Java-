package com.company;

import java.awt.*;

class Shield extends PowerUp {
    int time;
    Shield(int x, int y) { super(x, y); }

    void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.YELLOW);
        g2d.drawOval(this.x , this.y , 30, 30);
        g2d.setFont(new Font(g2d.getFont().getName(), g2d.getFont().getStyle(), 37));
        g2d.drawString("◆", this.x-1, this.y+27);
    }

    void growOld() {
        this.age--;
        if (this.age <= 0) { this.on_map = false; }
    }

    void time_limit() { this.time--; }
}
