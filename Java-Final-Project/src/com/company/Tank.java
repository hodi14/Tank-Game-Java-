package com.company;

import java.awt.*;

class Tank  {
    double x, y, direction, av = 0.02, x_gun, y_gun;
    Color c;
    boolean has_shield = false;

    Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(c);
        g2d.setStroke(new BasicStroke(8));

        x_gun = Math.round(this.x + 35 * Math.sin(this.direction));
        y_gun = Math.round(this.y + 35 * Math.cos(this.direction));

        g2d.fillOval((int) this.x - 30, (int) this.y - 30, 60, 60);
        g2d.drawLine((int) this.x, (int) this.y, (int) this.x_gun, (int) this.y_gun);
    }

    private void change_direction(double amount) {
        this.direction = (this.direction + amount) % (2 * Math.PI);
    }

    void turn_left() {
        this.change_direction(av);
    }

    void turn_right() {
        this.change_direction(-av);
    }

    void move() {
        this.x += Math.round(2 * Math.sin(this.direction));
        this.y += Math.round(2 * Math.cos(this.direction));
    }

    boolean can_move() {
        if ((this.x <= 40 && this.direction > Math.PI && this.direction < 2 * Math.PI) || (this.x <= 40 && this.direction > -Math.PI && this.direction < 0))
            return false;
        if ((this.x >= 960 && this.direction > 0 && this.direction < Math.PI) || (this.x >= 960 && this.direction > -2 * Math.PI && this.direction < -Math.PI))
            return false;
        if ((this.y >= 960 && this.direction > 0 && this.direction < Math.PI / 2) || (this.y >= 960 && this.direction > 3 * Math.PI / 2 && this.direction < 2 * Math.PI))
            return false;
        return (!(this.y >= 960) || !(this.direction < 0) || !(this.direction > -Math.PI / 2)) && (!(this.y >= 960) || !(this.direction > -2 * Math.PI) || !(this.direction < -3 * Math.PI / 2));
    }

    boolean hit_wall(Wall w) {
        if (w.y - this.y <= 29 && w.y - this.y >= 25 && this.x >= w.x - 30 && this.x <= w.x + w.width + 30) {
            if ((this.direction >= 0 && this.direction <= Math.PI / 2) || (this.direction >= 3 * Math.PI / 2 && this.direction <= 2 * Math.PI)) { return true; }
            return ((this.direction <= 0 && this.direction >= -Math.PI / 2) || (this.direction >= -2 * Math.PI && this.direction <= -3 * Math.PI / 2));
        }
        if (-w.y - w.height + this.y <= 29 && -w.y - w.height + this.y >= 25 && this.x >= w.x - 30 && this.x <= w.x + w.width + 30)
            return ((this.direction >= Math.PI / 2 && this.direction <= 3 * Math.PI / 2) || (this.direction >= -3 * Math.PI / 2 && this.direction <= -Math.PI / 2));
        if (this.x - w.x - w.width <= 29 && this.x - w.x - w.width >= 25 && this.y >= w.y - 30 && this.y <= w.y + w.height + 30)
            return (this.direction >= Math.PI && this.direction <= 2 * Math.PI || (this.direction >= -Math.PI && this.direction <= 0));
        if (w.x - this.x <= 29 && w.x - this.x >= 25 && this.y >= w.y - 30 && this.y <= w.y + w.height + 30)
            return (this.direction >= 0 && this.direction <= Math.PI) || (this.direction >= -2 * Math.PI && this.direction <= -Math.PI);

        return false;
    }

    boolean hit_life(Life l) { return Math.abs(this.x - l.x) <= 40 && Math.abs(this.y - l.y) <= 40; }
    boolean hit_bullet(Bullet_p bp) { return Math.abs(this.x - bp.x) <= 40 && Math.abs(this.y - bp.y) <= 40; }
    boolean hit_shield(Shield s) { return Math.abs(this.x - s.x) <= 40 && Math.abs(this.y - s.y) <= 40; }
}
