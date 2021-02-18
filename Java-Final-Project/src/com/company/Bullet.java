package com.company;

import java.awt.Color;
import java.awt.Graphics;


class Bullet {
    double x, y, direction;
    Color c;
    Boolean on_map;
    private int age = 200;

    Bullet(int x, int y, double d) {
        this.x = x;
        this.y = y;
        this.direction = d;
        this.on_map = true;
    }

    void paint(Graphics g) {
        g.setColor(this.c);
        g.fillOval((int)this.x, (int)this.y, 10, 10);
    }

    void growOld() {
        this.age--;
        if (this.age <= 0) { this.on_map = false; }
    }

    void move() {
        this.x += (double)Math.round(8.0D * Math.sin(this.direction));
        this.y += (double)Math.round(8.0D * Math.cos(this.direction));
    }

    Data.hit_wall_e hit_wall(Wall w) {
        if (this.x - w.x - w.width < 10 && this.x - w.x - w.width > 0 && this.y <= w.y + w.height + 5 && this.y >= w.y - 5) { return Data.hit_wall_e.Vertical; }
        if (this.y - w.y < 10 && this.y - w.y > 0 && this.x <= w.x + w.width + 5 && this.x >= w.x - 5) { return Data.hit_wall_e.Horizontal; }
        if (w.x - this.x < 10 && w.x - this.x > 0 && this.y <= w.y + w.height + 5 && this.y >= w.y - 5) { return Data.hit_wall_e.Vertical; }
        if (this.y - w.y - w.height < 10 && this.y - w.y - w.height > 0 && this.x <= w.x + w.width + 5 && this.x >= w.x - 5) { return Data.hit_wall_e.Horizontal; }

        return Data.hit_wall_e.None;
    }

    boolean hit_tank(Tank t) { return (Math.abs(this.x - t.x) <= 25 && Math.abs(this.y - t.y) <= 25); }
}
